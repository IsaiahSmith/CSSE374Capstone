package parser.visitors.classvisitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import model.IFile;
import model.IMethod;
import model.INode;
import nodes.ArgumentNode;
import nodes.FileNode;
import nodes.MethodNode;
import parser.visitors.methodvisitors.MethodInsnVisitor;
import parser.visitors.methodvisitors.MethodTypeInsnVisitor;

public class ClassMethodVisitor extends ClassVisitor{
	
	private IFile node;
	private IMethod method;
	
	public ClassMethodVisitor(int api) {
		super(api);
	}
	
	public ClassMethodVisitor(int api, ClassVisitor decorated) {
		super(api, decorated);
		this.node = new FileNode();
	}
	
	public ClassMethodVisitor(int api, ClassVisitor decorated, IFile node) {
		super(api, decorated);
		this.node = node;
	}
	
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		method = new MethodNode();
		
		method.setName(name);
		
		addArguments(desc, name);
		addReturnType(desc);
		addAccessLevel(access);
		
		method.setClassName(this.node.getName());
		
		method.setSignature(signature);
		
				
		MethodVisitor body = new MethodInsnVisitor(Opcodes.ASM5, toDecorate, method);
		MethodVisitor Type = new MethodTypeInsnVisitor(Opcodes.ASM5, body, method);
		node.addMethod(method);
		return Type;
	}
	
	
	private void addArguments(String desc, String name) {
		Type[] args = Type.getArgumentTypes(desc);
		
		
		for(int i=0; i<args.length; i++){
			INode argNode = new ArgumentNode();
			argNode.setName("arg"+i);
			argNode.setType(args[i].getClassName());
			method.addArg(argNode);
		}
	}
	
	private void addReturnType(String desc) {
		String returnType = Type.getReturnType(desc).getClassName();
		
		method.setType(returnType);
	}
	
	private void addAccessLevel(int access) {
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
		this.method.setAccessLevel(level);
	}
}
