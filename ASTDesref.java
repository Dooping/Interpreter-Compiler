
public class ASTDesref  implements ASTNode{
	ASTNode expr;
	
	public ASTDesref(ASTNode expr) {
		this.expr = expr;
	}

	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException,DuplicateIdentifierException {
		RefValue r = (RefValue) expr.eval(e);
		return r.getValue();
	}

	
	public Type typeCheck(Environ<Type> env) throws TypeErrorException {
		RefType t = (RefType) expr.typeCheck(env);
			return t.getType();
	}

	
	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub
		
	}
	
	 public String toString(){
	    	return "*"+expr.toString();
	    }

}
