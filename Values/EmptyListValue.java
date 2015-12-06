package Values;

public class EmptyListValue implements IListValue {

	
	public IValue hd() {
		return null;
	}
	
	public IListValue tl() {
		return null;
	}
	
	
	public String toString(){
		return "[]";
	}

}
