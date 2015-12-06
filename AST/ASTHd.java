package AST;

import Types.Type;
import Values.*;
import exceptions.DuplicateIdentifierException;
import exceptions.ExecutionErrorException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import main.CodeBlock;
import main.CompilerFrame;
import main.Environ;

public class ASTHd implements ASTNode {

	ASTNode list;
	
	public ASTHd(ASTNode list) {
		this.list = list;
	}

	@Override
	public IValue eval(Environ<IValue> e)
			throws UndeclaredIdentifierException, DuplicateIdentifierException, ExecutionErrorException {
		return ((IListValue)list.eval(e)).hd();
	}

	@Override
	public Type typeCheck(Environ<Type> env)
			throws TypeErrorException, DuplicateIdentifierException, UndeclaredIdentifierException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void compile(CodeBlock code, CompilerFrame env)
			throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub

	}

}
