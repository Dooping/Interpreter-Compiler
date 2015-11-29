
public class ASTIf implements ASTNode{

	ASTNode clause, bodyThen, bodyElse;
	boolean toS;
	
	public ASTIf(ASTNode clause, ASTNode bodyT, ASTNode bodyE){
		this.clause = clause;
		this.bodyThen = bodyT;
		this.bodyElse = bodyE;
		toS = false;
	}

	
	
	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException,DuplicateIdentifierException {
		BooleanValue v =  (BooleanValue) clause.eval(e);
		if(v.val){
			toS = true;
			return bodyThen.eval(e);
		}
		else return bodyElse.eval(e);

	}

	public Type typeCheck(Environ<Type> env) throws TypeErrorException {
		Type c = clause.typeCheck(env);
		Type t = bodyThen.typeCheck(env);
		Type e = bodyElse.typeCheck(env);
		if(t.equals(e))
			return bodyThen.typeCheck(env);
		else throw new TypeErrorException("Exepecting equal types");

	}

	public void compile(CodeBlock code, CompilerFrame env)
			throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub
		
	}
	
	public String toString(){
		if(toS) return bodyThen.toString();
		return bodyElse.toString();
	}

}
