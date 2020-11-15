package haagahelia.fi.ProjectManagement.system;


public class NotEnoughBudgetException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public NotEnoughBudgetException() {
	}
	public NotEnoughBudgetException(String message) {
	super(message);
	}
	public NotEnoughBudgetException(String message, Throwable cause) {
	super(message, cause);
	}
	public NotEnoughBudgetException(Throwable cause) {
	super(cause);
	}

}
