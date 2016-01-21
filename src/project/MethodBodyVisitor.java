package project;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import model.IMethod;
import model.IMethodInsn;
import model.INode;
import model.ITypeInsn;
import nodes.ArgumentNode;
import nodes.MethodInsn;
import nodes.MethodNode;
import nodes.TypeInsn;

public class MethodBodyVisitor extends MethodVisitor{

	private IMethod method;
	private MethodVisitor toDecorate;

	public MethodBodyVisitor(int api, MethodVisitor decorated) {
		super(api, decorated);
	}
	
	public MethodBodyVisitor(int api, MethodVisitor toDecorate, IMethod method) {
		super(api, toDecorate);
		this.method = method;
		this.toDecorate = toDecorate;
	}
	
	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
		super.visitMethodInsn(opcode, owner, name, desc, itf);
//		IMethodInsn methodInsn = new MethodInsn();
//		methodInsn.setOwner(sanitize(owner));
//		methodInsn.setDesc(sanitize(desc));
//		methodInsn.setName(name);
		
		IMethod innerMethod = new MethodNode();
		innerMethod = addArguments(desc, innerMethod);
		innerMethod = addReturnType(desc, innerMethod);
		innerMethod = addAccessLevel(opcode, innerMethod);
		innerMethod.setName(name);
		
		System.out.println("ClassName: "+owner+"\nName: "+name+"\nDesc: "+desc);
		
		this.method.addInnerMethod(innerMethod);
		
		
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
	
	private IMethod addArguments(String desc, IMethod m) {
		Type[] args = Type.getArgumentTypes(desc);
		
		
		for(int i=0; i<args.length; i++){
			INode argNode = new ArgumentNode();
			argNode.setName("arg"+i);
			argNode.setType(sanitize(args[i].getClassName()));
			m.addArg(argNode);
		}
		
		return m;
	}
	private IMethod addAccessLevel(int access, IMethod m) {
		String level="";
		if((access&Opcodes.ACC_PUBLIC)!=0){
			level="public";
		}else if((access&Opcodes.ACC_PROTECTED)!=0){
			level="protected";
		}else if((access&Opcodes.ACC_PRIVATE)!=0){
			level="private";
		}else{
			level="default";
		}
		m.setAccessLevel(level);
		return m;
	}
	
	private IMethod addReturnType(String desc, IMethod m) {
		String returnType = Type.getReturnType(desc).getClassName();
		
		m.setType(sanitize(returnType));
		return m;
	}
	
	private String sanitize(String input) {
		String temp = input.replace("/", "_");
		temp = temp.replace(".", "_");
		
		return temp;
	}
}
