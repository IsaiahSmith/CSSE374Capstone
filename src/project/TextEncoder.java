package project;

import java.util.List;

public class TextEncoder implements IEncoder {

	@Override
	public StringBuilder encode(List<ClassBuilder> Classes, boolean includeAll) {
		StringBuilder str = new StringBuilder();
		for(int i=0;i<Classes.size();i++){
			str.append(Classes.get(i)+"\n");
		}
		return str;
	}

}
