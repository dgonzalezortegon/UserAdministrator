package com.malaga.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.malaga.constants.ConstantsAdmin;
import com.malaga.dto.AccountDTO;
import com.malaga.dto.UserDTO;
import com.malaga.exceptions.AdministratorException;
import com.malaga.service.AccountService;
import com.malaga.service.AuthService;
import com.malaga.service.UserService;
import com.malaga.utils.ValidatorUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommandControllerImpl {

	@Autowired
	UserService userService;

	@Autowired
	AuthService authService;

	@Autowired
	AccountService accountService;

	@Autowired
	Gson gson;

	/**
	 * Check if the user is Authenticated
	 * 
	 * @param user
	 * @return
	 */
	public boolean isAuthenticated(String user) {

		return authService.isAuthenticated(user);

	}

	public boolean login(String user, String pass) throws AdministratorException {
		log.debug("Login");
		return authService.authenticated(user, pass);
	}

	public List<UserDTO> findAll() throws AdministratorException {
		log.debug("findAll");

		return userService.findAll();
	}

	/**
	 * CREATE USER
	 * 
	 * @param user
	 * @return
	 * @throws AdministratorException
	 */
	public UserDTO create(String user) throws AdministratorException {

		UserDTO userDto = gson.fromJson(ValidatorUtils.parserStringToDTO(user), UserDTO.class);

		return userService.create(userDto);
	}

	/**
	 * UPDATE USER
	 * 
	 * @param user to be updated
	 * @return
	 * @throws AdministratorException
	 */
	public UserDTO update(String user, String json) throws AdministratorException {

		UserDTO userDto = gson.fromJson(ValidatorUtils.parserStringToDTO(json), UserDTO.class);

		userDto.setUsername(user);

		return userService.update(userDto);
	}

	/**
	 * GET USER
	 * 
	 * @param idUser
	 * @return
	 * @throws AdministratorException
	 */
	public UserDTO findByUsername(String idUser) throws AdministratorException {
		if (!authService.isAuthenticated(idUser)) {
			throw new AccessDeniedException("Access Denied");
		}
		return userService.findByUsername(idUser);
	}

	/**
	 * DELETE User
	 * 
	 * @param user
	 * @throws AdministratorException
	 */
	@Transactional
	public void delete(String idUser) throws AdministratorException {

		UserDTO user = userService.findByUsername(idUser);

		accountService.deleteAccount(user.getId());

		userService.delete(user);

	}

	/**
	 * Create Account
	 * 
	 * @param user
	 * @param iban
	 * @return
	 * @throws AdministratorException
	 */
	public AccountDTO createAccount(String user, String iban) throws AdministratorException {

		return accountService.createAccount(user, iban);
	}

	/**
	 * return the current user
	 * 
	 * @return
	 */
	public String whoAmI() {
		return authService.currentUser();
	}

	/**
	 * Main method for the Console to execute the commands
	 * 
	 * @param args
	 * @return the result
	 */
	public String executeCommand(String args[]) {

		String result = "No command";

		try {
			switch (args[0]) {
			case ConstantsAdmin.WHO:
				result = whoAmI();
				break;
			case ConstantsAdmin.HELP:
				result = ConstantsAdmin.FORMAT_HELP;
				break;
			case ConstantsAdmin.AUTH:
				result = login(args[1], args[2]) + "";
				break;
			case ConstantsAdmin.ALL_USER:
				result = findAll().toString();
				break;
			case ConstantsAdmin.CREATE_USER:
				result = create(args[1]).toString();
				break;
			case ConstantsAdmin.UPDATE_USER:
				update(args[1], args[2]);
				result = "updated";
				break;
			case ConstantsAdmin.GET_USER:
				UserDTO resultUser = findByUsername(args[1]);
				if (resultUser == null) {
					result = "Not found";
				} else {
					result = resultUser.toString();
				}
				break;
			case ConstantsAdmin.DELETE_USER:
				delete(args[1]);
				result = "deleted";
				break;
			case ConstantsAdmin.CREATE_ACCOUNT:
				createAccount(args[1], args[2]);
				result = "IBAN created";
				break;
			default:
				break;
			}

		} catch (BadCredentialsException | AccessDeniedException e) {
			result = e.getMessage();

		} catch (AuthenticationCredentialsNotFoundException e) {
			result = "User No Authenticated";
		} catch (AdministratorException e) {
			result = e.getMessage();
		} catch (Exception e) {
			result = "Error " + args[0];

		}

		return result;

	}

	/**
	 * Validate the Commands
	 * 
	 * @param args
	 * @return
	 */
	public boolean validateCommand(String args[]) {

		Boolean result = true;
		try {

			switch (args[0]) {
			case ConstantsAdmin.AUTH:
				if (args.length != 3) {
					log.error("No Valid LOGIN,   login user pass");
					result = false;
				}
				break;
			case ConstantsAdmin.GET_USER:
				if (args.length != 2) {
					log.error(ConstantsAdmin.FORMAT_GET_USER);
					result = false;
				}
				break;
			case ConstantsAdmin.CREATE_ACCOUNT:
				if (args.length != 3) {
					log.error(ConstantsAdmin.FORMAT_CREATE_ACCOUNT);
					result = false;
				}
				break;
			case ConstantsAdmin.CREATE_USER:
				if (args.length != 2) {
					log.error(ConstantsAdmin.FORMAT_CREATE_USER);
					result = false;
				}
				break;
			case ConstantsAdmin.DELETE_USER:
				if (args.length != 2) {
					log.error(ConstantsAdmin.FORMAT_DELETE_USER);
					result = false;
				}
				break;
			case ConstantsAdmin.UPDATE_USER:
				if (args.length != 3) {
					log.error(ConstantsAdmin.FORMAT_UPDATE_USER);
					result = false;
				}
				break;
			case ConstantsAdmin.EXIT:
				result = false;
				break;
			default:
				break;
			}
		} catch (Exception e) {

			log.error(e.getMessage());
			result = false;

		}

		return result;

	}

}
