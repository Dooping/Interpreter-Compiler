
public class ASTOr implements ASTNode{

	ASTNode left, right;
	
	public int eval(Environ e) throws UndeclaredIdentifierException, DuplicateIdentifierException 
	{
		if(left.eval(e) == 0 && right.eval(e) == 0)
			return 0;
		return 1; 
	}
	
	 public ASTOr(ASTNode l, ASTNode r)
     {
		left = l; right = r;
      }
	
	 @Override
 	public String toString(){
 		return left.toString() +" || "+ right.toString();
 	}

	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException  {
		
	}
}