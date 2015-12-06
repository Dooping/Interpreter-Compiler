package AST;

import Types.ListType;
import Types.Type;
import Values.*;
import exceptions.DuplicateIdentifierException;
import exceptions.ExecutionErrorException;
import exceptions.TypeErrorException;
import exceptions.UndeclaredIdentifierException;
import main.CodeBlock;
import main.CompilerFrame;
import main.Environ;

public class ASTList  implements ASTNode {

ASTNode hd, tl;

	public ASTList(ASTNode hd, ASTNode tl)
	{
		this.hd = hd; this.tl = tl;
	}

    @Override
    public String toString() {
    	return "["+hd + ";" + tl+"]";
    }

	
	public IValue eval(Environ<IValue> e)throws UndeclaredIdentifierException, DuplicateIdentifierException, ExecutionErrorException {
		
		IListValue tailValue = (tl!=null) ? (IListValue)tl.eval(e) : new EmptyListValue();
		
		return new ConsValue(hd.eval(e),tailValue);
	}

	
	public Type typeCheck(Environ<Type> env)throws TypeErrorException, DuplicateIdentifierException, UndeclaredIdentifierException {
		hd.typeCheck(env);/////
		if(tl!=null)
			tl.typeCheck(env);/////
		return ListType.value;
	}

	
	public void compile(CodeBlock code, CompilerFrame env)
			throws UndeclaredIdentifierException, DuplicateIdentifierException {
		
	}
}
