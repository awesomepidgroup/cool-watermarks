package process;

/** 
 * Class of the NavigatorResponse object. It is needed
 * to communicate the GUI with ImageMethods and
 * Navigator classes.
 * 
 * @author	Camacho Sosa, José Manuel
 * @author	Muñoz Ríos, Gabriel
 * @author	Pozo Nuñez, José Antonio
 * @version	1.0, 10 Jan 2013
*/
public class NavigatorResponse {
	
	/**
	 * Attribute which contains the image path.
	 */
	private String imagePath;
	
	/**
	 * Boolean attribute for enable the save button.
	 * True allows the click, false doesn't.
	 */
	private Boolean save;
	
	/**
	 * Boolean attribute for enable the previous button.
	 * True allows the click, false doesn't.
	 */
	private Boolean previous;
	
	/**
	 * Boolean attribute for enable the next button.
	 * True allows the click, false doesn't.
	 */
	private Boolean next;
	
	/**
	 * Boolean attribute for enable the toEnd button.
	 * True allows the click, false doesn't.
	 */
	private Boolean toEnd;
	
	/**
	 * Attribute which contains the explanation of the
	 * next step.
	 */
	private String stepExplanation;

	/**
	 * Default constructor.
	 * 
	 * @param	imagePath		the image path
	 * @param	save			the boolean value for the save button
	 * @param	previous		the boolean value for the previous button
	 * @param	next			the boolean value for the next button
	 * @param	toEnd			the boolean value for the toEnd button
	 * @param	stepExplanation	the string for the explanation of the next step
	 */
	public NavigatorResponse(String imagePath, Boolean save, Boolean previous, Boolean next, Boolean toEnd, String stepExplanation) {
		this.imagePath = imagePath;
		this.save = save;
		this.previous = previous;
		this.next = next;
		this.toEnd = toEnd;
		this.stepExplanation = stepExplanation;
	}

	/**
	 * Gets the value of the attribute imagePath.
	 * 
	 * @return	the value of the attribute imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Sets the value of the attribute imagePath.
	 * 
	 * @param	imagePath	the new value for the attribute imagePath
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	/**
	 * Gets the value of the attribute save.
	 * 
	 * @return	the value of the attribute save.
	 */
	public Boolean getSave() {
		return save;
	}
	
	/**
	 * Sets the value of the attribute save.
	 * 
	 * @param	save the new value for the attribute save.
	 */
	public void setSave(Boolean save) {
		this.save = save;
	}

	/**
	 * Gets the value of the attribute previous.
	 * 
	 * @return	the value of the attribute previous
	 */
	public Boolean getPrevious() {
		return previous;
	}

	/**
	 * Sets the value of the attribute previous.
	 * 
	 * @param	previous	the new value for the attribute previous
	 */
	public void setPrevious(Boolean previous) {
		this.previous = previous;
	}

	/**
	 * Gets the value of the attribute next.
	 * 
	 * @return	the value of the attribute next
	 */
	public Boolean getNext() {
		return next;
	}

	/**
	 * Sets the value of the attribute next.
	 * 
	 * @param	next	the new value of the attribute next
	 */
	public void setNext(Boolean next) {
		this.next = next;
	}

	/**
	 * Gets the value of the attribute toEnd.
	 * 
	 * @return	the value of the attribute toEnd
	 */
	public Boolean getToEnd() {
		return toEnd;
	}

	/**
	 * Sets the value of the attribute toEnd.
	 * 
	 * @param	toEnd	the new value of the attribute toEnd
	 */
	public void setToEnd(Boolean toEnd) {
		this.toEnd = toEnd;
	}

	/**
	 * Gets the value of the attribute stepExplanation.
	 * 
	 * @return	the value of the attribute stepExplanation
	 */
	public String getStepExplanation() {
		return stepExplanation;
	}

	/**
	 * Sets the value of the attribute stepExplanation.
	 * 
	 * @param	stepExplanation	the new value of the attribute stepExplanation
	 */
	public void setStepExplanation(String stepExplanation) {
		this.stepExplanation = stepExplanation;
	}
	
}
