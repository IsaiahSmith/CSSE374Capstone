package visitors;

import org.objectweb.asm.MethodVisitor;

import model.IMethod;
import nodes.MethodNode;

public class SequenceMethodInsnVisitor extends MethodVisitor{
	private SequenceDesignBuilder sequenceBuilder;
	
	public SequenceMethodInsnVisitor(int api, MethodVisitor toDecorate) {
		super(api, toDecorate);
	}
	
	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
		IMethod signature = new MethodNode();
		signature.setClassName(owner);
		signature.setName(name);
		signature.setSignature(desc);
		
		if(this.sequenceBuilder.getDepth() > 1) {
			SequenceDesignBuilder newSequenceBuilder = new SequenceDesignBuilder(signature, this.sequenceBuilder);
			
			newSequenceBuilder.build();
		}
	}
	
	public void setSequenceBuilder(SequenceDesignBuilder sequenceBuilder) {
		this.sequenceBuilder = sequenceBuilder;
	}

}
