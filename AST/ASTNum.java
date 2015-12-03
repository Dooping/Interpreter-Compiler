package AST;

import parser.*;
import Types.*;
import exceptions.*;
import Values.*;
import main.*;
public class ASTNum implements ASTNode {

	int val;

	public IValue eval(Environ<IValue> env) throws ExecutionErrorException 
	{ 
		return new IntegerValue(val); 
	}
	
	public Type typeCheck(Environ<Type> env) throws TypeErrorException, DuplicateIdentifierException, UndeclaredIdentifierException{
		return IntType.value;
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

