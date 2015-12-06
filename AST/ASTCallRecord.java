package AST;

import java.util.ArrayList;

import Types.*;
import exceptions.*;
import Values.*;
import main.*;

public class ASTCallRecord implements ASTNode{
	
	ASTNode record;
	String id;
	
	public ASTCallRecord(ASTNode record, String id){
		this.id = id;
		this.record = record;
	}

	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException,DuplicateIdentifierException, ExecutionErrorException {
		IValue ide = id.eval(e);
		return null;
	}

	public Type typeCheck(Environ<Type> env) throws TypeErrorException,DuplicateIdentifierException, UndeclaredIdentifierException {
		
		return null;
	}

	public void compile(CodeBlock code, CompilerFrame env)
			throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub
		
	}
	
}