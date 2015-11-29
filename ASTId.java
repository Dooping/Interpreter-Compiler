public class ASTId implements ASTNode{
	
	String id;
	Type type;

	public ASTId (String id){
		this.id = id;
	}
	
	public IValue eval(Environ<IValue> env) throws UndeclaredIdentifierException, DuplicateIdentifierException 
	{ 
		return env.find(id);
	}
	
	public Type typeCheck(Environ<Type> env) throws TypeErrorException{
		//System.out.println(id);
		try {
			type = env.findType(id);
		} catch (UndeclaredIdentifierException e) {
			//System.out.println("NULL");
		}
		
		return type;
	}
	


	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException {
		
		code.comment("get a value");
		code.loadFrame(env);
		CompilerFrame frame = env;
		while(frame.hasAncestor() && !frame.containsId(this.id)){
			//parte do get field para se subir ans frames ate atingir a variavel pretendida
			code.getFrame(frame.getType(), frame.getAncestor().getType());
			frame = frame.getAncestor();
		}
		
		if(!frame.containsId(this.id))
			throw new UndeclaredIdentifierException(id);
		else{
			String t ="I";
			
			if(type instanceof RefType)
				t = "Lref_int";
			
			code.emit_getfield(frame.getType(), this.id, t);
		}
			
	}
	
	public String toString(){
		return id;
	}

}
