package util;

/**
 * Class which contains the explanations for the
 * different steps in our program execution.
*/
public class Strings {

	/**
	 * Static method to get the explanation by the index of the
	 * appropriate step.
	 * @param index
	 * Index of the step.
	 * @return	A string which contains the appropriate explanation.
	 */
	public static String getExplanation(int index) {
		String result = "";

		if(index == 0) {
			result = "Texto para paso 1";
		} else if(index == 1) {
			result = "Texto para paso 2";
		} else if(index == 2) {
			result = "Texto para paso 3";
		} else if(index == 3) {
			result = "Texto para paso 4";
		} else if(index == 4) {
			result = "Texto para paso 5";
		} else if(index == 5){
			result = "Texto para paso 6";
		} else {
			result = "Este es el resultado final";
		}
		
		return result;
	}
	
}
