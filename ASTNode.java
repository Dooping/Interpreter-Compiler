public interface ASTNode {

	int eval(Environ e) throws UndeclaredIdentifierException, DuplicateIdentifierException;
	//IValue eval(Environ<IValue> e)
	//Type typeCheck(Environ<Type> e)

    void compile( CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException ;

}

