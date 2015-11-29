
public class ASTVar implements ASTNode{
	ASTNode expr;
	Type type;
	Type expType;

	public ASTVar(ASTNode expr) {
		this.expr = expr;
	}

	
	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException, DuplicateIdentifierException {
		IValue val = expr.eval(e);
		return new RefValue(val);
	}

	
	public Type typeCheck(Environ<Type> env) throws TypeErrorException {
		expType = expr.typeCheck(env);
		return type = new RefType(expType);
	}

	
	public void compile(CodeBlock code, CompilerFrame env) throws UndeclaredIdentifierException, DuplicateIdentifierException {
		code.comment("fazer um apontador ");
		if(expType instanceof RefType){
			code.emit_refClass();
			expr.compile(code, env);
			code.emit_putFieldRefClass();
		}
		else{
			code.emit_refInt();
			expr.compile(code, env);
			code.emit_putFieldRefInt();
		}
		

		
	}
	
	 public String toString(){
	    	return "var("+expr.toString()+")";
	    }

}
