package Values;

public class ConsValue implements IListValue {
	
	IValue hd;
	IListValue tl;

	public ConsValue(IValue hd, IListValue tl) {
		this.hd = hd;
		this.tl = tl;
	}

	@Override
	public IValue hd() {
		return hd;
	}

	@Override
	public IListValue tl() {
		return tl;
	}
	
	@Override
	public String toString(){
		return hd.toString();
	}

}
