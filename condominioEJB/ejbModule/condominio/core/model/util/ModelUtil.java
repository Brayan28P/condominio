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


	
}

