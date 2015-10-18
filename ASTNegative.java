
public class ASTNegative implements ASTNode {

	ASTNode num;
	
	public int eval(Environ e) throws UndeclaredIdentifierException, DuplicateIdentifierException 
	{ 
		return -num.eval(e); 
	}

    public ASTNegative(ASTNode num)
    {
    	this.num = num;
     }
        
    @Override
    public String toString(){
    	return "-"+num.toString();
    }

	
	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException  {
		num.compile(code, env);
		code.emit_push(-1);
		code.emit_mul();
	}

}
