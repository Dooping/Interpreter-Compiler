package Values;

import java.util.ArrayList;

import main.Binding;


public class RecordValue implements IValue{
	
	private ArrayList <Binding> values;
	
	public RecordValue (ArrayList <Binding> v){
		values = v;
	}
	
	public ArrayList<Binding> getBind(){
		return values;
	}
	
	public String toString(){
		return ""+ values.get(values.size()-1).toString();
	}
}