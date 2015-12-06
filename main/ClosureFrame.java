package main;


import java.util.*;

import Types.*;

public class ClosureFrame extends CodeBlock {
	
	private Type parType, returnType;
	CompilerFrame father;
	int closureNumber;

	public ClosureFrame() {
		code = new ArrayList<String>();
		closureNumber = ++super.currentClosure;
	}
	
	public void addTypes(Type parType, Type returnType){
		this.parType = parType;
		this.returnType = returnType;
	}
	
	public void addAncestor(CompilerFrame father){
		this.father = father;
	}
	
	public void emit_headerClosure(){
		code.add(".class    closure_f_" + closureNumber);
		code.add(".implements    closure_interfaceClosure;");
		//code
	}
	
}