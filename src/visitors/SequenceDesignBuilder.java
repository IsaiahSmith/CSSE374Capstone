package visitors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import model.IMethod;

public class SequenceDesignBuilder {
	
	private int depth;
	private List<IMethod> methodCalls;
	private SequenceDesignBuilder prev;
	private ClassReader reader;
	private IMethod methodSignature;
	private ClassDeclarationVisitor decVisitor;
	private SequenceMethodVisitor methodVisitor;

	public SequenceDesignBuilder(IMethod mSig) {
		this(mSig, null);
	}
	
	public SequenceDesignBuilder(IMethod mSig, int depth) {
		this.depth = depth;
		this.methodCalls = new ArrayList<IMethod>();
		this.addSignature(mSig);
		try {
			this.reader = new ClassReader(mSig.getClassName());
		} catch (IOException e) {
			//no idea what kind of error this is.
			e.printStackTrace();
		}
		this.methodSignature = mSig;
		
		this.decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);
		this.methodVisitor = new SequenceMethodVisitor(Opcodes.ASM5, this.decVisitor);
		this.methodVisitor.setParams(mSig.getName(), mSig.getSignature(), this);
	}

	public SequenceDesignBuilder(IMethod mSig, SequenceDesignBuilder prev) {
		this.prev = prev;
		if(prev == null) {
			this.depth = prev.getDepth() - 1;
		} else {
			this.depth = 5;
			this.methodCalls = new ArrayList<IMethod>();
		}
		this.addSignature(mSig);
		try {
			this.reader = new ClassReader(mSig.getClassName());
		} catch (IOException e) {
			//still dont know
			e.printStackTrace();
		}
		//Might need this don't know yet
		//this.methodSignature = mSig;
		
		this.decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);
		this.methodVisitor = new SequenceMethodVisitor(Opcodes.ASM5, this.decVisitor);
		
		this.methodVisitor.setParams(mSig.getName(), mSig.getSignature(), this);
	}
	
	public List<IMethod> build(ClassVisitor visitor) {
		reader.accept(visitor, ClassReader.EXPAND_FRAMES);
		reader.accept(this.methodVisitor, ClassReader.EXPAND_FRAMES);
		
		return this.methodCalls;
	}
	
	public List<IMethod> build() {
		return this.build(this.getVisitor());
	}
	
	public ClassVisitor getVisitor() {
		return this.decVisitor;
	}
	
	public int getDepth() {
		return this.depth;
	}
	
	private void addSignature(IMethod mSig) {
		if (this.prev != null)
			this.prev.addSignature(mSig);
		else
			this.methodCalls.add(mSig);
		
	}
}
