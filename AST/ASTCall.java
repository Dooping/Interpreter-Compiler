package AST;

import types.IType;
import types.funType;
import util.DuplicateIdentifierException;
import util.Environment;
import util.ExecutionErrorException;
import util.TypeErrorException;
import util.UndeclaredIdentifierException;
import values.ClosureValue;
import values.IValue;

public class ASTCall implements ASTNode {
	
	private ASTNode left,right;
	
	public ASTCall (ASTNode left, ASTNode right){
		this.left = left;
		this.right = right;
	}

	@Override
	public IValue eval(Environment<IValue> env) throws UndeclaredIdentifierException, DuplicateIdentifierException,ExecutionErrorException {
		IValue result;
		
		IValue v1 = left.eval(env);
		IValue v2 = right.eval(env);
		
		if(v1 instanceof ClosureValue){
			ClosureValue closure = (ClosureValue)v1;
			
			String param = closure.getId();
			ASTNode body = closure.getBody();
			Environment<IValue> closureEnv = closure.getEnv();
			
			Environment<IValue> newEnv = newEnv.beginScope();
			
			newEnv.assoc(param,v2);
			result = body.eval(newEnv);
			
			
			newEnv.endScope();
		}
		else throw new ExecutionErrorException("Call a non-funtional value");
		
		
		return result;
	}

	public IType typecheck(Environment<IType> environment)
			throws UndeclaredIdentifierException, DuplicateIdentifierException,
			TypeErrorException {
		
		IType leftType = left.typecheck(environment);
		IType argType = right.typecheck(environment);
		
		if(leftType instanceof funType){
			funType funT = (funType) lefType;
			
			if(funT.getParType().equals(argType))
				return funT.getResultType;
			else
				throw new TypeErrorException("Type mismatch in funtion call");
			
		}
		else throw new TypeErrorException("Calling non-funtion value");
		
		return null;
	}

}
