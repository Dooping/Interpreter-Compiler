package AST;

import java.util.ArrayList;

import Types.*;
import exceptions.*;
import Values.*;
import main.*;

public class ASTFun implements ASTNode {
	
	private ASTNode exp;
	private String id;
	private Type type;
	private Type ReturnType;

 	
	public ASTFun (String id, ASTNode e, Type type){
		this.exp = e;
		this.id = id;
		this.type = type;
	}


	public IValue eval(Environ<IValue> env) throws UndeclaredIdentifierException, DuplicateIdentifierException, ExecutionErrorException {
		return new ClosureValue(id, exp, env);
	}

	public Type typeCheck(Environ<Type> env) throws TypeErrorException, DuplicateIdentifierException, UndeclaredIdentifierException{

		//Type parType = null;
		Environ<Type> envLoc = env.beginScope();
		//parType = envLoc.findType(id);
		//funType t = ((funType) type);
		//parType = t.getParType();
		
		envLoc.assocType(id, type);
		ReturnType = exp.typeCheck(envLoc);
		return new funType(type, ReturnType);
		//else throw new TypeErrorException("Expecting diferent types");
	}


	public void compile(CodeBlock code, CompilerFrame env) throws UndeclaredIdentifierException, DuplicateIdentifierException {
		//ClosureFrame clo = new ClosureFrame();
		//clo.addAncestor(env);
		//clo.addTypes(type, ReturnType);
		
		
		//exp.compile(clo, env);
		
		
		//code.emit_Fun();
		
	}
	
	public String toString(){
		return id;
	}
	
}
