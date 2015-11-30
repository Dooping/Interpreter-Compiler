package exceptions;

public class NotEndingCycleException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String id;
	
	public NotEndingCycleException(String id){
		this.id = id;
	}
	
	public String toString(){
		return "infinte cycle";
	}
	
	public String getId() {
		return this.id;
	}

}
