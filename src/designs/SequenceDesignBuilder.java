package designs;

import java.util.List;

import model.IModel;
import nodes.Model;

public class SequenceDesignBuilder implements DesignBuilder {
	
	private List<String> fileNames;
	private List<String> methodSignatures;
	private int depth;

	public SequenceDesignBuilder(List<String> fileNames, List<String> methodSignatures, int depth) {
		
		this.fileNames = fileNames;
		this.methodSignatures = methodSignatures;
		this.depth = depth;
	}
	
	@Override
	public IModel build() {
		return new Model();
//		return new Model
//				.Builder(fileNames)
//				.sequences(this.methodSignatures, depth)
//				.build();
	}

}
