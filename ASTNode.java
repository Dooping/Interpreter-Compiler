package AST;

import parser.*;
import Types.*;
import exceptions.*;
import Values.*;
import main.*;

public interface ASTNode {

	IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException, DuplicateIdentifierException;
	//IValue eval(Environ<IValue> e)
	//Type typeCheck(Environ<Type> e)
	
	Type typeCheck(Environ<Type> env) throws TypeErrorException;
	
    void compile( CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException ;

}

