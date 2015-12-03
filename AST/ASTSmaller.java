package AST;

import parser.*;
import Types.*;
import exceptions.*;
import Values.*;
import main.*;

public class ASTSmaller implements ASTNode{

	ASTNode left, right;
	
	public IValue eval(Environ<IValue> env) throws UndeclaredIdentifierException, DuplicateIdentifierException, ExecutionErrorException 
	{ 
		if(((IntegerValue)left.eval(env)).getValue() < ((IntegerValue)right.eval(env)).getValue()){
			return new BooleanValue(true); 
		}
		return new BooleanValue(false); 
		
	}
	
	public Type typeCheck(Environ<Type> env) throws TypeErrorException, DuplicateIdentifierException, UndeclaredIdentifierException{
		Type t1 = left.typeCheck(env);
		Type t2 = right.typeCheck(env);
		if (t1==IntType.value && t2==IntType.value)
			return BoolType.value;
		else
			throw new TypeErrorException(null);
	}
	
	 public ASTSmaller(ASTNode l, ASTNode r)
     {
		left = l; right = r;
      }
	
	 @Override
 	public String toString(){
		return left.toString() +" < "+ right.toString();
 	}

	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException  {
		code.comment("fazer <");
		right.compile(code, env);
		left.compile(code, env);
		//NOTA: o codeBlock tem codigo para x>y, para tal, reutiliza-se este codigo,
		//trocando os n�s, em vez x<y, fazemos, y>x!
		code.emit_compare();
	}

}