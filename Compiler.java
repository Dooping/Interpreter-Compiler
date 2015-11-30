package main;

import parser.*;
import Types.*;
import exceptions.*;
import Values.*;
import AST.*;

public class Compiler {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Parser parser = new Parser(System.in);
	    ASTNode exp;

	    while (true) {
	    try {
			CodeBlock code = new CodeBlock();
			exp = parser.Start();
			 exp.typeCheck(new Environ<Type>());
			//System.out.println( exp.toString() + " = " + exp.eval(new Environ()) );
			//criar o contador para usar na compiler frame
			exp.compile(code, new CompilerFrame());
			code.dump();
	    } catch (Exception e) {
	    	System.out.println ("Syntax Error!");
	    	e.printStackTrace();
	    	parser.ReInit(System.in);
	    }
	    }
	}

}
