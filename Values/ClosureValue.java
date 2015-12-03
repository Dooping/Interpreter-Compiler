package Values;

import AST.ASTNode;
import main.*;


public class ClosureValue implements IValue {
	
	private String id;
	private ASTNode expr;
	private Environ<IValue> env;
	
	public ClosureValue(String id, ASTNode expr, Environ<IValue> env){
		this.id = id;
		this.expr = expr;
		this.env = env;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the expr
	 */
	public ASTNode getExpr() {
		return expr;
	}

	/**
	 * @param expr the expr to set
	 */
	public void setExpr(ASTNode expr) {
		this.expr = expr;
	}

	/**
	 * @return the env
	 */
	public Environ<IValue> getEnv() {
		return env;
	}

	/**
	 * @param env the env to set
	 */
	public void setEnv(Environ<IValue> env) {
		this.env = env;
	}
	
	
}
