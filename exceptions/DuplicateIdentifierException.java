package exceptions;


public class DuplicateIdentifierException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String id;
	
	public DuplicateIdentifierException(String id){
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}

}
