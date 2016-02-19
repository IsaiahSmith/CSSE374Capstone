package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class LandingPage implements Observer {
	
	private JFrame mainframe;
	private JLabel loadingText;

	/**
     * Constructs the Start up screen.
     * 
     */
	public LandingPage() {
		this.mainframe = new JFrame();
		this.mainframe.setTitle("Design Parser");
        this.mainframe.setMinimumSize(new Dimension(600, 400));
        this.mainframe.setVisible(true);
        this.mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainframe.setResizable(false);
		this.mainframe.setBackground(Color.BLACK);
        
		MigLayout layout = new MigLayout();
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(layout);
        
        JPanel buttonPanel = new JPanel();
        
        LoadConfigButton loadButton = new LoadConfigButton();
        AnalyzeButton analyzeButton = new AnalyzeButton(mainframe, mainPanel);
        
        buttonPanel.add(loadButton);
        buttonPanel.add(new JLabel("  "));
        buttonPanel.add(analyzeButton);
        
        JPanel progressPanel = new JPanel();
        this.loadingText= new JLabel("");
        progressPanel.add(this.loadingText);
        
        
        mainPanel.add(buttonPanel, "dock north");
        mainPanel.add(progressPanel, "dock south");
        
        this.mainframe.getContentPane().add(mainPanel);
        this.mainframe.pack();
	}

	@Override
	public void update(Observable o, Object arg) {
		String label = (String) arg;
		this.loadingText.setText(label);
	}
}
