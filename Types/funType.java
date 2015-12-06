package Types;

import java.util.ArrayList;

public class funType implements Type{
	
	Type parType, resultType;

	
	public funType(Type parType, Type exp){
		this.parType = parType;
		this.resultType = exp;
	}


	public Type getParType() {
		return parType;
	}
	
	public Type getResultType() {
		return resultType;
	}
}
