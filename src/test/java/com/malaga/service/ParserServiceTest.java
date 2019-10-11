package com.malaga.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.malaga.constants.ParamsMock;
import com.malaga.controller.CommandControllerImpl;
import com.malaga.dto.UserDTO;
import com.malaga.utils.ValidatorUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * JUnits using Injection of Services
 * 
 * SpringBootTest
 *
 */
@Slf4j
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserServiceTest {

	@Autowired
	CommandControllerImpl command;

	@Autowired
	Gson gson;

	@Autowired
	AuthService authService;

	@Test
	public void testParser() throws Exception {

		String json = "{username:'Dani',lastname:'lastName',email:'email@com.com',enabled:'true',admin:'false'}";

		// Authentication First
		authService.authenticated("admin", "password");

		UserDTO userDto = gson.fromJson(ValidatorUtils.parserStringToDTO(json), UserDTO.class);

		Assert.assertNotNull(userDto);
	}

	@Test
	public void testJson() throws Exception {

		String user = gson.toJson(ParamsMock.USER_ENTITY);

		Assert.assertNotNull(user);
	}

}
