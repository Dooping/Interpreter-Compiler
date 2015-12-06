package AST;

import Types.Type;
import Values.IListValue;
import Values.IValue;
import exceptions.DuplicateIdentifierException;
import exceptions.ExecutionErrorException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import main.CodeBlock;
import main.CompilerFrame;
import main.Environ;

public class ASTTl implements ASTNode {
	
	ASTNode list;

	public ASTTl(ASTNode list) {
		this.list = list;
	}

	
	public IValue eval(Environ<IValue> e)
			throws UndeclaredIdentifierException, DuplicateIdentifierException, ExecutionErrorException {
		return ((IListValue)list.eval(e)).tl();
	}

	
	public Type typeCheck(Environ<Type> env) throws TypeErrorException, DuplicateIdentifierException, UndeclaredIdentifierException {

		return list.typeCheck(env);
	}

	
	public void compile(CodeBlock code, CompilerFrame env)
			throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub

	}

}
