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
		ArrayList<IValue> v = new ArrayList<IValue>();
		IValue idValue = null;
		Environ<IValue> newEnv = e.beginScope();
		for(Binding value: values){
			v.add(value.getExpr().eval(e));
			newEnv.assoc(value.getID(),idValue);
		}
		newEnv.endScope();
		return new RecordValue(v);
	}

	public Type typeCheck(Environ<Type> env) throws TypeErrorException,DuplicateIdentifierException, UndeclaredIdentifierException {
		ArrayList<Type> types = new ArrayList<Type>();
		Type idType = null;
		Environ<Type> newEnv = env.beginScope();
		TypesOfVar = new ArrayList<Type>();
		for(Binding value: values){
			types.add(value.getExpr().typeCheck(newEnv));
			newEnv.assocType(value.getID(),idType);
			TypesOfVar.add(idType);
		}
	
		newEnv.endScope();
		return new RecordType(types);
	}

	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException {
		
	}
	
	public String toString(){
		String toReturn = "{ ";
		for(Binding value: values){
			toReturn +=value.getID() + " = " + value.getExpr().toString() + " ";
		}
		toReturn+=" }";
		return toReturn;
	}
	
	
}