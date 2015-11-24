
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
		return i;
	}

	public Type typeCheck(Environ<Type> env) throws TypeErrorException {
		return clause.typeCheck(env);
	}

	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException {

		
	}
	
	public String toString(){
		//o string leva o que?
		return body.toString();
	}

}
