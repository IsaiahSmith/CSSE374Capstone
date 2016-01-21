package project;

import java.util.Set;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class SequenceMethodVisitor extends ClassMethodVisitor {
	
	//Might want to change this later
	private String methodName;
	private String signature;
	private Set<SequenceMethodInsnVisitor> methodInsnVisitors;
	private SequenceDesignBuilder sequenceBuilder;

	public SequenceMethodVisitor(int api) {
		super(api);
	}
	
	public SequenceMethodVisitor(int api, ClassVisitor toDecorate) {
		super(api, toDecorate);
	}
	
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		
		if(name.equals(this.methodName) && desc.equals(this.signature)) {
			SequenceMethodInsnVisitor methodInsnVisitor = new SequenceMethodInsnVisitor(Opcodes.ASM5, toDecorate);
			
			methodInsnVisitor.setSequenceBuilder(this.sequenceBuilder);
			this.methodInsnVisitors.add(methodInsnVisitor);
			return methodInsnVisitor;
		}
		
		return toDecorate;
	}
	
	public void setParams(String methodName, String signature, SequenceDesignBuilder sequenceBuilder) {
		this.methodName = methodName;
		this.signature = signature;
		this.sequenceBuilder = sequenceBuilder;
	}
	

}
