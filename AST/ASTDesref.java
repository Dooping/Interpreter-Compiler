package AST;

import parser.*;
import Types.*;
import exceptions.*;
import Values.*;
import main.*;
import parser.*;
import Types.*;
import exceptions.*;
import Values.*;
import main.*;


public class ASTDesref  implements ASTNode{
	ASTNode expr;
	Type type;
	
	public ASTDesref(ASTNode expr) {
		this.expr = expr;
	}

	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException,DuplicateIdentifierException, ExecutionErrorException {
		RefValue r = (RefValue) expr.eval(e);
		return r.getValue();
	}

	
	public Type typeCheck(Environ<Type> env) throws TypeErrorException, DuplicateIdentifierException, UndeclaredIdentifierException {
		Type t =  expr.typeCheck(env);
		if(t instanceof RefType){
			RefType t1 = (RefType)t;
			type = t1.getType();
		}
		return type;
		//else
			//throw new TypeErrorException(null);
	}

	
	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException {
		code.comment("desref");
		if(type instanceof RefType){
			expr.compile(code, env);
			code.emit_CheckCastRefClass();
			code.emit_getFieldForRefClass();
		}
		else {
			expr.compile(code, env);
			code.emit_CheckCastRefInt();
			code.emit_getFieldForRefInt();
		}

	}
	
	 public String toString(){
	    	return "*"+expr.toString();
	    }

}
