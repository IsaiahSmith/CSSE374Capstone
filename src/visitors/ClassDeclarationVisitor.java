package visitors;

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
		this.node.setName(sanitize(name));
		addType(access);
		if (superName != null) {
			this.node.setSuperName(sanitize(superName));
		}
		List<String> inters = new ArrayList<String>();
		for (String s : interfaces) {
			inters.add(s);
			IFile inface = new FileNode();
			inface.setName(sanitize(s));
			node.addImplements(inface);
		}		
		
		super.visit(version, access, name, signature, superName, interfaces);
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
	
	private String sanitize(String input) {
		String temp = input.replace("/", "_");
		temp = temp.replace(".", "_");
		
		return temp;
	}
}
