package project;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class ClassDeclarationVisitor extends ClassVisitor {
	
	private ClassBuilder cls;
	
	public ClassDeclarationVisitor(int api, ClassBuilder cls) {
		super(api);
		this.cls = cls;
	}
	
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		this.cls.name = name;
		if (superName != null)
			this.cls.superName = superName;
		if((access&Opcodes.ACC_INTERFACE)!=0){
			this.cls.isClass=false;
		}
		List<String> inters = new ArrayList<String>();;
		for (String s : interfaces) {
			inters.add(s);
		}
		this.cls.inter = inters;
		
		
		super.visit(version, access, name, signature, superName, interfaces);
	}

}
