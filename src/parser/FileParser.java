package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import model.IFile;
import nodes.FileNode;
import parser.visitors.classvisitors.ClassDeclarationVisitor;
import parser.visitors.classvisitors.ClassFieldVisitor;
import parser.visitors.classvisitors.ClassMethodVisitor;

public class FileParser implements Parser<IFile> {
	private List<String> fileNames;
	
	public FileParser(List<String> fileNames){
		this.fileNames = fileNames;
	}
	
	@Override
	public List<IFile> parse() {
		List<IFile> files = new ArrayList<IFile>();
		for(String className: this.fileNames) {
			
			IFile node = new FileNode();
			
			// ASM's ClassReader does the heavy lifting of parsing the compiled Java class
			ClassReader reader = null;
			try {
				reader = new ClassReader(className);
			} catch (IOException e) {
				System.err.println("ERROR: "+className+" could not be found!");
			}
			// make class declaration visitor to get superclass and interfaces
			ClassVisitor decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, node);
			// DECORATE declaration visitor with field visitor
			ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, decVisitor, node);
			// DECORATE field visitor with method visitor
			ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, node);
			
			//ClassVisitor innerMethodVisitor = new ClassMethodVisitor(Opcodes.ASM5, methodVisitor, node);
			// TODO: add more DECORATORS here in later milestones to accomplish specific tasks
			// Tell the Reader to use our (heavily decorated) ClassVisitor to visit the class
			
			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
			files.add(node);
		}
		return files;
	}

}
