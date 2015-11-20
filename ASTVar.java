
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
		//return type;
	}

	
	public void compile(CodeBlock code, CompilerFrame env) throws UndeclaredIdentifierException, DuplicateIdentifierException {
		//code.emitVarRef(type)
		//para fazer as 4 expressoes iniciais: new_ref, dup, inv...
		//exp.compile
		//code.emitPutField(code.RefClassOfType(type), "v",type)
		//code.RefClassOfType(type) -> dado um tipo, diz a class
		
		
	}
	
	 public String toString(){
	    	return "var("+expr.toString()+")";
	    }

}
