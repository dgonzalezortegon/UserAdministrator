package com.malaga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.malaga.constants.ConstantsAdmin;
import com.malaga.dto.UserDTO;
import com.malaga.exceptions.AdministratorException;
import com.malaga.service.UserService;
import com.malaga.utils.ValidatorUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommandControllerImpl {

	@Autowired
	UserService userService;

	@Autowired
	Gson gson;

	public String login(String user, String pass) {

		log.info("Login\n");
		return "OK";
	}

	public List<UserDTO> findAll() throws AdministratorException {
		log.debug("findAll");

		return userService.findAll();
	}

	public UserDTO create(String user) throws AdministratorException {

		UserDTO userDto = gson.fromJson(ValidatorUtils.parserStringToDTO(user), UserDTO.class);

		return userService.create(userDto);
	}

	public UserDTO update(UserDTO user) throws AdministratorException {
		return null;
	}

	public UserDTO findByUsername(String idUser) throws AdministratorException {
		return null;
	}

	public void delete(UserDTO user) throws AdministratorException {

	}

	public String executeCommand(String args[]) {

		String result = null;
		try {
		switch (args[0]) {
		case ConstantsAdmin.AUTH:
			result = login(args[1], args[2]);

			break;

		default:
			break;
		}
		
		} catch (Exception e) {

			log.error(e.getMessage());
			log.error( "Error {} \n",args[0]);
			result = "Error ";

		}

		return result;

	}

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
			case ConstantsAdmin.EXIT:
				result = false;
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
