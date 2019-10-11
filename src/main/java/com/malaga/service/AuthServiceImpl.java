package com.malaga.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

	@Override
	public boolean authenticated(String user, String pass) {

		boolean passOk = true;
		// Contrast password from DB

		if (pass != null && passOk) {

			UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user, pass);

			SecurityContext sc = SecurityContextHolder.getContext();
			sc.setAuthentication(authReq);
		}
		return passOk;
	}

	@Override
	public boolean isAuthenticated(String user) {
		boolean is = false;
		SecurityContext sc = SecurityContextHolder.getContext();

		if (sc.getAuthentication().getName().equals(user)) {
			is = sc.getAuthentication().isAuthenticated();
		}
		return is;
	}

	@Override
	public boolean checkRestrictions(String user) {
		// TODO Pending
		return false;
	}

}
