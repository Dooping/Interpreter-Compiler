public class IntegerValue implements IValue {
	int val;
	
	public IntegerValue(int val) {
		this.val = val;
	}
	
	public int getValue() {
		return val;
	}
	
	public String toString() {
		return Integer.toString(val);
	}
}
