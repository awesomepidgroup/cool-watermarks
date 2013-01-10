package util;

/**
 * Class which contains the explanations for the
 * different steps in our program execution.
 * 
 * @author	Camacho Sosa, José Manuel
 * @author	Muñoz Ríos, Gabriel
 * @author	Pozo Nuñez, José Antonio
 * @version	1.0, 10 Jan 2013
 */
public class Strings {

	/**
	 * Static method to get the explanation by the
	 * index of the step.
	 * 
	 * @param	index	index of the step
	 * @return			a string which contains the
	 * 					appropriate explanation
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
			result = "The next step is a simple binarization. The only strange aspect is that " +
						"the choice of the threshold is automatic. Uses an algorithm that " +
						"selects a threshold associated with the composite average of the " +
						"pixel values​​.";
		} else if(index == 5){
			result = "Texto para paso 6";
		} else {
			result = "This is the final result.";
		}
		
		return result;
	}
	
}
