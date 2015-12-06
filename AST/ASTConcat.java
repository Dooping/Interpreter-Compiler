package AST;

import Types.*;
import Values.IValue;
import exceptions.*;
import main.CodeBlock;
import main.CompilerFrame;
import main.Environ;

public class ASTConcat implements ASTNode{
	ASTNode left, right;

	public ASTConcat(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	
	public IValue eval(Environ<IValue> e)throws UndeclaredIdentifierException, DuplicateIdentifierException, ExecutionErrorException {
		return new ASTList(left,right).eval(e);
	}

	
	public Type typeCheck(Environ<Type> env)throws TypeErrorException, DuplicateIdentifierException, UndeclaredIdentifierException {
		Type l = left.typeCheck(env);
		Type r = right.typeCheck(env);
		
		if(l instanceof ListType)
			throw new TypeErrorException("Expecting a value, not a list");
		
		if(r instanceof ListType)
			return ListType.value;
		
		throw new TypeErrorException("Expecting a List");
	}

	
	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException {
		
	}
	
	
	public String toString(){
		return left.toString() + "::" + right.toString();
	}

}
