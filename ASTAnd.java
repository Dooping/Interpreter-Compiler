
public class ASTAnd implements ASTNode{

	ASTNode left, right;
	
	public int eval(Environ e) throws UndeclaredIdentifierException, DuplicateIdentifierException 
	{
		if(left.eval(e) == 1 && right.eval(e) == 1)
			return 1;
		return 0; 
	}
	
	 public ASTAnd(ASTNode l, ASTNode r)
     {
		left = l; right = r;
      }
	
	 @Override
 	public String toString(){
 		return left.toString() +" && "+ right.toString();
 	}

	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException  {
	}

}