package Types;

import java.util.ArrayList;

import main.Binding;



public class RecordType implements Type{
	
	private ArrayList <Binding> types;
	
	public RecordType (ArrayList <Binding> t){
		this.types = t;
	}
	
	public ArrayList <Binding> getTypes(){
		return types;
	}

	
	public String toString(){
		return "Record";
	}
	
}