package AST;

import java.util.ArrayList;
import java.util.Map;

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
		//System.out.println(id);
		IValue iv = record.eval(e);
		if( iv instanceof RecordValue){
			Map<String, IValue> values = ( (RecordValue) iv).getValues();
			if(values.containsKey(id)){
				return values.get(id);
			}
		}
		return iv;
	}

	public Type typeCheck(Environ<Type> env) throws TypeErrorException,DuplicateIdentifierException, UndeclaredIdentifierException {
		Type recordType = record.typeCheck(env);
		if( recordType instanceof RecordType ){
			ArrayList <String> ids = ( (RecordType) recordType).getIds();
			ArrayList <Type> types = ( (RecordType) recordType).getTypes();
			for (int i = 0; i<ids.size(); i++){
				if(ids.get(i).equals(id))
					return types.get(i);
			}
			throw new UndeclaredIdentifierException("Expecting record identifier");
		}
		else throw new TypeErrorException("Expecting recordType");

	}

	public void compile(CodeBlock code, CompilerFrame env) throws UndeclaredIdentifierException, DuplicateIdentifierException {
	}
	
	public String toString(){
		return record.toString() + "." + id;
	}
	
}