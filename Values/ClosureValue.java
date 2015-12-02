package values;

import AST.ASTNode;
import util.Environment;


public class ClosureValue implements IValue {
	
	private String id;
	private ASTNode expr;
	private Environment<IValue> env;
	
	public ClosureValue(String...){
		
	}
	
	//criar set e gets
}
