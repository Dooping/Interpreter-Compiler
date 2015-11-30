package Types;

public class IntType implements Type{
	
	public final static IntType value = new IntType();
	
	private IntType(){}
	
	public String toString(){
		return "Int";
	}
}
