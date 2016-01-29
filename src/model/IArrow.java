package model;

public interface IArrow {
	public void setType(String type);
	public void setOrigin(String origin);
	public void setEnd(String end);
	
	public String getType();
	public String getOrigin();
	public String getEnd();
}
