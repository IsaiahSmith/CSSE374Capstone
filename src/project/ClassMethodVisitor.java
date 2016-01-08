package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ClassMethodVisitor extends ClassVisitor{
	
	private ClassBuilder cls;
	private Map<String, String> newMethod;
	private Map<String, List<String>> argsMap;
	
	public ClassMethodVisitor(int api) {
		super(api);
	}
	
	public ClassMethodVisitor(int api, ClassVisitor decorated) {
		super(api, decorated);
		this.cls=new ClassBuilder();
	}
	public ClassMethodVisitor(int api, ClassVisitor decorated, ClassBuilder cls) {
		super(api, decorated);
		this.cls = cls;
	}
	
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		this.newMethod = new HashMap<String, String>();
		this.newMethod.put("Name", name);
		addArguments(desc, name);
		addReturnType(desc);
		addAccessLevel(access);
		
		
		cls.methods.add(this.newMethod);
		
		return toDecorate;
	}
	
	private void addArguments(String desc, String name) {
		Type[] args = Type.getArgumentTypes(desc);
		String arguments = "";
		ArrayList<String> methodArgs = new ArrayList<String>();
		
		for(int i=0; i<args.length; i++){
			String arg=args[i].getClassName();
			String[] argSplit = arg.split("\\.");
			arg = argSplit[argSplit.length-1];
			arguments += "arg"+i+": "+arg+", ";
			methodArgs.add(arg);
		}
		if(args.length > 0) {
			this.newMethod.put("args", arguments.substring(0, arguments.length() - 2));
			this.argsMap.put(name, methodArgs);
		}
	}
	
	private void addReturnType(String desc) {
		String returnType = Type.getReturnType(desc).getClassName();
		this.newMethod.put("ReturnType", returnType);
		
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
		this.newMethod.put("AccessLevel", level);
	}
}
