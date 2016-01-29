package parser.visitors.methodvisitors;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import model.IMethod;
import model.ITypeInsn;
import nodes.TypeInsn;

public class MethodTypeInsnVisitor extends MethodVisitor {

	private IMethod method;
	
	public MethodTypeInsnVisitor(int api, MethodVisitor decorated) {
		super(api, decorated);
	}

	
	public MethodTypeInsnVisitor(int api, MethodVisitor toDecorate, IMethod method) {
		super(api, toDecorate);
		this.method = method;
	}
	
	@Override
	public void visitTypeInsn(int opcode, String type) {
		super.visitTypeInsn(opcode, type);
		if((opcode&Opcodes.NEW)!=0){
			ITypeInsn typeInsn = new TypeInsn();
			typeInsn.setType(type);
			this.method.addTypeInsn(typeInsn);
		}
	}
}
