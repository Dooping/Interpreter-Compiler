public class ASTId implements ASTNode{
	
	String id;

	public ASTId (String id){
		this.id = id;
	}
	
	public IValue eval(Environ<IValue> env) throws UndeclaredIdentifierException, DuplicateIdentifierException 
	{ 
		return env.find(id);
	}
	
	public Type typeCheck(Environ<Type> env) throws TypeErrorException{
		//System.out.println(id);
		Type t = null;
		try {
			t = env.findType(id);
		} catch (UndeclaredIdentifierException e) {
			//System.out.println("NULL");
		}
		
		return t;
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
		else
			code.emit_getfield(frame.getType(), this.id, "I");
	}
	
	public String toString(){
		return id;
	}

}
