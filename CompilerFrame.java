import java.util.*;



public class CompilerFrame {
	static int lastId = -1;
	
	//counter
	int instanceCounter = 0;
    static int counter = 0;
	private int type;
	private CompilerFrame up;
	private ArrayList<String> ids;
	
		
	public CompilerFrame(){
		instanceCounter = counter;
	    counter++;
		this.type = instanceCounter;
		up = null;
		ids = new ArrayList<String>();
	}

	public CompilerFrame (CompilerFrame up){
		instanceCounter = counter;
	    counter++;
		this.type = instanceCounter;
		this.up = up;
		ids = new ArrayList<String>();
	}

	public boolean hasAncestor (){
		return up != null;
	}
	
	//não é para ir a procura em cima, é so no seu env
	public boolean containsId (String id){
		for(String num: ids){
			if(num.equals(id)) return true;
		}
		return false;
		/*
			for(Assoc assoc: values)
				if(assoc.id.equals(id))
					return true;
		return false;*/
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
	
	void assoc(String id){
		if(!containsId(id)){
			ids.add(id);
		}
	}
	
	/*
	int find(String id) throws UndeclaredIdentifierException{
		CompilerFrame current = this;
		ArrayList<Assoc> teste = this.values;
		while (current != null ){
			for(Assoc assoc: teste)
				if(assoc.id.equals(id))
					return assoc.value;
			current = current.up;
			teste = current.getAssoc();
		}throw new UndeclaredIdentifierException(id);
	}
	*/
	public ArrayList<String> getAssoc (){
		return ids;
	}
	
	/*
	void assoc(String id, int value) throws DuplicateIdentifierException{
		for (Assoc assoc: values)
			if(assoc.id.equals(id))
				throw new DuplicateIdentifierException(id);
		values.add(new Assoc(id,value));
}*/
}
