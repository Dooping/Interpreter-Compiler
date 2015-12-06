package AST;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import Types.*;
import exceptions.*;
import Values.*;
import main.*;

public class ASTRecord implements ASTNode{
	
	ArrayList <Binding> values;
	private Map<String, Type> types = new TreeMap<String, Type>();
	private Map<String, IValue> Ivalues;
	
	public ASTRecord(ArrayList <Binding> ds){
		values = ds;
	}

	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException,DuplicateIdentifierException, ExecutionErrorException {
		Ivalues = new TreeMap<String, IValue>();
		for(Binding value: values)
			Ivalues.put(value.getID(), value.getExpr().eval(e)) ;
		return new RecordValue(Ivalues);
	}

	public Type typeCheck(Environ<Type> env) throws TypeErrorException,DuplicateIdentifierException, UndeclaredIdentifierException {
		types = new TreeMap<String, Type>();
		for(Binding value: values){
			types.put(value.getID(), value.getExpr().typeCheck(env));
		}
	
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