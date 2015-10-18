public class ASTNum implements ASTNode {

int val;

	public int eval(Environ e) 
	{ 
		return val; 
	}

    public ASTNum(int n)
    {
        	val = n;
     }
        
    @Override
    public String toString(){
    	return Integer.toString(val);
    }

	
	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException  {
		code.emit_push(val);
	}

}

