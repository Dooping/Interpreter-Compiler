package Types;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import main.Binding;



public class RecordType implements Type{
	
	private ArrayList<String> ids;
	private ArrayList<Type> types;

	public RecordType (ArrayList<Type> t, ArrayList<String> i){
		this.types = t;
		this.ids = i;
	}
	
	public ArrayList<Type> getTypes(){
		return types;
	}
	
	public ArrayList<String> getIds(){
		return ids;
	}

	
	public String toString(){
		return "Record";
	}
	
}