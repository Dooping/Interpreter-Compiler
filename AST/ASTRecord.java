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
		for(Binding value: values)
			value.getExpr().eval(e);
		return new RecordValue(values);
	}

	public Type typeCheck(Environ<Type> env) throws TypeErrorException,DuplicateIdentifierException, UndeclaredIdentifierException {
		Type idType = null;
		TypesOfVar = new ArrayList<Type>();
		for(Binding value: values){
			value.getExpr().typeCheck(env);
			TypesOfVar.add(idType);
		}
	
		return new RecordType(values);
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