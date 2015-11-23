
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
		expr.compile(code, env);
		code.emit_CheckCastRefInt();
		code.emit_getFieldForRefInt();
	}
	
	 public String toString(){
	    	return "*"+expr.toString();
	    }

}
