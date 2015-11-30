package main;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;

import parser.*;

import Types.*;
import exceptions.*;
import exceptions.*;
import AST.*;

public class Compiler {
	
	
	public static void compile(String s) throws ParseException, UndeclaredIdentifierException, DuplicateIdentifierException, TypeErrorException, FileNotFoundException {
		Parser parser = new Parser(new ByteArrayInputStream(s.getBytes()));
		ASTNode exp = parser.Start();
		CodeBlock code = new CodeBlock();
		Type type = exp.typeCheck(new Environ<Type>());
		exp.compile(code, new CompilerFrame());
		 code.dump();
	}
	
	public static void main(String args[]) {
		Parser parser = new Parser(System.in);
	    ASTNode exp;
	
	    try {
	      exp = parser.Start();
	
	      CodeBlock code = new CodeBlock();
	      Type type = exp.typeCheck(new Environ<Type>());
	      System.out.println( type.toString() );
	      exp.compile(code, new CompilerFrame());
	
	      code.dump();
	      System.out.println("Code dumped");
	
	    } catch (ParseException e) {
	    	System.out.println ("Syntax Error!");
	    	e.printStackTrace();
	    } catch (UndeclaredIdentifierException e) {
	    	System.out.println("Undeclared Identifier "+e.getId());
			e.printStackTrace();
		} catch (DuplicateIdentifierException e) {
			System.out.println("Duplicate Identifier "+e.getId());
			parser.ReInit(System.in);
		} catch (TypeErrorException e) {
			System.out.println("Type Error: "+e.getMessage());
			parser.ReInit(System.in);
		} 
	}
	
/*
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
*/
}
