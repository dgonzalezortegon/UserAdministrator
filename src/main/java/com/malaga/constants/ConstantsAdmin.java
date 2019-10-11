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

	public static final String FORMAT_CREATE_USER = "create username lastname email";

	public static final String FORMAT_CREATE_ACCOUNT = "newiban username iban";

	public static final String FORMAT_UPDATE_USER = "update username email";

	public static final String FORMAT_DELETE_USER = "delete username";

	public static final String FORMAT_GET_USER = "get username";

	public static final String FORMAT_HELP = "Create an User --> " + FORMAT_CREATE_USER + "\n" + "Update an user --> "
			+ FORMAT_UPDATE_USER + "\n" + "Delete an user" + FORMAT_DELETE_USER + "\n" + "Get an User" + FORMAT_GET_USER
			+ "\n" + "Create a new Account --> " + FORMAT_CREATE_ACCOUNT + "\n";

	// MESSAGES
	public static final String WELCOME = "Welcome to Users Administrator\n";
	public static final String BYE = "Thanks, Bye\n";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_IBAN = "iban";

}
