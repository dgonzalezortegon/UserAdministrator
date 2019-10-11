package com.malaga.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.malaga.constants.ParamsMock;
import com.malaga.dto.UserDTO;
import com.malaga.mapper.UserMapper;
import com.malaga.repository.UserDetailsRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * JUnit Test using Mocks
 */

@Slf4j
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@InjectMocks
	private UserService userService = new UserServiceImpl();

	@Mock
	private UserDetailsRepository userRepository;

	@Mock
	UserMapper mapperUser;

	@Test
	public void findAll() throws Exception {
		Mockito.when(userRepository.findAll()).thenReturn(ParamsMock.USERS_ENTITY);

		Mockito.when(mapperUser.toDTO(ParamsMock.USERS_ENTITY)).thenReturn(ParamsMock.users);

		List<UserDTO> listUsersDTO = userService.findAll();
		log.debug(listUsersDTO.toString());
		Assert.assertEquals(ParamsMock.users, listUsersDTO);
	}

}
