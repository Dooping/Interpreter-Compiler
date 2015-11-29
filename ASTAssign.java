
public class ASTAssign implements ASTNode{
	
	ASTNode left, right;

	public ASTAssign(ASTNode left, ASTNode right) {
		//um id
		this.left = left;
		//um type
		this.right = right;
	}

	
	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException,DuplicateIdentifierException {
		IValue l = left.eval(e);
		IValue r = right.eval(e);

		((RefValue) l).setValue(r);

		return r;
	}

	
	public Type typeCheck(Environ<Type> env) throws TypeErrorException {
		Type l =  left.typeCheck(env);
		Type r = right.typeCheck(env);
		if(l instanceof RefType){
			RefType t = (RefType) l;
			Type teste = t.getType();
			if(teste.equals(r)){
				return r;
			}
			else throw new TypeErrorException("Diferent types");
		}

		return null;
	}

	
	 public String toString(){
	    	return left.toString() + ":=" + right.toString();
	    }
	
	public void compile(CodeBlock code, CompilerFrame env) throws UndeclaredIdentifierException, DuplicateIdentifierException {
		left.compile(code, env);
		code.emit_CheckCastRefClass();
		//dup
		code.emit_dup();
		right.compile(code, env);
		code.emit_putFieldRefClass();
		code.emit_CheckCastRefClass();
		code.emit_getFieldForRefClass();
		//fazer um checkcast e depois um getfield
		
	}

}
