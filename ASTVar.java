
public class ASTVar implements ASTNode{
	ASTNode expr;

	public ASTVar(ASTNode expr) {
		this.expr = expr;
	}

	
	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException, DuplicateIdentifierException {
		IValue val = expr.eval(e);
		return new RefValue(val);
	}

	
	public Type typeCheck(Environ<Type> env) throws TypeErrorException {
		
		RefType t = new RefType(expr.typeCheck(env));
		
		//System.out.println(t.getType().toString());
		
		return t;
	}

	
	public void compile(CodeBlock code, CompilerFrame env)
			throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub
		
	}
	
	 public String toString(){
	    	return "var("+expr.toString()+")";
	    }

}
