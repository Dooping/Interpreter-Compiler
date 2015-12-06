package AST;

import java.util.ArrayList;

import Types.*;
import exceptions.*;
import Values.*;
import main.*;

public class ASTRecord implements ASTNode{
	
	ArrayList <Binding> values;
	ArrayList<Type> TypesOfVar;
	
	public ASTRecord(ArrayList <Binding> ds){
		values = ds;
	}

	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException,DuplicateIdentifierException, ExecutionErrorException {
		IValue idValue = null;
		Environ<IValue> newEnv = e.beginScope();
		for(Binding decl: values){
			idValue = decl.getExpr().eval(e);
			newEnv.assoc(decl.getID(),idValue);
		}
		newEnv.endScope();
		return idValue;
	}

	public Type typeCheck(Environ<Type> env) throws TypeErrorException,DuplicateIdentifierException, UndeclaredIdentifierException {
		Type idType = null;
		Environ<Type> newEnv = env.beginScope();
		TypesOfVar = new ArrayList<Type>();
		for(Binding decl: values){
			idType = decl.getExpr().typeCheck(newEnv);
			newEnv.assocType(decl.getID(),idType);
			TypesOfVar.add(idType);
		}
	
		newEnv.endScope();
		return idType;
	}

	public void compile(CodeBlock code, CompilerFrame env)
			throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub
		
	}
	
	
}