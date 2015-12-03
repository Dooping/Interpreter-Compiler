package exceptions;

public class TypeErrorException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String id;
	
	public TypeErrorException(String id){
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}

}