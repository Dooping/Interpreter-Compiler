package AST;

import parser.*;
import Types.*;
import exceptions.*;
import Values.*;
import main.*;

public class ASTOr implements ASTNode{

	ASTNode left, right;
	
	public IValue eval(Environ<IValue> env) throws UndeclaredIdentifierException, DuplicateIdentifierException, ExecutionErrorException 
	{ 
		if(((BooleanValue)left.eval(env)).getValue() || ((BooleanValue)right.eval(env)).getValue()){
			return new BooleanValue(true); 
		}
		return new BooleanValue(false); 
		
	}
	
	public Type typeCheck(Environ<Type> env) throws TypeErrorException, DuplicateIdentifierException, UndeclaredIdentifierException{
		Type t1 = left.typeCheck(env);
		Type t2 = right.typeCheck(env);
		if (t1==BoolType.value && t2==BoolType.value)
			return BoolType.value;
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
		left.compile(code, env);
		right.compile(code, env);
		code.emit_or();
	}
}