package AST;

import parser.*;
import Types.*;
import exceptions.*;
import Values.*;
import main.*;

public class ASTMul implements ASTNode{
	ASTNode left, right;

	public IValue eval(Environ<IValue> env) throws UndeclaredIdentifierException, DuplicateIdentifierException, ExecutionErrorException 
	{ 
		return new IntegerValue(((IntegerValue)left.eval(env)).getValue()*((IntegerValue)right.eval(env)).getValue()); 
	}
	
	public Type typeCheck(Environ<Type> env) throws TypeErrorException, DuplicateIdentifierException, UndeclaredIdentifierException{
		Type t1 = left.typeCheck(env);
		Type t2 = right.typeCheck(env);
		if (t1==IntType.value && t2==IntType.value)
			return IntType.value;
		else
			throw new TypeErrorException(null);
	}

        public ASTMul(ASTNode l, ASTNode r)
        {
		left = l; right = r;
        }
        @Override
    	public String toString(){
    		return left.toString() +" * "+ right.toString();
    	}

		
		public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException  {
		  	left.compile(code, env);
	    	right.compile(code, env);
	    	code.emit_mul();
		}
}