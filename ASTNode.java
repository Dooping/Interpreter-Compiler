public interface ASTNode {

	int eval(Environ e) throws UndeclaredIdentifierException, DuplicateIdentifierException;

    void compile( CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException ;

}

