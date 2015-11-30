package AST;

import parser.*;
import Types.*;
import exceptions.*;
import Values.*;
import main.*;

public class ASTAssign implements ASTNode{
	
	ASTNode left, right;
	Type rightType;

	public ASTAssign(ASTNode left, ASTNode right) {
		//um id
		this.left = left;
		//um type
		this.right = right;
	}
	
	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException,DuplicateIdentifierException {
		IValue l = left.eval(e);
		IValue r = right.eval(e);

		((RefValue) l).setValue(r);

		return r;
	}

	
	public Type typeCheck(Environ<Type> env) throws TypeErrorException {
		Type l =  left.typeCheck(env);
		rightType = right.typeCheck(env);
		if(l instanceof RefType){
			RefType t = (RefType) l;
			Type teste = t.getType();
			if(teste.equals(rightType)){
				return rightType;
			}
			else throw new TypeErrorException("Diferent types");
		}

		return null;
	}

	
	 public String toString(){
	    	return left.toString() + ":=" + right.toString();
	    }
	
	public void compile(CodeBlock code, CompilerFrame env) throws UndeclaredIdentifierException, DuplicateIdentifierException {
		code.comment("assign");
		if(rightType instanceof RefType){
			left.compile(code, env);
			code.emit_CheckCastRefClass();
			code.emit_dup();
			right.compile(code, env);
			code.emit_putFieldRefClass();
			code.emit_CheckCastRefClass();
			code.emit_getFieldForRefClass();
		}
		else {
			left.compile(code, env);
			code.emit_CheckCastRefInt();
			code.emit_dup();
			right.compile(code, env);
			code.emit_putFieldRefInt();
			code.emit_CheckCastRefInt();
			code.emit_getFieldForRefInt();
		}

	}

}
