package com.malaga.service;

public interface AuthService {

	/**
	 * Method to authenticate the user
	 * 
	 * 
	 * @param user
	 * @param pass
	 * @return
	 */
	boolean authenticated(String user, String pass);

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

}
