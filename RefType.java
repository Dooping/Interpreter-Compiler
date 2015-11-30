package Types;

public class RefType implements Type{
	public final Type type;

	public RefType(Type type) {
		this.type = type;
	}
	
	public String toString(){
		return "Ref";
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
			return false;
	}

}
