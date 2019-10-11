package com.malaga.service;

import java.util.List;

import com.malaga.dto.UserDTO;
import com.malaga.exceptions.AdministratorException;

/**
 * User Service
 * 
 * @author Daniel González Ortegón
 * @date 11/10/2019
 */

public interface UserService {

	/**
	 * List all the users (for up layers)
	 * 
	 * Operation allowed for the Admin
	 * 
	 * @return List of users
	 * @throws AdministratorException
	 */
	public List<UserDTO> findAll() throws AdministratorException;

	/**
	 * Create users
	 * 
	 * Operation allowed for the Admin
	 * 
	 * @return user created
	 * @throws AdministratorException
	 */

	public UserDTO create(UserDTO user) throws AdministratorException;

	/**
	 * Update the Data User
	 * 
	 * @param user
	 * @return user Updated
	 * @throws AdministratorException
	 */
	public UserDTO update(UserDTO user) throws AdministratorException;

	/**
	 * Get the Data User for the users
	 * 
	 * 
	 * @param idUser
	 * @return
	 * @throws AdministratorException
	 */
	public UserDTO findByUsername(String idUser) throws AdministratorException;

	/**
	 * Delete User For the Administrators
	 * 
	 * @param user to be deleted
	 * @throws AdministratorException
	 */
	public void delete(UserDTO user) throws AdministratorException;

}
