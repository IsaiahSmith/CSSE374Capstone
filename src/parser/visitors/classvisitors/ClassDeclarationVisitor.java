package parser.visitors.classvisitors;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import model.IFile;
import nodes.FileNode;

public class ClassDeclarationVisitor extends ClassVisitor{
	private IFile node;
	
	public ClassDeclarationVisitor(int api) {
		super(api);
		this.node = new FileNode();
	}
	
	public ClassDeclarationVisitor(int api, IFile node) {
		super(api);
		this.node = node;
	}
	
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		this.node.setName(name);
		addType(access);
		addAccessLevel(access);
		if (superName != null) {
			this.node.setSuperName(superName);
		}
		List<String> inters = new ArrayList<String>();
		for (String s : interfaces) {
			inters.add(s);
			IFile inface = new FileNode();
			inface.setName(s);
			node.addImplements(inface);
		}		
		
		super.visit(version, access, name, signature, superName, interfaces);
	}
	
	private void addAccessLevel(int access) {
		String level="";
		String modifier="";
		if((access&Opcodes.ACC_PUBLIC)!=0){
			level="public";
		}else if((access&Opcodes.ACC_PROTECTED)!=0){
			level="protected";
		}else if((access&Opcodes.ACC_PRIVATE)!=0){
			level="private";
		}else{
			level="default";
		}
		
		if((access&Opcodes.ACC_STATIC)!=0){
			modifier="static";
		}
		this.node.setVisibility(level);
		this.node.addModifier(modifier);
	}
	
	private void addType(int access) {
		String type = "";
		if((access&Opcodes.ACC_INTERFACE)!=0){
			type="interface";
		} else if((access&Opcodes.ACC_ABSTRACT)!=0){
			type="abstract";
		} else {
			type = "class";
		}
		node.setType(type);
	}
	
}
