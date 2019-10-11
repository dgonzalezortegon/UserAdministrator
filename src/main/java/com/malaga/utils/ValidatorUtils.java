package com.malaga.utils;

import java.util.regex.Pattern;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.malaga.constants.ConstantsAdmin;

public final class ValidatorUtils {

	public static boolean validaCampo(String campo, String value) {
		switch (campo.toLowerCase()) {
		case ConstantsAdmin.FIELD_EMAIL:
			return validarEmail(value);
		case ConstantsAdmin.FIELD_IBAN:
			return validarTexto(value, 50);

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

	public static JsonElement parserStringToDTO(String user) {

		JsonParser parser = new JsonParser();
		JsonElement json = parser.parse(user);

		return json;

	}

}
