package AST;

import Types.ListType;
import Types.Type;
import Values.IValue;
import exceptions.DuplicateIdentifierException;
import exceptions.ExecutionErrorException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import main.CodeBlock;
import main.CompilerFrame;
import main.Environ;

public class ASTConcat implements ASTNode{
	ASTNode left, right;

	public ASTConcat(ASTNode left, ASTNode right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environ<IValue> e)
			throws UndeclaredIdentifierException, DuplicateIdentifierException, ExecutionErrorException {

		return new ASTList(left,right).eval(e);
	}

	@Override
	public Type typeCheck(Environ<Type> env)
			throws TypeErrorException, DuplicateIdentifierException, UndeclaredIdentifierException {
		// TODO Auto-generated method stub
		return ListType.value;
	}

	@Override
	public void compile(CodeBlock code, CompilerFrame env)
			throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString(){
		return left.toString() + "::" + right.toString();
	}

}
