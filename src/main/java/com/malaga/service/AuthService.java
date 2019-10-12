package com.malaga.service;

import com.malaga.exceptions.AdministratorException;

public interface AuthService {

	/**
	 * Method to authenticate the user
	 * 
	 * 
	 * @param user
	 * @param pass
	 * @return
	 * @throws AdministratorException 
	 */
	boolean authenticated(String user, String pass) throws AdministratorException;

	/**
	 * Check if the User is authenticated
	 * 
	 * @param user
	 * @return true in case the user authenticated
	 *  
	 */
	boolean isAuthenticated(String user);

	/**
	 * Check the authorities for the user
	 * 
	 * @param user
	 * @return in case the user is allowed
	 */
	boolean checkRestrictions(String user);

	/**
	 * Return the current User 
	 * @return
	 */
	String currentUser();

}
