package exceptions;


public class ExecutionErrorException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String id;
	
	public ExecutionErrorException(String id){
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}

}