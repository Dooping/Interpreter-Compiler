package Values;

import java.util.ArrayList;

import Types.Type;

public class RecordValue implements IValue{
	
	ArrayList<IValue> values;
	
	public RecordValue (ArrayList<IValue> v){
		values = v;
	}
	
	public String toString(){
		return ""+ values.get(values.size()-1).toString();
	}
}