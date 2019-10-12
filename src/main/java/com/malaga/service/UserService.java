package com.malaga.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.malaga.dto.UserDTO;
import com.malaga.exceptions.AdministratorException;

public interface UserService {

	/**
	 * List all the users (for up layers)
	 * 
	 * Operation allowed for the Admin
	 * 
	 * @return List of users
	 * @throws AdministratorException
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<UserDTO> findAll() throws AdministratorException;

	/**
	 * Create users
	 * 
	 * Operation allowed for the Admin
	 * 
	 * @return user created
	 * @throws AdministratorException
	 */

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserDTO create(UserDTO user) throws AdministratorException;

	/**
	 * Update the Data User
	 * 
	 * @param user
	 * @return user Updated
	 * @throws AdministratorException
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	public UserDTO update(UserDTO user) throws AdministratorException;

	/**
	 * Get the Data User for the users
	 * 
	 * 
	 * @param idUser
	 * @return
	 * @throws AdministratorException
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	public UserDTO findByUsername(String idUser) throws AdministratorException;

	/**
	 * Delete User For the Administrators
	 * 
	 * @param user to be deleted
	 * @throws AdministratorException
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void delete(UserDTO user) throws AdministratorException;
	
	
	/**
	 * Check the user without Authorizations
	 * 
	 * @param idUser
	 * @return
	 * @throws AdministratorException
	 */
	public UserDTO existUsername(String idUser) throws AdministratorException;

}
