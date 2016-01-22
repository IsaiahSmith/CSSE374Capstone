package builders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import model.IFile;
import model.IMethod;
import model.IModel;
import model.INode;
import nodes.ArgumentNode;
import nodes.FileNode;
import nodes.MethodNode;
import nodes.Model;
import visitors.ClassDeclarationVisitor;
import visitors.ClassMethodVisitor;

public class SequenceDesignBuilder implements IDesignBuilder{
	
	
	private int depth;
	private IFile file;
	
	public SequenceDesignBuilder(String className, String methodName, List<String>args, int depth) {
		this.depth = depth;
		this.file = new FileNode();
		this.file.setName(className);
		IMethod method = new MethodNode();
		method.setClassName(className);
		method.setName(methodName);
		for (String arg : args) {
			INode argNode = new ArgumentNode();
			argNode.setType(arg);
			method.addArg(argNode);
		}
		this.file.addMethod(method);
	}

	public IModel build() {
		
		addSignature(this.file.getMethods().get(0), this.depth);
		
		IModel model = new Model();
		model.addFile(this.file);
		return model;
	}
	
	private void addSignature(IMethod method, int depth) {
		depth--;
		//### define root signature ###
		String cName = method.getClassName();
		String mName = method.getName();
		List<String> mArgs = new ArrayList<String>();
		for(INode arg: method.getArgs()) {
			mArgs.add(arg.getType());
		}
		//## Finished defining root signature ##
		
		IFile tempNode = new FileNode();
		try {
			ClassReader reader = new ClassReader(cName);
			ClassVisitor decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, tempNode);
			ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, decVisitor, tempNode);
			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
		} catch (IOException e) {
			System.err.println("ERROR: "+cName+" could not be found!");
		}
		//System.out.println("\nTempNode: "+tempNode.toString());
		
		
		for(IMethod m: tempNode.getMethods()) {
			//find the method of the class we want
			//System.out.println("\n\nPassed Method: "+method.toString()+"\nFound Method: "+m.toString()+"\nEquals: "+m.equals(method));
			
			if(m.equals(method)) {
				for(IMethod innerMethod: m.getInnerMethods()) {
					method.addInnerMethod(innerMethod);
					//printing inner method to find out what a method bottom level method looks like.
					System.out.println("\nInnerMethod: "+innerMethod);
					System.out.println("Depth: "+depth);
					//need to add OR statement that checks if the innermethod is at the lowest level.
					if(depth != 0 && !innerMethod.getName().equals("<init>")) {
						addSignature(innerMethod, depth);
					}
				}
			}
		}
		
	}
	
	private String reformat(String input) {
		return input.replaceAll("_", ".");
	}
}
