public class ASTDiv implements ASTNode{
	
	ASTNode left, right;

	public int eval(Environ env) throws UndeclaredIdentifierException, DuplicateIdentifierException 
	{ 
		return left.eval(env) / right.eval(env); 
	}
	

     public ASTDiv(ASTNode l, ASTNode r)
     {
		left = l; right = r;
      }
   
        @Override
    	public String toString(){
    		return left.toString() +" / "+ right.toString();
    	}

		
		public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException  {
			left.compile(code, env);
	    	right.compile(code, env);
	    	code.emit_div();
		}
}