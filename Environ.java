import java.util.*;;


public class Environ {

	static class Assoc{
		String id;
		int value;
		
		public Assoc(String id, int value){
			this.id=id;
			this.value=value;
		}
	}
	
	Environ up;
	ArrayList<Assoc> assocs;
	
	public Environ(){
		up = null;
		assocs = new ArrayList<Assoc>();
	}
	
	private Environ (Environ up){
		this.up = up;
		assocs = new ArrayList<Assoc>();
	}
	
	int find(String id) throws UndeclaredIdentifierException{
		Environ current = this;
		ArrayList<Assoc> teste = this.assocs;

		while (current != null ){
			for(Assoc assoc: teste)
				if(assoc.id.equals(id))
					return assoc.value;
			current = current.up;
			teste = current.getAssoc();
		}throw new UndeclaredIdentifierException(id);
	}
	
	public ArrayList<Assoc> getAssoc (){
		return assocs;
	}
	
	Environ beginScope(){
		return new Environ(this);
	}
	
	Environ endScope(){
		return up;
	}
	
	void assoc(String id, int value) throws DuplicateIdentifierException{
			for (Assoc assoc: assocs)
				if(assoc.id.equals(id))
					throw new DuplicateIdentifierException(id);
			assocs.add(new Assoc(id,value));
	}
}



