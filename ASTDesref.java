
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
		//temos de fazer o check type ao que está dentro do Id, não ao id
		//o ASTId nunca sera do tipo RefType!!!
		//RefType t1 = (RefType) expr.typeCheck(env);
		//if (t1 instanceof RefType)
		//System.out.println(expr.getClass().getName());
		RefType t = (RefType) expr.typeCheck(env);
			return t.getType();
		//else
			//throw new TypeErrorException(null);
	}

	
	public void compile(CodeBlock code, CompilerFrame env)
			throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub
		
	}
	
	 public String toString(){
	    	return "*"+expr.toString();
	    }

}
