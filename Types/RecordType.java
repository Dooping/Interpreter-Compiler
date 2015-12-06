package Types;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import main.Binding;



public class RecordType implements Type{
	

	private Map<String, Type> types = new TreeMap<String, Type>();

	public RecordType (Map<String, Type> t){
		this.types = t;
	}
	
	public Map<String, Type> getTypes(){
		return types;
	}

	
	public String toString(){
		return "Record";
	}
	
}