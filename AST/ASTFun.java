package AST;

import types.*;
import util.DuplicateIdentifierException;
import util.Environment;
import util.TypeErrorException;
import util.UndeclaredIdentifierException;
import values.IValue;

public class ASTFun implements ASTNode {
	
	
	private ASTNode exp;
	private String id;
	private IType type;
	
	public ASTFun (String id, ASTNode e, IType type){
		this.exp = e;
		this.id = id;
		this.type = type;
	}

	public IValue eval(Environment<IValue> env) throws UndeclaredIdentifierException, DuplicateIdentifierException {
		return new ClosureValue(id, exp, env);
	}

	public IType typecheck(Environment<IType> env)
			throws UndeclaredIdentifierException, DuplicateIdentifierException,
			TypeErrorException {
		
		IType parType;
		
		Environment<IType> envLoc = env.beginScope();
		
		envLoc.assoc(id, parType);
		
		
		IType returnType = exp.typecheck(envLoc);
		
		
		
		
		return new funType(parType, returnType);

	}
	
}
