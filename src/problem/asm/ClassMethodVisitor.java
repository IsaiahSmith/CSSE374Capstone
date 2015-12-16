package problem.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ClassMethodVisitor extends ClassVisitor {

	public ClassMethodVisitor(int api) {
		super(api);
	}
	
	public ClassMethodVisitor(int api, ClassVisitor decorated) {
		super(api, decorated);
	}
	
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		// TODO: delete the line below
		System.out.println("method " + name);
		// TODO: create an internal representation of the current method and pass it to the methods below
		addAccessLevel(access);
		addReturnType(desc);
		addArguments(desc);
		// TODO: add the current method to your internal representation of the current class
		// What is a good way for the code to remember what the current class is?
		return toDecorate;
	}

	private void addArguments(String desc) {
		Type[] args = Type.getArgumentTypes(desc);
		for(int i=0; i<args.length; i++){
			String arg=args[i].getClassName();
			System.out.println("arg "+i+": "+arg);
		}
		
	}

	private void addReturnType(String desc) {
		String returnType = Type.getReturnType(desc).getClassName();
		
		System.out.println("return type: " + returnType );
		
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
		// TODO: delete the next line
		System.out.println("access level: "+level);
		// TODO: ADD this information to your representation of the current method.
		
	}

}
