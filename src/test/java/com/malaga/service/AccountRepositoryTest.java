package com.malaga.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.malaga.constants.ParamsMock;
import com.malaga.entity.Account;
import com.malaga.entity.User;
import com.malaga.repository.AccountRepository;
import com.malaga.repository.UserDetailsRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Account repository
 * 
 * @author Daniel González Ortegón
 * @date 11/10/2019
 */

@Slf4j
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserDetailsRepository userRepository;

	@Before
	public void init() {

	}

	@Test
	public void createAccount() throws Exception {

		User user = userRepository.save(ParamsMock.USER_ENTITY_1);

		ParamsMock.ACCOUNT_ENTITY.setUser(user);
		Account ccc = accountRepository.save(ParamsMock.ACCOUNT_ENTITY);

		log.debug("CCC Created");

		Assert.assertNotNull(ccc.getId());

		Assert.assertEquals(ccc.getUser().getId(), ParamsMock.USER_ENTITY_1.getId());
	}

	@Test
	public void findAccountsByUser() throws Exception {

		User user = userRepository.save(ParamsMock.USER_ENTITY);

		ParamsMock.ACCOUNT_ENTITY.setUser(user);
		ParamsMock.ACCOUNT_ENTITY_1.setUser(user);
		accountRepository.save(ParamsMock.ACCOUNT_ENTITY);
		Account ccc_1 = accountRepository.save(ParamsMock.ACCOUNT_ENTITY_1);

		log.debug("CCC Created");

		Assert.assertEquals(ccc_1.getUser(), ParamsMock.USER_ENTITY);

		Assert.assertEquals(2, accountRepository.findAllByUserId(ccc_1.getUser().getId()).size());
	}

}
