
public class ASTDesref  implements ASTNode{
	ASTNode expr;

	@Override
	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException,
			DuplicateIdentifierException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type typeCheck(Environ<Type> env) throws TypeErrorException {
		
		if (t1 instanceof RefType)
			return expr.typeCheck(env);
		else
			throw new TypeErrorException(null);
	}

	@Override
	public void compile(CodeBlock code, CompilerFrame env)
			throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub
		
	}

}
