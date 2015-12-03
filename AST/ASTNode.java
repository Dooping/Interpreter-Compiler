package AST;

import parser.*;
import Types.*;
import exceptions.*;
import Values.*;
import main.*;

public interface ASTNode {

	IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException, DuplicateIdentifierException, ExecutionErrorException;
	
	Type typeCheck(Environ<Type> env) throws TypeErrorException, DuplicateIdentifierException, UndeclaredIdentifierException;
	
    void compile( CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException ;

}

