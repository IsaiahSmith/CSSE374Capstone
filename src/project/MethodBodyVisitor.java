package project;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import model.IMethod;
import model.IMethodInsn;
import model.ITypeInsn;
import nodes.MethodInsn;
import nodes.TypeInsn;

public class MethodBodyVisitor extends MethodVisitor{

	private IMethod method;

	public MethodBodyVisitor(int api, MethodVisitor decorated) {
		super(api, decorated);
	}
	
	public MethodBodyVisitor(int api, MethodVisitor toDecorate, IMethod method) {
		super(api, toDecorate);
		this.method = method;
	}
	
	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
		super.visitMethodInsn(opcode, owner, name, desc, itf);
		IMethodInsn methodInsn = new MethodInsn();
		methodInsn.setOwner(sanitize(owner));
		methodInsn.setDesc(sanitize(desc));
		methodInsn.setName(name);
		this.method.addMethodInsn(methodInsn);
		
		
//		System.out.println("--vistMethodInsn--");
//		System.out.println("opcode: "+opcode);
//		System.out.println("owner: "+owner);
//		System.out.println("name: "+name);
//		System.out.println("desc: "+desc);
//		System.out.println("itf: "+itf);
	}

	
	@Override
	public void visitTypeInsn(int opcode, String type) {
		super.visitTypeInsn(opcode, type);
		if((opcode&Opcodes.NEW)!=0){
			ITypeInsn typeInsn = new TypeInsn();
			typeInsn.setType(sanitize(type));
			this.method.addTypeInsn(typeInsn);
		}
		
//		System.out.println("--vistTypeInsn--");
//		System.out.println("opcode: "+opcode);
//		System.out.println("type: "+type);
	}
	private String sanitize(String input) {
		String temp = input.replace("/", "_");
		temp = temp.replace(".", "_");
		
		return temp;
	}
}
