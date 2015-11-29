


public class ASTWhile implements ASTNode{
	
	ASTNode clause, body;
	
	public ASTWhile(ASTNode clause, ASTNode body){
		this.clause = clause;
		this.body = body;
	}

	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException,DuplicateIdentifierException {
		IValue i =  body.eval(e);
		BooleanValue v =  (BooleanValue) clause.eval(e);
		while(v.val){
			i = body.eval(e);
			v = (BooleanValue) clause.eval(e);
			System.out.println( i.toString());
		}
		
		return v;
	}

	public Type typeCheck(Environ<Type> env) throws TypeErrorException {
		Type t1 = clause.typeCheck(env);
		Type t2 = body.typeCheck(env);
		if( t1 == BoolType.value ) 
			return BoolType.value;
		else 
			throw new TypeErrorException("Using a non-boolean where a boolean was expected.");

	}

	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException {
		code.comment("fazer o while");
		int label = code.labelGenarator();
		code.emit_label(label);
		clause.compile(code, env);
		int label2 = code.labelGenarator();
		code.emit_ifeq(label2);
		body.compile(code, env);
		code.emit_goto(label);
		code.emit_label(label2);
	}
	
	public String toString(){
		return body.toString();
	}

}
