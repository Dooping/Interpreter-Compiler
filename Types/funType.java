package Types;

import java.util.ArrayList;

public class funType implements Type{
	
	//Type parType, resultType;
	Type resultType;
	ArrayList<Type> parTypes;

	
	public funType(Type parType, Type exp){
		//this.parType = parType;
		parTypes = new ArrayList<Type>();
		this.resultType = exp;
	}
	
	void addParameters(Type parTyep){
		if (parType instanceof PairType){
			partTyp...parTyepa
			addParame(parType.getLeft)
			addP..(parType.getRig)
		}
		else
			parTypes.add();
	}
	
	public funType(ArrayList<Type> parType, Type exp){
		this.parTypes = parType;
		this.resultType = exp;
	}

	public ArrayList<Type> getParType() {
		return parTypes;
	}
	
	public Type getResultType() {
		return resultType;
	}
}
