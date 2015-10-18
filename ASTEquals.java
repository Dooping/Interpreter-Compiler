
public class ASTEquals implements ASTNode{

	ASTNode left, right;
	
	public int eval(Environ e) throws UndeclaredIdentifierException, DuplicateIdentifierException 
	{
		if(left.eval(e) == right.eval(e))
			return 1;
		return 0; 
	}
	
	 public ASTEquals(ASTNode l, ASTNode r)
     {
		left = l; right = r;
      }
	
	 @Override
 	public String toString(){
 		return left.toString() +" == "+ right.toString();
 	}

	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException  {
	}

}
