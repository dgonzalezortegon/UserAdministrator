package com.malaga.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Configuration and Initialization
 * 
 * the part of Security
 * 
 * @author Dani
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig {

	/**
	 * In Memory two users (provisioning) (USER and ADMIN)
	 * 
	 * Initialized 2 kind of users 
	 * (this a temporal approach)
	 * 
	 * For next features to have :
	 * 
	 * - the Profiles in Database
	 * 
	 * - the password (MD5) in user table
	 * 
	 * 
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void initUsers(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("admin")
				.password("password").roles("USER", "ADMIN");
		
	}
}