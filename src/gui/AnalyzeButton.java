package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import nodes.Model;

public class AnalyzeButton extends JButton {
	private JPanel mainpanel;
	private JFrame mainframe;
	Model model;
	
	public AnalyzeButton(JFrame mainframe, JPanel mainpanel) {
		this.mainframe = mainframe;
		this.mainpanel = mainpanel;
		this.setText("Analyze");
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AnalyzeButton.this.mainpanel.removeAll();
				AnalyzeButton.this.mainpanel.revalidate();
				AnalyzeButton.this.mainframe.getContentPane().removeAll();
				AnalyzeButton.this.mainframe.getContentPane().revalidate();
				AnalyzeButton.this.mainframe.setBackground(Color.BLACK);
				AnalyzeButton.this.mainframe.add(AnalyzeButton.this.mainpanel);
				
				try {
					AnalyzeButton.this.populatePanel();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	protected void populatePanel() throws IOException {
		new ResultGUI(this.mainframe, this.mainpanel, this.model);
	}
}
