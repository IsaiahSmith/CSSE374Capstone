package project;

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
import nodes.MethodNode;

public class ClassMethodVisitor extends ClassVisitor{
	
	private IFile node;
	private IMethod method;
	
	public ClassMethodVisitor(int api) {
		super(api);
	}
	
	public ClassMethodVisitor(int api, ClassVisitor decorated) {
		super(api, decorated);
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
		
		
		MethodVisitor body = new MethodBodyVisitor(Opcodes.ASM5, toDecorate, method);
		node.addMethod(method);
		return body;
	}
	
	
	private void addArguments(String desc, String name) {
		Type[] args = Type.getArgumentTypes(desc);
		
		
		for(int i=0; i<args.length; i++){
			INode argNode = new ArgumentNode();
			argNode.setName("arg"+i);
			argNode.setType(sanitize(args[i].getClassName()));
			method.addArg(argNode);
		}
	}
	
	private void addReturnType(String desc) {
		String returnType = Type.getReturnType(desc).getClassName();
		
		method.setType(sanitize(returnType));
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
	private String sanitize(String input) {
		String temp = input.replace("/", "_");
		temp = temp.replace(".", "_");
		
		return temp;
	}
}
