package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class LandingPage {
	
	private JFrame mainframe;

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
        JPanel progressPanel = new JPanel();
        
        Object api = new Object();
        
        LoadConfigButton loadButton = new LoadConfigButton(mainframe, api);
        AnalyzeButton analyzeButton = new AnalyzeButton(mainframe, mainPanel ,progressPanel, loadButton, api);
        
        buttonPanel.add(loadButton);
        buttonPanel.add(new JLabel("  "));
        buttonPanel.add(analyzeButton);
        
        progressPanel.revalidate();
        mainPanel.add(buttonPanel, "cell 1 1");
        mainPanel.add(progressPanel, "cell 2 3");
        
        this.mainframe.getContentPane().add(mainPanel);
        this.mainframe.pack();
	}
}
