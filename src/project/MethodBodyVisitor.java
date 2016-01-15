package project;

import java.util.HashMap;
import java.util.List;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import model.IFile;

public class MethodBodyVisitor extends MethodVisitor{

	private ClassBuilder cls;
	private IFile node;

	public MethodBodyVisitor(int api, MethodVisitor decorated) {
		super(api, decorated);
		this.cls=new ClassBuilder();
	}

	public MethodBodyVisitor(int api, MethodVisitor toDecorate, ClassBuilder cls) {
		super(api, toDecorate);
		this.cls= cls;
	}
	
	public MethodBodyVisitor(int api, MethodVisitor toDecorate, ClassBuilder cls, IFile node) {
		super(api, toDecorate);
		this.cls= cls;
		this.node = node;
	}
	
	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
		super.visitMethodInsn(opcode, owner, name, desc, itf);
		System.out.println("--vistMethodInsn--");
		System.out.println("opcode: "+opcode);
		System.out.println("owner: "+owner);
		System.out.println("name: "+name);
		System.out.println("desc: "+desc);
		System.out.println("itf: "+itf);
	}

	@Override
	public void visitFieldInsn(int opcode, String owner, String name, String desc) {
		System.out.println("--vistFieldInsn--");
		System.out.println("opcode: "+opcode);
		System.out.println("owner: "+owner);
		System.out.println("name: "+name);
		System.out.println("desc: "+desc);
		
	}
	
	@Override
	public void visitTypeInsn(int opcode, String type) {
		System.out.println("--vistTypeInsn--");
		System.out.println("opcode: "+opcode);
		System.out.println("type: "+type);
	}
	
	@Override
	public void visitVarInsn(int opcode, int var) {
		System.out.println("--vistVarInsn--");
		System.out.println("var: "+var);
	}
}
