package AST;

import java.util.ArrayList;
import java.util.Map;

import Types.*;
import exceptions.*;
import Values.*;
import main.*;

public class ASTCall implements ASTNode {
	
	private ASTNode left,right;
	
	public ASTCall (ASTNode left, ASTNode right){
		this.left = left;
		this.right = right;
	}
	
	public IValue eval(Environ<IValue> env) throws UndeclaredIdentifierException, DuplicateIdentifierException,ExecutionErrorException {
		IValue result;
		IValue v1 = left.eval(env);
		IValue v2 = right.eval(env);
		if(v1 instanceof ClosureValue){
			ClosureValue closure = (ClosureValue)v1;
			String param = closure.getId();
			ASTNode body = closure.getExpr(); //get body
			Environ<IValue> closureEnv = closure.getEnv();
			Environ<IValue> newEnv = closureEnv.beginScope();
			newEnv.assoc(param,v2);
			result = body.eval(newEnv);
			newEnv.endScope();
		}
		else throw new ExecutionErrorException("Call a non-funtional value");
		
		return result;
	}

	public Type typeCheck(Environ<Type> environment) throws UndeclaredIdentifierException, DuplicateIdentifierException,TypeErrorException {
		
		Type leftType = left.typeCheck(environment);
		Type argType = right.typeCheck(environment);
		
		if(leftType instanceof funType){
			funType funT = (funType) leftType;
			//o argumento que passamos por parametro, tem de ser igual ao
			//que declaramos que iamos receber!
			System.out.println(funT.getParType() + " " + argType);
			if(!funT.getParType().toString().equals(argType.toString())){
				throw new TypeErrorException("Expecting same type!");
			}

			if(argType instanceof RecordType){
				RecordType ArgumentType = (RecordType)argType;
				//Já sabemos que são os dois do mesmo tipo!
				RecordType original = (RecordType)funT.getParType();
				ArrayList<Type> typesOfArg = ArgumentType.getTypes();
				ArrayList<Type> typesOfOrig = original.getTypes();
				for (int i = 0; i<typesOfArg.size(); i++){
					if(!typesOfArg.get(i).equals(typesOfOrig.get(i)))
						throw new TypeErrorException("Expecting same type on record!");
				}
			}
				
			return funT.getResultType();
		}
		else throw new TypeErrorException("Calling non-funtion value");
	}
	
	public void compile(CodeBlock code, CompilerFrame env) throws UndeclaredIdentifierException, DuplicateIdentifierException {
		// TODO Auto-generated method stub
		
	}
	
	public String toString(){
		return left.toString() + "(" + right.toString() + ")";
	}
}
