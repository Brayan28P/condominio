package condominio.modulos.util.view.controller;

import org.apache.commons.codec.digest.DigestUtils;

public class Seguridad {

	public static String encriptar(String passsword) {
		String contrasenia=DigestUtils.sha256Hex(passsword);
		return contrasenia;

	}
}
