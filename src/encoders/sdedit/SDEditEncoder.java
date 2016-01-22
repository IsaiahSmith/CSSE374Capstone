package encoders.sdedit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import encoders.IEncoder;
import model.IMethod;
import model.IModel;
import model.INode;

public class SDEditEncoder implements IEncoder {

	List<String> classes;
	List<String> madeClasses;
	List<String> signatures;
	List<String> news;
	
	@Override
	public StringBuilder encode(IModel model, boolean IncludeAll) {
		
		IMethod method = model.getFiles().get(0).getMethods().get(0);
		this.classes = new ArrayList<String>();
		this.madeClasses = new ArrayList<String>();
		this.signatures = new ArrayList<String>();
		this.news = new ArrayList<String>();
		getClasses(method);
		getSignatures(method);
		StringBuilder str = new StringBuilder();
		return str.append(compose());
	}

	private void getClasses(IMethod method) {
		if(!this.classes.contains(method.getClassName())){
			this.classes.add(method.getClassName());
		}
		if(method.getName().equals("<init>")){
			if(!this.madeClasses.contains(method.getClassName()))
				this.madeClasses.add(method.getClassName());
		}
		
		if(method.getInnerMethods().size()!=0){
			for(IMethod m:method.getInnerMethods()){
				getClasses(m);
			}
		}
		
	}
	
	private void getSignatures(IMethod method) {
		if(method.getName().equals("<init>") && this.news.contains(method.getClassName())){
			return;
		}
		String sig = new String();
		String tempArgsList;
		sig+=method.getParent().getClassName().toLowerCase();
		sig+=":";
		//sig+=method.getType();
		//sig+="=";
		if(!method.getType().equals("void")) {
			sig+=method.getClassName().toLowerCase();
			sig+=".";
		}
		if(method.getName().equals("<init>")){
			sig+=method.getClassName().toLowerCase();
			sig+=".";
			sig+="new";
			this.news.add(method.getClassName());
		} else {
			sig+=method.getName();
			sig+="(";
			tempArgsList = new String();
			for(INode arg: method.getArgs()){
				String[] argSplit = arg.getType().split("\\.");
				tempArgsList+=argSplit[argSplit.length-1]+", ";
			}
			if(method.getArgs().size()>0)
				tempArgsList = tempArgsList.substring(0, tempArgsList.length()-2);
			sig+=tempArgsList;
			sig+=")";
		}
		this.signatures.add(sig);
		if(method.getInnerMethods().size()!=0){
			for(IMethod m:method.getInnerMethods()){
				getSignatures(m);
			}
		}
	}
	
	private String compose() {
		String classBlock = new String();
		
		for(String str:this.classes) {
			if(this.madeClasses.contains(str)) {
				classBlock+="/";
			}
			classBlock+=str.toLowerCase();
			classBlock+=":";
			classBlock+=str;
			classBlock+="[a]\n";
		}
		
		String signatureBlock = new String();
		for(String sig:this.signatures){
			signatureBlock+=sig+"\n";
		}
		
		return classBlock+"\n"+signatureBlock;
	}

}
