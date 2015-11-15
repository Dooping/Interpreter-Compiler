import java.util.*;;


public class Environ<T> {

	static class Assoc{
		String id;
		IValue value;
		Type type;
		
		public Assoc(String id, IValue value, Type type){
			this.id=id;
			this.value=value;
			this.type=type;
		}
	}
	
	Environ<T> up;
	ArrayList<Assoc> assocs;
	
	public Environ(){
		up = null;
		assocs = new ArrayList<Assoc>();
	}
	
	private Environ (Environ<T> up){
		this.up = up;
		assocs = new ArrayList<Assoc>();
	}
	
	IValue find(String id) throws UndeclaredIdentifierException{
		Environ<T> current = this;
		ArrayList<Assoc> teste = this.assocs;

		while (current != null ){
			for(Assoc assoc: teste)
				if(assoc.id.equals(id))
					return assoc.value;
			current = current.up;
			teste = current.getAssoc();
		}throw new UndeclaredIdentifierException(id);
	}
	/*
	Type findType(String id) throws UndeclaredIdentifierException{
		Environ<T> current = this;
		ArrayList<Assoc> teste = this.assocs;

		while (current != null ){
			for(Assoc assoc: teste)
				if(assoc.id.equals(id))
					return assoc.
			current = current.up;
			teste = current.getAssoc();
		}throw new UndeclaredIdentifierException(id);
	}*/
	
	public ArrayList<Assoc> getAssoc (){
		return assocs;
	}
	
	Environ<T> beginScope(){
		return new Environ<T>(this);
	}
	
	Environ<T> endScope(){
		return up;
	}
	
	void assoc(String id, IValue value, Type type) throws DuplicateIdentifierException{
			for (Assoc assoc: assocs)
				if(assoc.id.equals(id))
					throw new DuplicateIdentifierException(id);
			assocs.add(new Assoc(id,value,type));
	}
}



