package Values;

import java.util.Map;
import java.util.TreeMap;



public class RecordValue implements IValue{
	
	private Map<String, IValue> values = new TreeMap<String, IValue>();
	
	
	public RecordValue (Map<String, IValue> v){
		values = v;
	}
	
	public Map<String, IValue> getValues(){
		return values;
	}
	
	public String toString(){
		return  values.toString();
	}
}