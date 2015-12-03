package AST;

import Types.*;
import exceptions.*;
import Values.*;
import main.*;

public class ASTFun implements ASTNode {
	
	
	private ASTNode exp;
	private String id;
	private Type type;
	
	public ASTFun (String id, ASTNode e, Type type){
		this.exp = e;
		this.id = id;
		this.type = type;
	}

	public IValue eval(Environ<IValue> env) throws UndeclaredIdentifierException, DuplicateIdentifierException, ExecutionErrorException {
		return new ClosureValue(id, exp, env);
	}

	public Type typeCheck(Environ<Type> env) throws TypeErrorException, DuplicateIdentifierException, UndeclaredIdentifierException{

		Type parType = null;
		Environ<Type> envLoc = env.beginScope();
		//TODO: erro
		//parType = envLoc.findType(id);
		funType t = ((funType) type);
		parType = t.getParType();
		Type typeBo = t.getResultType();
		envLoc.assocType(id, parType);
		Type returnType = exp.typeCheck(envLoc);
		if(typeBo.equals(returnType))
			return new funType(parType, returnType);
		else throw new TypeErrorException("Expecting diferent types");
	}


	public void compile(CodeBlock code, CompilerFrame env) throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub
		
	}
	
	public String toString(){
		return id;
	}
	
}
