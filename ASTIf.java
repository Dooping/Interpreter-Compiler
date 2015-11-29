import javax.swing.text.StyledEditorKit.BoldAction;


public class ASTIf implements ASTNode{

	ASTNode clause, bodyThen, bodyElse;
	
	public ASTIf(ASTNode clause, ASTNode bodyT, ASTNode bodyE){
		this.clause = clause;
		this.bodyThen = bodyT;
		this.bodyElse = bodyE;
	}

	
	
	public IValue eval(Environ<IValue> e) throws UndeclaredIdentifierException,DuplicateIdentifierException {
		BooleanValue v =  (BooleanValue) clause.eval(e);
		if(v.val){
			return bodyThen.eval(e);
		}
		else return bodyElse.eval(e);

	}

	public Type typeCheck(Environ<Type> env) throws TypeErrorException {
		Type c = clause.typeCheck(env);
		Type t = bodyThen.typeCheck(env);
		Type e = bodyElse.typeCheck(env);
		
		if(c instanceof BoolType){
			if(t.equals(e))
				return bodyThen.typeCheck(env);
			else throw new TypeErrorException("Exepecting equal types");
		}
		else 
			throw new TypeErrorException("Expecting boolean clause");


	}

	public void compile(CodeBlock code, CompilerFrame env)throws UndeclaredIdentifierException, DuplicateIdentifierException {
		//Vamos precisar de dois labels
		int label = code.labelGenarator();
		int label2 = code.labelGenarator();
		//fazer o compile da expressão de avaliação
		clause.compile(code, env);
		//caso falhe (seja 0), vai para o branch do else
		code.emit_ifeq(label);
		bodyThen.compile(code, env);
		code.emit_goto(label2);
		code.emit_label(label);
		bodyElse.compile(code, env);
		code.emit_label(label2);
	}
	
	public String toString(){
		return clause.toString();
	}

}
