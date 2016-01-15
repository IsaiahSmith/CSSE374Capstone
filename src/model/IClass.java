package model;

import java.util.List;

public interface IClass extends Iinterface{
	public void addField(IInnerNode field);
	public void addImplements(Iinterface inface);
	
	public List<IInnerNode> getFields();
	public List<Iinterface> getInterfaces();
}
