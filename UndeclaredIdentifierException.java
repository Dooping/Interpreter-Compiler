package exceptions;

public class UndeclaredIdentifierException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String id;
	
	public UndeclaredIdentifierException(String id){
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
}
