package com.malaga.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.malaga.dto.AccountDTO;
import com.malaga.exceptions.AdministratorException;

public interface AccountService {

	/**
	 * List all accounts for one user
	 * 
	 * 
	 * @return List of Accounts
	 * @throws AdministratorException
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<AccountDTO> findAccounts(String user) throws AdministratorException;

	/**
	 * Create account associated for one user
	 * 
	 * Operation allowed for the Admin
	 * 
	 * @return account created
	 * @throws AdministratorException
	 */

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public AccountDTO createAccount(String user, String iban) throws AdministratorException;

}
