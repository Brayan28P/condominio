package condominio.core.model.util; 

public class ModelUtil {
//
	public static boolean isEmpty(String cadena){
		if(cadena==null||cadena.length()==0)
			return true;
		return false;
	}
	public static boolean isEmptyNumber(long numeroID){
		if(numeroID==0)
			return true;
		return false;
	}

	public static  boolean validarCedula(String cedula) {
		boolean cedulaCorrecta = false;
		try {
			if (cedula.equals("0000000000")) {
				return false;
			}

			if (cedula.length() == 10)  
			{
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula.substring(9, 10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula.length() - 1); i++) {
						digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}

					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					} else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
				cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {
			err.printStackTrace();
			cedulaCorrecta = false;
		} 
		return cedulaCorrecta;
	}
	
}

