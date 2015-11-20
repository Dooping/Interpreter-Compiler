
public class ASTDesref  implements ASTNode{
	ASTNode expr;
	Type type;
	
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
		Type t =  expr.typeCheck(env);
		if(t instanceof RefType){
			RefType t1 = (RefType)t;
			type = t1.getType();
		}
			
			return type;
		//else
			//throw new TypeErrorException(null);
	}

	
	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException {
		//fazer o expr.compile
		//ver o tipo para o check cast
		//getfield
		
	}
	
	 public String toString(){
	    	return "*"+expr.toString();
	    }

}
