
public class Binding {
	
	private String id;
	private ASTNode expr;
	
	public Binding (String id, ASTNode expr){
		this.id = id;
		this.expr = expr;
	}
	
	public String getID(){
		return id;
	}
	
	public ASTNode getExpr(){
		return expr;
	}
}
