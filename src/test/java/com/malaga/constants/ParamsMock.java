package com.malaga.constants;

import java.util.ArrayList;
import java.util.List;

import com.malaga.dto.UserDTO;
import com.malaga.entity.Account;
import com.malaga.entity.User;

/**
 * Account repository
 * 
 * @author Daniel González Ortegón
 * @date 11/10/2019
 */
public class ParamsMock {

	public static Account ACCOUNT_ENTITY;
	public static Account ACCOUNT_ENTITY_1;

	public static List<User> USERS_ENTITY;

	public static User USER_ENTITY;
	public static User USER_ENTITY_1;

	public static List<UserDTO> users;

	public static UserDTO USER_EXAMPLE;
	public static UserDTO USER_EXAMPLE_1;

	static {

		USERS_ENTITY = new ArrayList<>();
		users = new ArrayList<>();

		USER_EXAMPLE = new UserDTO();
		USER_EXAMPLE.setUsername("Dani");
		USER_EXAMPLE.setLastname("lastName");
		USER_EXAMPLE.setEmail("email@com.com");
		USER_EXAMPLE.setEnabled(Boolean.TRUE);

		USER_EXAMPLE_1 = new UserDTO();
		USER_EXAMPLE_1.setUsername("Javier");
		USER_EXAMPLE_1.setLastname("lastName");
		USER_EXAMPLE_1.setEmail("email_1@com.com");
		USER_EXAMPLE_1.setEnabled(Boolean.TRUE);

		USER_ENTITY = new User();
		USER_ENTITY.setUsername("Dani");
		USER_ENTITY.setLastname("lastName");
		USER_ENTITY.setEmail("email@com.com");
		USER_ENTITY.setEnabled(Boolean.TRUE);
		USER_ENTITY.setAccountsUser(null);

		USER_ENTITY_1 = new User();
		USER_ENTITY_1.setUsername("Javier");
		USER_ENTITY_1.setLastname("lastName");
		USER_ENTITY_1.setEmail("email_1@com.com");
		USER_ENTITY_1.setEnabled(Boolean.TRUE);
		USER_ENTITY_1.setAccountsUser(null);

		ACCOUNT_ENTITY = new Account();
		ACCOUNT_ENTITY.setIban("ES06 3939 3939");

		ACCOUNT_ENTITY_1 = new Account();
		ACCOUNT_ENTITY_1.setIban("ES06 777 777 ");

		users.add(USER_EXAMPLE);
		USERS_ENTITY.add(USER_ENTITY);

	}

}
