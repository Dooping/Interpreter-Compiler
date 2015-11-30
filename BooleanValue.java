package Values;


public class BooleanValue implements IValue {
	public boolean val;
	
	public BooleanValue(boolean val) {
		this.val = val;
	}
	
	public boolean getValue() {
		return val;
	}
	
	public String toString() {
		return Boolean.toString(val);
	}
}
