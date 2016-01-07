package project;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.ClassVisitor;

import org.objectweb.asm.ClassVisitor;

public class ClassDeclarationVisitor extends ClassVisitor {
	
	public Map<String, String> classes;
	
	public ClassDeclarationVisitor(int api) {
		super(api);
		classes = new HashMap<String, String>();
	}
	
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		// TODO: delete the line below
		System.out.println("Class: "+name+" extends "+superName+" implements "+Arrays.toString(interfaces));
		//TODO: construct an internal representation of the class for later use
		// by decorators
		
		classes.put("Class", name);
		classes.put("extends", superName);
		classes.put("implements", Arrays.toString(interfaces));
		
		super.visit(version, access, name, signature, superName, interfaces);
	}

}
