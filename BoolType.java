package Types;


public class BoolType implements Type{
	
	public final static BoolType value = new BoolType();
	
	private BoolType(){}
	
	public String toString(){
		return "Bool";
	}
}
