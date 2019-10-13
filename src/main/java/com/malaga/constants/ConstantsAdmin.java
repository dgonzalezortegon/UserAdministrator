package com.malaga.constants;

public class ConstantsAdmin {

	// COMMANDS
	public static final String EXIT = "quit";
	public static final String INIT = "Init";
	public static final String AUTH = "login";
	public static final String CREATE_USER = "create";
	public static final String UPDATE_USER = "update";
	public static final String DELETE_USER = "delete";
	public static final String GET_USER = "get";
	public static final String ALL_USER = "all";
	public static final String CREATE_ACCOUNT = "newiban";
	public static final String HELP = "help";
	public static final String WHO = "whoami";

	// FORMAT COMMANDS
	public static final String FORMAT_CREATE_USER = "create -json {username:'userTest',password:'pass',lastname:'test',email:'test@mail.com'}\n";

	public static final String FORMAT_CREATE_ACCOUNT = "newiban username iban\n";

	public static final String FORMAT_UPDATE_USER = "update userTest -json {password:'hola',lastname:'test',email:'test@mail.com'}\n";

	public static final String FORMAT_DELETE_USER = "delete username\n";

	public static final String FORMAT_GET_USER = "get username\n";

	public static final String FORMAT_ALL_USERS = "all\n";

	public static final String FORMAT_HELP = "\nCreate an User (username is case sensitive) --> " + FORMAT_CREATE_USER + "Update an user --> "
			+ FORMAT_UPDATE_USER + "Delete an user --> " + FORMAT_DELETE_USER + "Get an User --> " + FORMAT_GET_USER
			+ "All users --> " + FORMAT_ALL_USERS + "Create a new Account --> " + FORMAT_CREATE_ACCOUNT + "\n";

	// MESSAGES
	public static final String WELCOME = "Welcome to Users Administrator\n";
	public static final String BYE = "Thanks, Bye\n";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_IBAN = "iban";
	public static final String KEY = "password";

	// ROLES
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

}
