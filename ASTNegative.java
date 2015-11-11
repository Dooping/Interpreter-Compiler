
public class ASTNegative implements ASTNode {

	ASTNode num;
	
	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException, DuplicateIdentifierException 
	{ 
		return new IntegerValue(- ((IntegerValue) num.eval(e)).getValue()); 
	}

	public Type typeCheck(Environ<Type> env) throws TypeErrorException{
		Type t1 = num.typeCheck(env);
		if (t1==IntType.value )
			return IntType.value;
		else
			throw new TypeErrorException(null);
	}
	
    public ASTNegative(ASTNode num)
    {
    	this.num = num;
     }
        
    @Override
    public String toString(){
    	return num.toString();
    }

	
	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException  {
		num.compile(code, env);
		code.emit_push(-1);
		code.emit_mul();
	}

}
