package AST;

import Types.*;
import Values.*;
import exceptions.DuplicateIdentifierException;
import exceptions.ExecutionErrorException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import main.CodeBlock;
import main.CompilerFrame;
import main.Environ;

public class ASTNegate implements ASTNode {
	
	ASTNode expr;

	public ASTNegate(ASTNode expr) {
		this.expr = expr;
	}

	@Override
	public IValue eval(Environ<IValue> e)
			throws UndeclaredIdentifierException, DuplicateIdentifierException, ExecutionErrorException {
		return new BooleanValue(!((BooleanValue)expr.eval(e)).getValue());
	}

	@Override
	public Type typeCheck(Environ<Type> env)
			throws TypeErrorException, DuplicateIdentifierException, UndeclaredIdentifierException {
		Type type = expr.typeCheck(env);
		if(type instanceof BoolType)
			return BoolType.value;
		else
			throw new TypeErrorException("Value is not Boolean");
	}

	@Override
	public void compile(CodeBlock code, CompilerFrame env)
			throws UndeclaredIdentifierException, DuplicateIdentifierException {
		expr.compile(code, env);
		code.emit_push(1);
		code.emit_xor();
	}
	
	@Override
	public String toString(){
		return "!"+expr.toString();
	}

}
