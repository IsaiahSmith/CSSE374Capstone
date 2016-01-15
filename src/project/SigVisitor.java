package project;

import org.objectweb.asm.signature.SignatureVisitor;

public class SigVisitor extends SignatureVisitor {

	public SigVisitor(int arg0) {
		super(arg0);
	}
	
	@Override
	public void visitClassType(String name){
		
	}

}
