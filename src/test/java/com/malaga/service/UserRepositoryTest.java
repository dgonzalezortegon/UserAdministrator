package com.malaga.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.malaga.constants.ParamsMock;
import com.malaga.entity.User;
import com.malaga.repository.UserDetailsRepository;

/**
 * Account repository
 * 
 * @author Daniel González Ortegón
 * @date 11/10/2019
 */

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserDetailsRepository userRepository;

	@Test
	public void createUser() throws Exception {
		User user = userRepository.save(ParamsMock.USER_ENTITY);

		Assert.assertNotNull(user.getId());
	}

	@Test
	public void findUsers() throws Exception {

		userRepository.save(ParamsMock.USER_ENTITY);
		userRepository.save(ParamsMock.USER_ENTITY_1);

		Assert.assertEquals(2, userRepository.findAll().size());
	}

	@Test
	public void updateUser() throws Exception {

		User user = userRepository.save(ParamsMock.USER_ENTITY);
		user.setEnabled(Boolean.FALSE);

		user = userRepository.save(user);

		Assert.assertEquals(Boolean.FALSE, user.isEnabled());

	}

}
