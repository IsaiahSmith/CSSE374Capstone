package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import api.UmlGeneratorApi;
import net.miginfocom.swing.MigLayout;

public class CheckBoxTree extends JPanel {
	private UmlGeneratorApi api;
	Map<String, List<String>> strMap;
	Map<JCheckBox, List<JCheckBox>> cboxMap;
	int row = 0;
	int col = 0;
	private ArrayList<String> patterns;
	private ArrayList<String> cinstances;
	private ArrayList<String> dinstances;
	private ArrayList<String> sinstances;
	
	private JLabel spacer = new JLabel();

	public CheckBoxTree(UmlGeneratorApi api) {		
//		this.patterns = new ArrayList<String>();
//		this.patterns.add("Decorator");
//		this.patterns.add("Composite");
//		this.patterns.add("Singleton");
//		
//		this.dinstances = new ArrayList<String>();
//		this.dinstances.add("Class 1");
//		this.dinstances.add("Class a");
//		this.dinstances.add("Class b");
//		
//		this.cinstances = new ArrayList<String>();
//		this.cinstances.add("Class 2");
//		
//		this.sinstances = new ArrayList<String>();
//		this.sinstances.add("Class 3");
//
//		strMap = new HashMap<String, List<String>>();
//		
//		strMap.put(this.patterns.get(0), dinstances);
//		strMap.put(this.patterns.get(1), cinstances);
//		strMap.put(this.patterns.get(2), sinstances);
		
		// TODO: uncomment this when you're ready to use the real thing
		strMap = api.getPatternRoots();
		
		
		this.setPreferredSize(new Dimension(380, 710));
		this.setLayout(new MigLayout());
	}

	public void generate() {
		this.cboxMap = new HashMap<JCheckBox, List<JCheckBox>>();
		
		for(String pattern : this.strMap.keySet()) {
			JCheckBox pattbox = new JCheckBox(pattern);
			this.add(pattbox, "cell " + col + " " + row);
			
			List<JCheckBox> cboxList = new ArrayList<JCheckBox>();
			col += 1;
			for(String root : this.strMap.get(pattern)) {
				JCheckBox rootbox = new JCheckBox(root);
				rootbox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(rootbox.isSelected()) {
							// TODO: remove it for root
							CheckBoxTree.this.api.removePatternRoot(root);
						}else {
							// TODO: do api call for root
							CheckBoxTree.this.api.addPatternRoot(root);
						}
					}
				});
				row += 1;
				CheckBoxTree.this.add(rootbox, "cell " + col + " " + row);
				CheckBoxTree.this.revalidate();
				cboxList.add(rootbox);
			}
			col -= 1;
			
			this.cboxMap.put(pattbox, cboxList);
			
			pattbox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(pattbox.isSelected()) {
						for(JCheckBox cb : cboxList) {
							cb.setSelected(true);
						}
						// TODO: do api call for pattern
						CheckBoxTree.this.api.addPattern(pattern);
					}else {
						for(JCheckBox cb : cboxList) {
							cb.setSelected(false);
						}
						// TODO: remove it for pattern
						CheckBoxTree.this.api.removePattern(pattern);
					}
				}
			});
			row += 1;
		}
	}
	
	
}
