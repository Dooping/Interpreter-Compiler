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

	public IValue eval(Environ<IValue> env) throws UndeclaredIdentifierException, DuplicateIdentifierException{
		IValue value;
		
		//novo ambiente
		Environ<IValue> newEnv = env.beginScope();
		for(Binding decl: decls){
			IValue idValue = decl.getExpr().eval(env);
			newEnv.assoc(decl.getID(),idValue);
		}
		
		value = expr.eval(newEnv);
		newEnv.endScope();
		return value;
	}
	public Type typeCheck(Environ<Type> env) throws TypeErrorException{
		Type value;
		Environ<Type> newEnv = env.beginScope();
		for(Binding decl: decls){
			Type idType = decl.getExpr().typeCheck(newEnv);
			try {
				newEnv.assocType(decl.getID(),idType);
			} catch (DuplicateIdentifierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		value = expr.typeCheck(newEnv);
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
