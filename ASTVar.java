
public class ASTVar implements ASTNode{
	ASTNode expr;
	Type type;

	public ASTVar(ASTNode expr) {
		this.expr = expr;
	}

	
	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException, DuplicateIdentifierException {
		IValue val = expr.eval(e);
		return new RefValue(val);
	}

	
	public Type typeCheck(Environ<Type> env) throws TypeErrorException {	
		return type = new RefType(expr.typeCheck(env));
	}

	
	public void compile(CodeBlock code, CompilerFrame env) throws UndeclaredIdentifierException, DuplicateIdentifierException {
		code.emit_refClass();
		expr.compile(code, env);
		code.emit_putFieldRefClass();
		
	}
	
	 public String toString(){
	    	return "var("+expr.toString()+")";
	    }

}
