package com.malaga.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.AssertionErrors;

import com.malaga.dto.UserDTO;

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
public class SecurityServiceTest {

	@Autowired
	UserService userService;

	@Autowired
	AuthService authService;

	/**
	 * Test ,to check the permission for the Administrator user
	 * @throws Exception
	 */
	@Test
	public void findAllPermitedForAdmin() throws Exception {

		// Authentication First
		authService.authenticated("admin", "password");

		// Restricted operation for Admin
		List<UserDTO> listUsersDTO = userService.findAll();
		log.debug(listUsersDTO.toString());
		Assert.assertNotNull(listUsersDTO);
	}

	/**
	 * Test ,to check the permission for the non-Administrator User
	 * @throws Exception
	 */
	@Test(expected = AccessDeniedException.class)
	public void findAllNotPermitedForUser() throws Exception {

		// Authentication First
		authService.authenticated("user", "password");

		// Restricted operation for Admin
		userService.findAll();

		AssertionErrors.fail("No Permited");
	}

}
