import java.util.ArrayList;


public class ASTDecl implements ASTNode{
	
	//lista com id e os seus valores: x = 1; y = 2 + 3, etc...
	ArrayList <Binding> decls;
	//expressão a avaliar: x + y, etc
	ASTNode expr;
	public ASTDecl(ArrayList <Binding> ds, ASTNode e){
		decls = ds;
		expr = e;
	}

	public int eval(Environ env) throws UndeclaredIdentifierException, DuplicateIdentifierException {
		int value;
		//novo ambiente
		Environ newEnv = env.beginScope();
		//encontrar todas os id usados (ou dados)
		for(Binding decl: decls){
			int idValue = decl.getExpr().eval(env);
			newEnv.assoc(decl.getID(),idValue);
		}
		value = expr.eval(newEnv);
		newEnv.endScope();
		return value;
	}

	@SuppressWarnings("static-access")
	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException {

		CompilerFrame scope = env.beginScope();
		code.newFrame(scope.getType(), scope);
		int i = 1;
		for(Binding decl: decls){
			decl.getExpr().compile(code,env);
			code.emit_putField(scope.getType(), decl.getID(), "I");
			scope.assoc(decl.getID());
			if(i < decls.size()){
				code.emit_dup();
			}
			i++;
		}
		code.emit_astore();
		expr.compile(code, scope);
		code.endFrame(scope.getType(), scope.getAncestor().getType());
		
		if(env.getType() == 0)
			env.counter=0;
	}
	
	
	public String toString(){
    	return "(" +  expr.toString() + ")";
    }


}
