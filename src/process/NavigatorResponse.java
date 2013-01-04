package process;

/** 
	Clase del Objeto envoltura "Navigator Response"
	Usado para la comunicacion entre la UI y la clase "Navigator"
*/
public class NavigatorResponse {
	
	private String imagePath;
	private Boolean save;
	private Boolean previous;
	private Boolean next;
	private Boolean toEnd;
	private String stepExplanation;

	public NavigatorResponse(String imagePath, Boolean save, Boolean previous, Boolean next, Boolean toEnd, String stepExplanation) {
		this.imagePath = imagePath;
		this.save = save;
		this.previous = previous;
		this.next = next;
		this.toEnd = toEnd;
		this.stepExplanation = stepExplanation;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public Boolean getSave() {
		return save;
	}
	
	public void setSave(Boolean save) {
		this.save = save;
	}

	public Boolean getPrevious() {
		return previous;
	}

	public void setPrevious(Boolean previous) {
		this.previous = previous;
	}

	public Boolean getNext() {
		return next;
	}

	public void setNext(Boolean next) {
		this.next = next;
	}

	public Boolean getToEnd() {
		return toEnd;
	}

	public void setToEnd(Boolean toEnd) {
		this.toEnd = toEnd;
	}

	public String getStepExplanation() {
		return stepExplanation;
	}

	public void setStepExplanation(String stepExplanation) {
		this.stepExplanation = stepExplanation;
	}
	
}
