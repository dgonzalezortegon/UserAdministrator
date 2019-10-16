package com.malaga.utils;

import java.util.regex.Pattern;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.malaga.constants.ConstantsAdmin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ValidatorUtils {

	/**
	 * Main to validate a field with the value
	 * 
	 * @param field
	 * @param value
	 * @return
	 */
	public static boolean validaCampo(String field, String value) {
		switch (field.toLowerCase()) {
		case ConstantsAdmin.FIELD_EMAIL:
			return validarEmail(value);
		case ConstantsAdmin.FIELD_IBAN:
			return validarIBAN(value);

		default:
			return true;

		}
	}

	public static boolean validarTexto(String campo, int size) {
		boolean resul = true;
		if (null != campo) {
			Pattern pattern = Pattern.compile("[^.#]+{0," + size + "}$");
			resul = pattern.matcher(campo).matches();
		}
		return resul;
	}

	public static boolean validarEmail(String email) {

		boolean resul = true;
		if (null != email) {

		}
		return resul;
	}

	/**
	 * Validate IBAN
	 * 
	 * @param campo iban
	 * @return true or false
	 */
	public static boolean validarIBAN(String campo) {
		boolean resul = true;
		if (null != campo) {
			Pattern pattern = Pattern.compile("^[A-Z]{2}(?:[ ]?[0-9]){18,20}$");
			resul = pattern.matcher(campo).matches();
		}
		return resul;
	}

	/**
	 * Utility to parse from String to JsonElement
	 * 
	 * @param user
	 * @return
	 */
	public static JsonElement parserStringToDTO(String user) {

		JsonParser parser = new JsonParser();
		JsonElement ele = null;
		try {
			ele = parser.parse(user);
		} catch (Exception e) {

			log.error(e.getMessage());

		}

		return ele;
	}

	public static boolean isValid(String[] args) {
		boolean valid = true;
		if (args != null && args.length == 0) {
			valid = false;
		}
		if (args != null && args.length == 1) {
			if (args[0].equals(System.lineSeparator()) || args[0].equals("")) {

				valid = false;
			}
		}
		return valid;

	}

	/**
	 * parse the Arguments
	 * 
	 * @param arg
	 * @return
	 */
	public static String[] parseArgs(String arg) {

		String[] args = null;
		String[] newArgs = null;
		String[] argsJson = arg.split("-json");

		// No contains the parameter -json
		if (argsJson != null && argsJson.length == 1) {
			newArgs = arg.split(" ");
		} else {
			if (argsJson != null) {
				args = argsJson[0].split(" ");

				newArgs = new String[args.length + 1];

				for (int i = 0; i < args.length; i++) {
					newArgs[i] = args[i].trim();
				}
				newArgs[args.length] = argsJson[1];
			}
		}

		return newArgs;

	}

	/**
	 * trim the spaces
	 * 
	 * @param args
	 * @return
	 */
	public static String[] trimCommands(String[] args) {

		String[] newArgs = null;

		if (args != null) {

			int gaps = 0;
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("")) {
					gaps++;
				}
			}
			newArgs = new String[args.length - gaps];

			int j = 0;
			for (int i = 0; i < args.length; i++) {
				if (!args[i].equals("")) {
					newArgs[j] = args[i].trim();
					++j;
				}
			}
		}

		return newArgs;

	}

}
