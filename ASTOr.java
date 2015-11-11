
public class ASTOr implements ASTNode{

	ASTNode left, right;
	
	public IValue eval(Environ<IValue> env) throws UndeclaredIdentifierException, DuplicateIdentifierException 
	{ 
		if(((IntegerValue)left.eval(env)).getValue()== 0 && ((IntegerValue)right.eval(env)).getValue()==0){
			return new IntegerValue(0); 
		}
		return new IntegerValue(1); 
		
	}
	
	public Type typeCheck(Environ<Type> env) throws TypeErrorException{
		Type t1 = left.typeCheck(env);
		Type t2 = right.typeCheck(env);
		if (t1==IntType.value && t2==IntType.value)
			return IntType.value;
		else
			throw new TypeErrorException(null);
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