package encoders.dot;

import java.util.HashMap;
import java.util.Map;

import model.IArrow;
import model.IModel;
import model.IPattern;

public class DotArrowDecorator extends DotDecorator{

	private Map<String, String> patternStyles;
	private Map<String, String> arrowHeads;
	private Map<String, String> arrowStyles;

	public DotArrowDecorator(Dot dot, IModel model) {
		super(dot, model);
		this.patternStyles = new HashMap<String, String>();
		//add fancy words to arrows here
		this.arrowHeads = new HashMap<String, String>();
		this.arrowHeads.put("Association", "vee");
		this.arrowHeads.put("Implements", "onormal");
		this.arrowHeads.put("Inheritance", "normal");
		this.arrowHeads.put("Uses", "vee");
		this.arrowStyles = new HashMap<String, String>();
		this.arrowStyles.put("Association", "normal");
		this.arrowStyles.put("Implements", "dashed");
		this.arrowStyles.put("Inheritance", "normal");
		this.arrowStyles.put("Uses", "dashed");
	}
	
	public StringBuilder getDot() {
		String str = new String();
		for(IArrow arrow:model.getArrows()) {
			str+=arrow.getOrigin()+"->"+arrow.getEnd()
				+" [arrowhead="
				+arrowHeads.get(arrow.getType())+" style="
				+arrowStyles.get(arrow.getType());
			
			for(IPattern pattern:model.getPatterns()) {
				if(pattern.getArrow().equals(arrow))
					str+=" "+patternStyles.get(pattern.getName());
			}
			str+="]\n\t";
			
		}
		return super.getDot().append(str);
	}

}
