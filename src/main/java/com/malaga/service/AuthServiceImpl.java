package com.malaga.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.malaga.constants.ConstantsAdmin;
import com.malaga.dto.UserDTO;
import com.malaga.exceptions.AdministratorException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	UserService userService;

	@Autowired
	AuthenticationManagerBuilder memory;

	@Override
	public boolean authenticated(String idUser, String pass) throws AdministratorException {

		boolean passOk = false;

		UserDTO user = userService.existUsername(idUser);

		// List permissions
		List<GrantedAuthority> authorities = new ArrayList<>();

		// Contrast password from DB
		if (pass != null && pass.equals(getPass(idUser, user))) {

			// By default
			authorities.add(new SimpleGrantedAuthority(ConstantsAdmin.ROLE_USER));

			// add ROLE Addmin
			addRoleAdmin(idUser, user, authorities);

			UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(idUser, pass,
					authorities);

			SecurityContext sc = SecurityContextHolder.getContext();

			sc.setAuthentication(authReq);
			passOk = isAuthenticated(idUser);
		}
		return passOk;
	}

	/**
	 * Method toGet the password
	 * 
	 * 
	 * 
	 * @param iduser
	 * @param user
	 * @return
	 */
	private String getPass(String iduser, UserDTO user) {

		String pass = null;

		// Check from Memory
		if (user == null) {
			try {
				UserDetails objUser = memory.getDefaultUserDetailsService().loadUserByUsername(iduser);
				pass = objUser.getPassword();

			} catch (Exception e) {
				log.error(e.getMessage());
			}

		} else {
			// get from Database
			pass = user.getPassword();
		}

		return pass;
	}

	/**
	 * 
	 * Method add the Role_ADMIN
	 * 
	 * @param iduser
	 * @param user
	 * @param authorities
	 */
	private final void addRoleAdmin(String iduser, UserDTO user, List<GrantedAuthority> authorities) {

		if (isAdmin(iduser, user)) {

			// add ROLE ADMIN
			authorities.add(new SimpleGrantedAuthority(ConstantsAdmin.ROLE_ADMIN));
		}

	}

	/**
	 * Check if the user isAdmin
	 * 
	 * @param iduser
	 * @param user
	 * @return
	 */
	private final boolean isAdmin(String iduser, UserDTO user) {

		boolean isAdmin = false;

		// Check from Memory
		if (user == null) {
			try {
				UserDetails objUser = memory.getDefaultUserDetailsService().loadUserByUsername(iduser);
				// check if the user memory is admin
				if (objUser.getUsername().equals("admin")) {
					isAdmin = true;
				}
			} catch (Exception e) {

				log.error(e.getMessage());
			}

		} else {
			isAdmin = user.isAdmin();
		}
		return isAdmin;

	}

	/**
	 * Check The ROLE ADMIN into the authenticated user
	 * 
	 * @return
	 */
	private final boolean checkAutheticatedUser() {

		boolean check = false;
		SecurityContext sc = SecurityContextHolder.getContext();

		if (sc.getAuthentication() != null) {

			Collection<? extends GrantedAuthority> perms = sc.getAuthentication().getAuthorities();
			for (GrantedAuthority auth : perms) {
				if (auth.getAuthority().equals(ConstantsAdmin.ROLE_ADMIN)) {
					check = true;
				}
			}
		}

		return check;
	}

	/**
	 * Check if the username is authenticated to allow operations or the
	 * authenticated user is admininistrator
	 */
	@Override
	public boolean isAuthenticated(String username) {
		boolean is = false;
		SecurityContext sc = SecurityContextHolder.getContext();

		if (sc.getAuthentication() != null && sc.getAuthentication().getName() != null) {
			if (sc.getAuthentication().getName().equals(username) || checkAutheticatedUser()) {
				is = sc.getAuthentication().isAuthenticated();
			}
		}
		return is;
	}

	@Override
	public String currentUser() {

		String user = "none";
		SecurityContext sc = SecurityContextHolder.getContext();

		if (sc.getAuthentication() != null) {

			user = sc.getAuthentication().getName();
		}

		return user;
	}

	@Override
	public boolean checkRestrictions(String user) {
		// TODO Pending
		return false;
	}

}
