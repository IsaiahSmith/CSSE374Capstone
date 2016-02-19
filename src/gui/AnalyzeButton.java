package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import api.UmlGeneratorApi;
import nodes.Model;

public class AnalyzeButton extends JButton {
	private JFrame mainframe;
	private JPanel mainpanel;
	private JPanel progpanel;
	private JButton loadButton;
	private UmlGeneratorApi api;
	
	public AnalyzeButton(JFrame mainframe, JPanel mainpanel, JPanel progpanel, JButton loadButton, UmlGeneratorApi api) {
		this.mainframe = mainframe;
		this.mainpanel = mainpanel;
		this.progpanel = progpanel;
		this.loadButton = loadButton;
		this.api = api;
		
		this.setText("Analyze");
		this.setEnabled(false);
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: make sure there is a file in the directory, hint: use api
				if(api.hasConfigFile() == false){
					// don't do anything
					
				}else {
					// TODO: do the prog bar stuff
					JProgressBar pbar = new JProgressBar();
					pbar.setValue(50);
					AnalyzeButton.this.progpanel.add(pbar);
					AnalyzeButton.this.progpanel.revalidate();
					AnalyzeButton.this.setText("Analyzing...");
					AnalyzeButton.this.setEnabled(false);
					AnalyzeButton.this.loadButton.setEnabled(false);
					AnalyzeButton.this.api.buildModel();
					// wait for pbar to finish then go to result
					 try {
						 goToResult();
					} catch (IOException e1) {
						e1.printStackTrace();
					}					
				}
				
			}
		});
	}

	protected void goToResult() throws IOException {
		AnalyzeButton.this.mainpanel.removeAll();
		AnalyzeButton.this.mainpanel.revalidate();
		AnalyzeButton.this.mainframe.getContentPane().removeAll();
		AnalyzeButton.this.mainframe.getContentPane().revalidate();
		AnalyzeButton.this.mainframe.setBackground(Color.BLACK);
		AnalyzeButton.this.mainframe.add(AnalyzeButton.this.mainpanel);
		
		new ResultGUI(this.mainframe, this.mainpanel, this.api);		
	}
}
