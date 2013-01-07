package process;

/** 
 * Class of the NavigatorResponse object. It is needed
 * to communicate the GUI with ImageMethods and Navigator classes.
*/
public class NavigatorResponse {
	
	/**
	 * Attribute which contains the image path.
	 */
	private String imagePath;
	
	/**
	 * Boolean attribute which get the value for click the save button.
	 * True does allow the click, false does not it.
	 */
	private Boolean save;
	
	/**
	 * Boolean attribute which get the value for click the previous button.
	 * True does allow the click, false does not it.
	 */
	private Boolean previous;
	
	/**
	 * Boolean attribute which get the value for click the next button.
	 * True does allow the click, false does not it.
	 */
	private Boolean next;
	
	/**
	 * Boolean attribute which get the value for click the toEnd button.
	 * True does allow the click, false does not it
	 */
	private Boolean toEnd;
	
	/**
	 * Attribute which contains the explanation of the appropriate step.
	 */
	private String stepExplanation;

	/**
	 * Default constructor.
	 * 
	 * @param imagePath
	 * The image path.
	 * @param save
	 * The boolean value for the save button.
	 * @param previous
	 * The boolean value for the previous button.
	 * @param next
	 * The boolean value for the next button.
	 * @param toEnd
	 * The boolean value for the toEnd button.
	 * @param stepExplanation
	 * The string for the step explanation.
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
	 * @return the value of the attribute imagePath.
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * Sets the value of the attribute imagePath with the value of the @param.
	 * @param imagePath
	 * The new value for the attribute imagePath.
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	/**
	 * Gets the value of the attribute save.
	 * @return the value of the attribute save.
	 */
	public Boolean getSave() {
		return save;
	}
	
	/**
	 * Sets the value of the attribute save with the value of the @param.
	 * @param save
	 * The new value for the attribute save.
	 */
	public void setSave(Boolean save) {
		this.save = save;
	}

	/**
	 * Gets the value of the attribute previous.
	 * @return the value of the attribute previous.
	 */
	public Boolean getPrevious() {
		return previous;
	}

	/**
	 * Sets the value of the attribute previous with the value of the @param.
	 * @param previous
	 * the new value for the attribute previous.
	 */
	public void setPrevious(Boolean previous) {
		this.previous = previous;
	}

	/**
	 * Gets the value of the attribute next.
	 * @return the value of the attribute next.
	 */
	public Boolean getNext() {
		return next;
	}

	/**
	 * Sets the value of the attribute next with the value of the @param.
	 * @param next
	 * the new value of the attribute next.
	 */
	public void setNext(Boolean next) {
		this.next = next;
	}

	/**
	 * Gets the value of the attribute toEnd.
	 * @return the value of the attribute toEnd.
	 */
	public Boolean getToEnd() {
		return toEnd;
	}

	/**
	 * Sets the value of the attribute toEnd with the value of the @param.
	 * @param toEnd
	 * the new value of the attribute toEnd.
	 */
	public void setToEnd(Boolean toEnd) {
		this.toEnd = toEnd;
	}

	/**
	 * Gets the value of the attribute stepExplanation.
	 * @return the value of the attribute stepExplanation.
	 */
	public String getStepExplanation() {
		return stepExplanation;
	}

	/**
	 * Sets the value of the attribute stepExplanation with the value of the @param.
	 * @param stepExplanation
	 * the new value of the attribute stepExplanation.
	 */
	public void setStepExplanation(String stepExplanation) {
		this.stepExplanation = stepExplanation;
	}
	
}
