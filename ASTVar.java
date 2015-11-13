
public class ASTVar implements ASTNode{
	ASTNode expr;

	public ASTVar(ASTNode expr) {
		this.expr = expr;
	}

	@Override
	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException,
			DuplicateIdentifierException {
		IValue val = expr.eval(e);
		return new RefValue(val);
	}

	@Override
	public Type typeCheck(Environ<Type> env) throws TypeErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void compile(CodeBlock code, CompilerFrame env)
			throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub
		
	}

}
