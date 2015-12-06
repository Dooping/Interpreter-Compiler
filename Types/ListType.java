package Types;

public class ListType implements Type {
	public final static ListType value = new ListType();
	
	private ListType(){}
	
	public String toString(){
		return "List";
	}
}