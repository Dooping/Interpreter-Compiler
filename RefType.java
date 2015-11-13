
public class RefType implements Type{
	final Type type;

	public RefType(Type type) {
		this.type = type;
	}
	
	public String toString(){
		return "";
	}
	
	public Type getType(){
		return type;
	}
	
	@Override
	public boolean equals(Object o){
		if (o instanceof RefType){
			Type other = ((RefType) o).getType();
			return this.type.equals(other);
		}
		else
			throw new TypeErrorException(null);
	}

}
