package AST;

import Types.*;
import Values.*;
import exceptions.*;
import main.*;


public class ASTHd implements ASTNode {

	ASTNode list;
	
	public ASTHd(ASTNode list) {
		this.list = list;
	}

	
	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException, DuplicateIdentifierException, ExecutionErrorException {
		return ((IListValue)list.eval(e)).hd();
	}

	
	public Type typeCheck(Environ<Type> env)throws TypeErrorException, DuplicateIdentifierException, UndeclaredIdentifierException {
		Type l = list.typeCheck(env);
		if(l instanceof ListType)
			return l; 
		throw new TypeErrorException("Expecting a list");
	}


	public void compile(CodeBlock code, CompilerFrame env)
			throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub

	}

}
