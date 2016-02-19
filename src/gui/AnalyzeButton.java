package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import nodes.Model;

public class AnalyzeButton extends JButton {
	private JFrame mainframe;
	private JPanel mainpanel;
	private JPanel progpanel;
	private JButton loadButton;
	private Object api;
	
	public AnalyzeButton(JFrame mainframe, JPanel mainpanel, JPanel progpanel, JButton loadButton, Object api) {
		this.mainframe = mainframe;
		this.mainpanel = mainpanel;
		this.progpanel = progpanel;
		this.loadButton = loadButton;
		this.api = api;
		
		this.setText("Analyze");
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: make sure there is a file in the directory, hint: use api
				if(/*api.hasConfigFile() != true*/ false){
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
					// wait for pbar to finish then go to result
					// goToResult();					
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
