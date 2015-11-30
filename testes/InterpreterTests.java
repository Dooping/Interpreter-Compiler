package testes;


import exceptions.*;
import Values.*;


import static org.junit.Assert.*;

import org.junit.Test;
import main.Interpreter;

public class InterpreterTests {

	@Test
	public void test1() throws Exception {
		assertTrue(Interpreter
				.evaluate("1\n")
				.equals(new IntegerValue(1)));
	}

	@Test
	public void testArithmeticOps() throws Exception {
		assertTrue(Interpreter
				.evaluate("1+2\n")
				.equals(new IntegerValue(3)));
		assertTrue(Interpreter
				.evaluate("1-2-3\n")
				.equals(new IntegerValue(-4)));
		assertTrue(Interpreter
				.evaluate("4*2\n")
				.equals(new IntegerValue(8)));
		assertTrue(Interpreter
				.evaluate("4/2/2\n")
				.equals(new IntegerValue(1)));		
	}
	
	@Test
	public void testDeclarations() throws Exception {
		assertTrue(Interpreter
				.evaluate("decl x = 1 in x end\n")
				.equals(new IntegerValue(1)));				
		assertTrue(Interpreter
				.evaluate("decl x = 1 in decl y = x x = 2 in x+y end end\n")
				.equals(new IntegerValue(3)));
	}

	@Test(expected = UndeclaredIdentifierException.class)
	public void testDeclarations1() throws Exception {
		Interpreter.evaluate("x\n");
	}

	@Test(expected = UndeclaredIdentifierException.class)
	public void testDeclarations2() throws Exception {
		Interpreter.evaluate("decl x = 1 in y end\n");
	}
	
	@Test(expected = UndeclaredIdentifierException.class)
	public void testDeclarations3() throws Exception {
		Interpreter.evaluate("decl x = x in x+1 end\n");
	}
	
	@Test(expected = UndeclaredIdentifierException.class)
	public void testDeclarations4() throws Exception {
		Interpreter.evaluate("decl x = 1 y = x in x+1 end\n");
	}

	@Test(expected = DuplicateIdentifierException.class)
	public void testDeclarations5() throws Exception {
		Interpreter.evaluate("decl x = 1 x = 3 in x+1 end\n");
	}

	@Test
	public void testVariables() throws Exception {
		assertTrue(Interpreter
				.evaluate("decl x = var(0) in decl y = x := *x + 1 in 2 * *x end end\n")
				.equals(new IntegerValue(2)));
	}
	
	@Test
	public void testFunction() throws Exception{
		assertTrue(Interpreter
				.evaluate("decl f = fun x :int => x+1 in\n")
				.equals(new IntegerValue(2)));
	}
	
	public void testFunctionAsArgument() throws Exception{
		assertTrue(Interpreter
				.evaluate("decl f = fun x :int=> x+1 in" + "decl g = fun f :int=> f(1) end in" + "g(f) end\n")
				.equals(new IntegerValue(2)));
	}
	
	public void testFunctionAsResult() throws Exception{
		assertTrue(Interpreter
				.evaluate("decl f = fun x:int => fun y:int => x + y end end in" + "decl g = f(1) in" + "g(2)+g(3)\n")
				.equals(new IntegerValue(2)));
	}
}
