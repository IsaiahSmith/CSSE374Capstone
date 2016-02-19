package nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.IArrow;
import model.IFile;
import model.IPattern;

public class Pattern implements IPattern {
	
	private String type;
	private String name;
	private String node;
	private IArrow arrow;
	private Integer instance;
	private Boolean root;
	
	public Pattern(String type){
		this.type = type;
		this.name = new String();
		this.arrow = new Arrow();
		this.node = new String();
		this.root= false;
		this.instance = -1;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setNode(String file) {
		this.node = file;
	}

	@Override
	public void setArrow(IArrow arrow) {
		this.arrow = arrow;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getNode() {
		return this.node;
	}

	@Override
	public IArrow getArrow() {
		return this.arrow;
	}

	@Override
	public void setInstance(int instance) {
		this.instance = instance;
		
	}

	@Override
	public void setRoot() {
		this.root = true;
		
	}

	@Override
	public int getInstance() {
		return this.instance;
	}

	@Override
	public boolean isRoot() {
		return this.root;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getType() {
		return this.type;
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof IPattern)){
			return false;
		}
		IPattern other=(IPattern)o;
		
		if(this.type.equals(other.getType()) &&
				(this.root == other.isRoot()) &&
				this.name.equals(other.getName()) &&
				this.node.equals(other.getNode()) &&
				this.arrow.equals(other.getArrow()) &&
				(this.instance == other.getInstance())) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return type.hashCode() +
				root.hashCode() +
				name.hashCode() +
				node.hashCode() +
				arrow.hashCode() +
				instance.hashCode();
	}

}
