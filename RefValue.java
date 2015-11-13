
public class RefValue implements IValue{
	IValue val;
	
	public RefValue(IValue val){
		this.val = val;
	}
	
	public IValue getValue() {
		return val;
	}
	
	public void setValue(IValue val) {
		this.val = val;
	}
	
	public String toString() {
		return "var("+val.toString()+")";
	}

}
