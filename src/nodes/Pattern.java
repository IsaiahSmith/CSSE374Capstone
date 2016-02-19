package nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.IArrow;
import model.IFile;
import model.IPattern;

public class Pattern implements IPattern {
	
	private String name;
	private String node;
	private IArrow arrow;
	private int instance;
	private boolean root;
	
	public Pattern(String name){
		this.name = name;
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

}
