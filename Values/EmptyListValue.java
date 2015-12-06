package Values;

public class EmptyListValue implements IListValue {

	@Override
	public IValue hd() {
		return null;
	}

	@Override
	public IListValue tl() {
		return null;
	}
	
	@Override
	public String toString(){
		return "[]";
	}

}
