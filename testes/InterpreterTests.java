package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import Values.IntegerValue;
import main.Interpreter;
import exceptions.*;


public class InterpreterTests {

	@Test
	public void test01() throws Exception {
		assertTrue(Interpreter
				.evaluate("1\n")
				.equals(new IntegerValue(1)));
	}

	@Test
	public void test02ArithmeticOps() throws Exception {
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
	public void test03Declarations() throws Exception {
		assertTrue(Interpreter
				.evaluate("decl x = 1 in x end\n")
				.equals(new IntegerValue(1)));				
		assertTrue(Interpreter
				.evaluate("decl x = 1 in decl y = x x = 2 in x+y end end\n")
				.equals(new IntegerValue(3)));
	}

	@Test(expected = UndeclaredIdentifierException.class)
	public void test04Declarations1() throws Exception {
		Interpreter.evaluate("x\n");
	}

	@Test(expected = UndeclaredIdentifierException.class)
	public void test05Declarations2() throws Exception {
		Interpreter.evaluate("decl x = 1 in y end\n");
	}
	
	@Test(expected = UndeclaredIdentifierException.class)
	public void test06Declarations3() throws Exception {
		Interpreter.evaluate("decl x = x in x+1 end\n");
	}
	
	@Test(expected = UndeclaredIdentifierException.class)
	public void test07Declarations4() throws Exception {
		Interpreter.evaluate("decl x = 1 y = x in x+1 end\n");
	}

	@Test(expected = DuplicateIdentifierException.class)
	public void test08Declarations5() throws Exception {
		Interpreter.evaluate("decl x = 1 x = 3 in x+1 end\n");
	}

	@Test
	public void test09Variables() throws Exception {
		assertTrue(Interpreter
				.evaluate("decl x = var(0) in decl y = x := *x + 1 in 2 * *x end end\n")
				.equals(new IntegerValue(2)));
	}
	
	@Test
	public void testRecordConstruction() throws Exception {
		assertTrue(Interpreter
				.evaluate("decl x = {a = 1, b = 2} in x.a + x.b end\n")
				.equals(new IntegerValue(3)));
	}

	@Test(expected = TypeErrorException.class)
	public void testRecordTyping1() throws Exception {
		Interpreter.evaluate("decl x = {a = 1, b = 2} in x.c end\n");
	}

	@Test(expected = TypeErrorException.class)
	public void testRecordTyping2() throws Exception {
		Interpreter.evaluate("decl x = {a = 1, b = false} in x.a + x.b end\n");
	}

	@Test(expected = TypeErrorException.class)
	public void testRecordTyping3() throws Exception {
		Interpreter.evaluate("decl x = {a = 1, b = false, a = 2} in x.a end\n");
	}
	
	@Test
	public void testRecordTypingFun() throws Exception {
		assertTrue(Interpreter
				.evaluate("decl x = {a = 1, b = false} in "
						+ "decl y = {a = 2, b = true} in "
						+ "decl f = fun r:{a:int, b:bool} => "
						+ "		if r.b then r.a else r.a*2 end end in "
						+ "f(x) + f(y) end end end\n")
				.equals(new IntegerValue(4)));
	}

	@Test(expected = TypeErrorException.class)
	public void testRecordTypingFun2() throws Exception {
		Interpreter.evaluate("decl x = {a = 1, b = false} in "
						+ "decl y = {b = true, a = 2} in "
						+ "decl f = fun r:{a:int, b:bool} => "
						+ "		if x.b then x.a else x.a*2 end end in "
						+ "f (x) + f(y) end end end\n");
	}

	@Test
	public void test10Function() throws Exception {
		assertTrue(Interpreter
				.evaluate("decl f = fun x : int => x + 1 end in f(1) end\n")
				.equals(new IntegerValue(2)));		
	}

	@Test
	public void test11FunctionAsArgument() throws Exception {
		assertTrue(Interpreter
				.evaluate("decl f = fun x : int => x + 1 end in "
						 +"decl g = fun f : int -> int => f(1) end in "
						 + " g (f) end end\n")
				.equals(new IntegerValue(2)));		
	}
	
	@Test
	public void test12FunctionAsResult() throws Exception {
		assertTrue(Interpreter
				.evaluate("decl f = fun x : int => fun y : int => x + y end end in "
						+ "decl g = f (1) in "
						+ "g (2) + g(3) end end\n")
				.equals(new IntegerValue(7)));		
	}

	@Test
	public void test13FunctionWithMultipleParams() throws Exception {
		assertTrue(Interpreter
				.evaluate("decl f = fun x : int, y : int => x + y end in "
						+ "f (1,2) + f (1,3) end\n")
				.equals(new IntegerValue(7)));		
	}
	
	@Test
	public void test14FunctionWithMultipleParams2() throws Exception {
		assertTrue(Interpreter
				.evaluate("(fun f:(int,int) -> int => f (2,3) end) (fun x:int, y:int => x+y end)\n")
				.equals(new IntegerValue(5)));
	}
	
	@Test
	public void test15Seq() throws Exception {
		assertTrue(Interpreter
				.evaluate("decl x = var(0) in x := 1; *x end\n")
				.equals(new IntegerValue(1)));
	}

	@Test
	public void test16While() throws Exception {
		assertTrue(Interpreter
				.evaluate("decl x = var(0) in while *x < 10 do x := *x + 1 end; *x end\n")
				.equals(new IntegerValue(10)));
	}

	@Test
	public void test17While() throws Exception {
		assertTrue(Interpreter
				.evaluate("decl f = fun x : int => fun y : int => x*y end end in "
						+ "decl x = var(0) y = var(f(0)) in while *x < 10 do "
						+ "x := *x + 1; y := f(*x) end; (*y)(*x) end end\n")
				.equals(new IntegerValue(100)));
	}
}
