public class ASTBool implements ASTNode {

	boolean val;

	public IValue eval(Environ<IValue> env) 
	{ 
		return new BooleanValue(val); 
	}
	
	public Type typeCheck(Environ<Type> env) throws TypeErrorException{
		return BoolType.value;
	}

    public ASTBool(boolean n)
    {
        	val = n;
     }
        
    @Override
    public String toString(){
    	return Boolean.toString(val);
    }

	
	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException  {
		//code.emit_push(val);
	}

}
