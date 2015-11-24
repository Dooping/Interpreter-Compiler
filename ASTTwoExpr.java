
public class ASTTwoExpr implements ASTNode{
	
	ASTNode left, right;
	IValue l, r;
	
	public ASTTwoExpr(ASTNode l, ASTNode r){
		this.left = l;
		this.right = r;
	}

	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException,DuplicateIdentifierException {
		l = left.eval(e);
		r = right.eval(e);
		//TODO: returnar o que?
		return null;
	}

	public Type typeCheck(Environ<Type> env) throws TypeErrorException {
		// TODO: typeCheck de qual?!
		return null;
	}

	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub
		
	}
	
	public String toString(){
		return left.toString() + "=" +  " " + right.toString();
	}

}
