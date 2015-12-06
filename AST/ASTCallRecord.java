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
		IValue iv = record.eval(e);
		if( iv instanceof RecordValue){
			ArrayList <Binding> types = ( (RecordValue) iv).getBind();
			for(Binding type: types){
				if(type.getID().equals(id)){
					return type.getExpr().eval(e);
				}
			}
		}
		return iv;
	}

	public Type typeCheck(Environ<Type> env) throws TypeErrorException,DuplicateIdentifierException, UndeclaredIdentifierException {
		Type recordType = record.typeCheck(env);
		if( recordType instanceof RecordType ){
			ArrayList <Binding> types = ( (RecordType) recordType).getTypes();
			for(Binding type: types){
				if(type.getID().equals(id)){
					return type.getExpr().typeCheck(env);
				}
			}
		}
		else throw new TypeErrorException("Expecting recordType");

		throw new UndeclaredIdentifierException("Expecting record identifier");
	}

	public void compile(CodeBlock code, CompilerFrame env)
			throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub
		
	}
	
	public String toString(){
		return record.toString() + "." + id;
	}
	
}