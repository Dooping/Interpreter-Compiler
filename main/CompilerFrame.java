package main;



import java.util.*;

public class CompilerFrame {
	static int lastId = -1;
	
	//counter
	int instanceCounter = 0;
    public static int counter = 0;
	private int type;
	private CompilerFrame up;
	private ArrayList<String> ids;
	private ArrayList<String> types;
	
		
	public CompilerFrame(){
		instanceCounter = counter;
	    counter++;
		this.type = instanceCounter;
		up = null;
		ids = new ArrayList<String>();
		types = new ArrayList<String>();
	}

	public CompilerFrame (CompilerFrame up){
		instanceCounter = counter;
	    counter++;
		this.type = instanceCounter;
		this.up = up;
		ids = new ArrayList<String>();
		types = new ArrayList<String>();
	}

	public boolean hasAncestor (){
		return up != null;
	}
	
	public boolean containsId (String id){
		for(String num: ids){
			if(num.equals(id)) return true;
		}
		return false;
	}
	
	public int getType(){
		return type;
	}
	
	public CompilerFrame getAncestor(){
		return up;
	}
	
	public CompilerFrame beginScope(){
		return new CompilerFrame(this);
	}
	
	public void assoc(String id){
		if(!containsId(id)){
			ids.add(id);
		}
	}
	
	public void assocType(String t){
		types.add(t);
	}
	
	public ArrayList<String> getTypes(){
		return types;
	}
		
	public ArrayList<String> getAssoc (){
		return ids;
	}
	
}
