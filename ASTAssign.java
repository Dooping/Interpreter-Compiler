
public class ASTAssign implements ASTNode{
	
	ASTNode left, right;

	public ASTAssign(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException,
			DuplicateIdentifierException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type typeCheck(Environ<Type> env) throws TypeErrorException {
		// left RefType
		// right Type
		RefType reftype = (RefType) left.typeCheck(env);
		Type type = right.typeCheck(env);
		if(reftype.equals(type))
			return 
		return null;
	}

	@Override
	public void compile(CodeBlock code, CompilerFrame env)
			throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub
		
	}

}
