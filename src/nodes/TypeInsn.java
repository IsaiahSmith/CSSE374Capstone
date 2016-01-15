package nodes;

import model.ITypeInsn;

public class TypeInsn implements ITypeInsn {

	private String Type;
	
	public TypeInsn() {
		Type = new String();
	}
	
	@Override
	public void setType(String type) {
		this.Type = type;

	}

	@Override
	public String getType() {
		return this.Type;
	}
	
	@Override
	public String toString() {
		return Type;
	}
	
}
