package project;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ClassFieldVisitor extends ClassVisitor{
	
	private ClassBuilder cls;
	private Map<String, String> newField;
	
	public ClassFieldVisitor(int api) {
		super(api);
	}
	
	public ClassFieldVisitor(int api, ClassVisitor decorated) {
		super(api, decorated);
		this.cls = new ClassBuilder();
	}
	
	public ClassFieldVisitor(int api, ClassVisitor decorated, ClassBuilder cls) {
		super(api, decorated);
		this.cls = cls;
	}
	
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		this.newField = new HashMap<String, String>();
		
		this.newField.put("Name", name);
		addAccessLevel(access);
		addType(desc);
		
		this.cls.fields.add(this.newField);
		
		return toDecorate;
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
		this.newField.put("AccessLevel", level);
	}
	private void addType(String desc) {
		String type = Type.getReturnType(desc).getClassName();
		this.newField.put("Type", type);
		
	}
}
