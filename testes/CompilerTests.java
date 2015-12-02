package testes;

import parser.*;
import Types.*;
import exceptions.*;
import Values.*;
import AST.*;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import main.Compiler;
import parser.ParseException;
import main.*;
import exceptions.*;
import Values.*;

public class CompilerTests {

	private void testCase(String expression, String result) throws IOException, InterruptedException, ParseException,
			UndeclaredIdentifierException, DuplicateIdentifierException, TypeErrorException, FileNotFoundException {
		Process p;
		
		p = Runtime.getRuntime().exec(new String[]{"sh", "-c", "rm *.j *.class"});
	    p.waitFor();	    

	    System.out.println("Compiling to Jasmin source code");

	   // Compiler.compile(expression);
		
	    System.out.println("Compiling to Jasmin bytecode");
	    
		p = Runtime.getRuntime().exec(new String[]{"sh", "-c", "java -jar jasmin.jar *.j"});
	    p.waitFor();	    
	    assertTrue("Compiled to Jasmin bytecode", p.exitValue() == 0);

	    BufferedReader reader = 
		         new BufferedReader(new InputStreamReader(p.getInputStream()));

	    StringBuffer output = new StringBuffer();
        String line = "";			
        while ((line = reader.readLine())!= null) {
        	output.append(line + "\n");
        }
	    System.out.println(output.toString());

		p = Runtime.getRuntime().exec(new String[] {"sh","-c", "java Demo"});
	    p.waitFor();

	    reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

	    output = new StringBuffer();
        line = "";			
        while ((line = reader.readLine())!= null) {
        	output.append(line + "\n");
        }
	    System.out.println("Output: #"+output.toString()+"#");
	    
	    assertTrue(result.equals(output.toString()));
	}

	@Test
	public void BasicTest() throws IOException, InterruptedException, ParseException, UndeclaredIdentifierException, DuplicateIdentifierException, TypeErrorException {
		testCase("1\n", "1\n");
	}
	
	@Test
	public void testDeclarations() throws IOException, InterruptedException, ParseException, UndeclaredIdentifierException, DuplicateIdentifierException, TypeErrorException {
		testCase("decl x = 1 in decl y = x x = 2 in x+y end end\n", "3\n");
	}

	@Test
	public void testVariables() throws IOException, InterruptedException, ParseException, UndeclaredIdentifierException, DuplicateIdentifierException, TypeErrorException {
		testCase("decl x = var(0) in decl y = x := *x + 1 in 2 * *x end end\n", "2\n");
	}

}
