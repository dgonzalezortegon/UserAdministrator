package com.malaga.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.malaga.constants.ConstantsAdmin;

@Service
public class AuthServiceImpl implements AuthService {

	@Override
	public boolean authenticated(String user, String pass) {

		boolean passOk = false;
		// Contrast password from DB

		if (pass != null && pass.equals(getPass(user))) {

			UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user, pass);

			SecurityContext sc = SecurityContextHolder.getContext();

			sc.setAuthentication(authReq);
			passOk = isAuthenticated(user);
		}
		return passOk;
	}

	public String getPass(String user) {

		// get from Database
		// next feature
		return ConstantsAdmin.KEY;
	}

	@Override
	public boolean isAuthenticated(String user) {
		boolean is = false;
		SecurityContext sc = SecurityContextHolder.getContext();

		if (sc.getAuthentication() != null && sc.getAuthentication().getName() != null) {
			if (sc.getAuthentication().getName().equals(user) || sc.getAuthentication().getName().equals("admin")) {
				is = true;
			}
		}
		return is;
	}

	@Override
	public boolean checkRestrictions(String user) {
		// TODO Pending
		return false;
	}

}
