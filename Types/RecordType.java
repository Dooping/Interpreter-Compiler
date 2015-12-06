package Types;

import java.util.ArrayList;



public class RecordType implements Type{
	
	ArrayList<Type> types;
	
	public RecordType (ArrayList<Type> t){
		this.types = t;
	}
	
	public ArrayList<Type> getTypes(){
		return types;
	}
	
	public String toString(){
		return "Record";
	}
	
}