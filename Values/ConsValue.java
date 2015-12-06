package Values;

public class ConsValue implements IListValue {
	
	IValue hd;
	IListValue tl;

	public ConsValue(IValue hd, IListValue tl) {
		this.hd = hd;
		this.tl = tl;
	}

	
	public IValue hd() {
		return hd;
	}

	
	public IListValue tl() {
		return tl;
	}
	
	
	public String toString(){
		return "("+hd.toString()+";"+tl.toString()+")";
	}

}
