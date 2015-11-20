
public class ASTAssign implements ASTNode{
	
	ASTNode left, right;

	public ASTAssign(ASTNode left, ASTNode right) {
		//um id
		this.left = left;
		//um valor
		this.right = right;
	}

	
	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException,DuplicateIdentifierException {
		return null;
	}

	
	public Type typeCheck(Environ<Type> env) throws TypeErrorException {
		// left RefType
		// right Type
		RefType reftype = (RefType) left.typeCheck(env);
		Type type = right.typeCheck(env);
		if(reftype.equals(type))
			return null;
		return null;
	}

	
	public void compile(CodeBlock code, CompilerFrame env)
			throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub
		
	}

}
