package main;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import AST.ASTNode;
import parser.*;
import Types.*;
import exceptions.*;
import Values.*;

public class Interpreter {

	public static IValue evaluate(String s) throws ParseException, UndeclaredIdentifierException, DuplicateIdentifierException, TypeErrorException,ExecutionErrorException {
		Parser parser = new Parser(new ByteArrayInputStream(s.getBytes()));
		ASTNode exp = parser.Start();
		Type type = exp.typeCheck(new Environ<Type>());
		return exp.eval(new Environ<IValue>());
	}
	
	@SuppressWarnings("static-access")
	public static void main(String args[])  {
		Parser parser = new Parser(System.in);
	    ASTNode exp;
	
	    while (true) {
		    try {
		      exp = parser.Start();
		      Type type = exp.typeCheck(new Environ<Type>());
		      System.out.println( type.toString() );
		      System.out.println( exp.toString() + " = "+ exp.eval(new Environ<IValue>()) );
		    } catch (ParseException e) {
		    	System.out.println ("Syntax Error!");
		    	e.printStackTrace();
		    	parser.ReInit(System.in);
		    } catch (UndeclaredIdentifierException e) {
		    	System.out.println("Undeclared Identifier "+e.getId());
				parser.ReInit(System.in);
			} catch (DuplicateIdentifierException e) {
				System.out.println("Duplicate Identifier "+e.getId());
				parser.ReInit(System.in);
			} catch (TypeErrorException e) {
				System.out.println("Type Error: "+e.getMessage());
				parser.ReInit(System.in);
			} catch (ExecutionErrorException e) {
				System.out.println("Execution Error: "+e.getMessage());
				parser.ReInit(System.in);
			}
	    }
	}
}
