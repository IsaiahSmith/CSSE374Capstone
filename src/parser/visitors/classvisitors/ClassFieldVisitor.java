package parser.visitors.classvisitors;


import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import model.IFile;
import model.IInnerNode;
import nodes.FieldNode;

public class ClassFieldVisitor extends ClassVisitor{
	
	private IFile node;
	private IInnerNode field;
	
	public ClassFieldVisitor(int api) {
		super(api);
	}
	
	public ClassFieldVisitor(int api, ClassVisitor decorated) {
		super(api, decorated);
	}
	
	public ClassFieldVisitor(int api, ClassVisitor decorated, IFile node) {
		super(api, decorated);
		this.node = node;
	}
	
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		this.field = new FieldNode();
		
		this.field.setName(name);
		
		addAccessLevel(access);
		addType(desc);
		
		this.node.addField(field);
		
		return toDecorate;
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
		this.field.setVisibility(level);
		this.field.addModifier(modifier);
	}
	private void addType(String desc) {
		String type = Type.getReturnType(desc).getClassName();
		this.field.setType(type);
		
	}
}
