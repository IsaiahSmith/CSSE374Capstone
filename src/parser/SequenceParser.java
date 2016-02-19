package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Set;

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
import parser.visitors.classvisitors.ClassDeclarationVisitor;
import parser.visitors.classvisitors.ClassMethodVisitor;

public class SequenceParser extends Observable implements Parser<IFile> {
	private int depth;
	private Set<IFile> files ;
	private Set<String> signatures;
	
	
	public SequenceParser(Set<IFile> files, Set<String> signatures, int depth) {
		this.files=files;
		this.signatures=signatures;
		this.depth=depth;
	}

	public Set<IFile> parse() {
		for(String signature : this.signatures) {
			this.addSignature(this.findMethod(signature), this.depth);
		}
		return this.files;
	}
	
	private void addSignature(IMethod method, int depth) {
		depth--;
		String cName = method.getClassName();
		
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
	
	private IMethod findMethod(String signature) {
		for(IFile file : this.files) {
			for(IMethod method : file.getMethods()) {
				IMethod tempMethod = new MethodNode();
				tempMethod.setName(this.getSignatureMethodName(signature));
				tempMethod.setClassName(this.getSignatureClassName(signature));
				for(String arg : this.getSignatureArguments(signature)) {
					INode tempArgument = new ArgumentNode();
					tempArgument.setType(arg);
					tempMethod.addArg(tempArgument);
				}
				if(method.equals(tempMethod)) {
					return method;
				}
			}
		}
		return null;
	}
	
	private String getSignatureClassName(String fullyQualifiedSignature) {
		String[] signatureSplit = fullyQualifiedSignature.split(":");
		
		return signatureSplit[0];
	}
	
	private String getSignatureMethodName(String fullyQualifiedSignature) {
		String[] fullyQualifiedSignatureSplit = fullyQualifiedSignature.split(":");
		String[] methodSignatureSplit = fullyQualifiedSignatureSplit[0].split("(");
		return methodSignatureSplit[0];
	}
	
	private List<String> getSignatureArguments(String fullyQualifiedSignature) {
		String[] fullyQualifiedSignatureSplit = fullyQualifiedSignature.split(":");
		String[] methodSignatureSplit = fullyQualifiedSignatureSplit[0].split("(");
		String[] argumentsSplit = methodSignatureSplit[0].split(")");
		String[] argumentsArray = argumentsSplit[0].split(", ");
		List<String> arguments = new ArrayList<String>();
		for(String arg : argumentsArray){
			arguments.add(arg);
		}
		return arguments;
	}
	
}
