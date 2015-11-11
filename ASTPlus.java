public class ASTPlus implements ASTNode {

ASTNode left, right;

	public int eval(Environ env) throws UndeclaredIdentifierException, DuplicateIdentifierException 
	{ 
		return left.eval(env) + right.eval(env); 
	}
	
	/*public IValue eval(Environ<IValue> env) throws UndeclaredIdentifierException, DuplicateIdentifierException 
	{ 
		return new IntegerValue(((IntegerValue)left.eval(env)).getValue()+((IntegerValue)right.eval(env)).getValue()); 
	}*/
	
	public Type typeCheck(Environ<Type> env){
		Type t1 = left.typeCheck(env);
		Type t2 = right.typeCheck(env);
		if (t1==IntType.value && t2==IntType.value)
			return IntType.value;
		else
			throw new TypeErrorException();
	}

    public ASTPlus(ASTNode l, ASTNode r)
    {
		left = l; right = r;
     }
        
    @Override
    public String toString(){
    	return left.toString() +" + "+ right.toString();
    }
    
    public void compile( CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException{
    	left.compile(code, env);
    	right.compile(code, env);
    	code.emit_add();
    }

}

