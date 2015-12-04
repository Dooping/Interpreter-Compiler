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
	private ArrayList<String> ids;
	private ArrayList<Type> types;
 	/*
	public ASTFun (String id, ASTNode e, Type type){
		this.exp = e;
		this.id = id;
		this.type = type;
	}
	*/
	public ASTFun (ArrayList<String> id, ASTNode e, ArrayList<Type> type){
		this.exp = e;
		this.ids = id;
		this.types = type;
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
		
		for (int i = 0; i<ids.size(); i++){
			envLoc.assocType(ids.get(i), types.get(i));
		}
		//envLoc.assocType(id, type);
		Type returnType = exp.typeCheck(envLoc);
		return new funType(types, returnType);
		//else throw new TypeErrorException("Expecting diferent types");
	}


	public void compile(CodeBlock code, CompilerFrame env) throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub
		
	}
	
	public String toString(){
		return id;
	}
	
}
