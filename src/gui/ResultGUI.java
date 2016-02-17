package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ResultGUI {

	private JFrame mainframe;
	private JPanel mainpanel;

	public ResultGUI(JFrame mainframe, JPanel mainpanel) {
		this.mainframe = mainframe;
		this.mainpanel = mainpanel;
		this.mainframe.setTitle("Design Parser - Results");
		this.mainframe.setSize(1200, 800);
		populatePanel();
	}

	private void populatePanel() {
		createMenuBar();
		createCheckboxTree();
		createGraphViz("./output/demoFull.txt");
	}
	
	private void createGraphViz(String filepath) {
//		String outPath = this.outputDir + "\\out.png";
//		ProcessBuilder pb = new ProcessBuilder(this.dotPath, "-Tpng", tempPath, "-o", outPath);
		Process process;
		try {
			process = new ProcessBuilder(
			"C:\\Program Files (x86)\\Graphviz2.38\\bin\\gvedit.exe",filepath).start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void createCheckboxTree() {
		JPanel treePanel = new JPanel();
		treePanel.setBackground(Color.black);
		this.mainpanel.add(treePanel, "dock west, h 800!, w 400! ");
	}

	private void createMenuBar() {
        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        

        JMenuItem loadMenuItem = new JMenuItem("Load new");
        loadMenuItem.setMnemonic(KeyEvent.VK_E);
        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Load new");
            }
        });
        
        JMenuItem exportMenuItem = new JMenuItem("Export");
        exportMenuItem.setMnemonic(KeyEvent.VK_E);
        exportMenuItem.setToolTipText("Export to pdf");
        exportMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Export");
            }
        });

        file.add(loadMenuItem);
        file.add(exportMenuItem);
        
        JMenu help = new JMenu("Help");
        file.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem instMenuItem = new JMenuItem("Instructions");
        instMenuItem.setMnemonic(KeyEvent.VK_E);
        instMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Instruction");
            }
        });
        
        JMenuItem aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.setMnemonic(KeyEvent.VK_E);
        aboutMenuItem.setToolTipText("Learn about this application");
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("About");
            }
        });
        
        help.add(instMenuItem);
        help.add(aboutMenuItem);
        
        menubar.add(file);
        menubar.add(help);

        this.mainframe.setJMenuBar(menubar);
    }

}
