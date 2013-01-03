package util;

/**
	Clase Strings que contiene las explicaciones
	de los pasos realizados para la obtención de
	la nueva imagen.
*/
public class Strings {

	public static String getExplanation(int index) {
		String result = "";

		if(index == 1) {
			result = "Texto para paso 1";
		} else if(index == 2) {
			result = "Texto para paso 2";
		} else if(index == 3) {
			result = "Texto para paso 3";
		} else if(index == 4) {
			result = "Texto para paso 4";
		} else if(index == 5) {
			result = "Texto para paso 5";
		} else {
			result = "Texto para paso 6";
		}

		return result;
	}
}
