package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import api.UmlGeneratorApi;
import nodes.Model;

public class ResultGUI {

	private JFrame mainframe;
	private JPanel mainpanel;
	private JPanel graphPanel;
	private UmlGeneratorApi api;
	private List<Class<? extends Model>> classes;

	public ResultGUI(JFrame mainframe, JPanel mainpanel, UmlGeneratorApi api) throws IOException {
		this.graphPanel = new JPanel();
		this.mainframe = mainframe;
		this.mainpanel = mainpanel;
		this.mainframe.setTitle("Design Parser - Results for "+api.getInputFileName());
		this.mainframe.setSize(1200, 800);
		
		this.api = api;
		
		populatePanel();
	}

	private void populatePanel() throws IOException {
		createMenuBar();
		createCheckboxTree();
//		String filepath = "./output/testGUI.dot";
//		makeFile(this.classes.encodeToDot(), filepath);
//		createGraphViz(filepath, "\"C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot\"");
		
		// TODO: find out when you want to call this
		showImage();
	}

	private void makeFile(String digraph, String resultPath) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(resultPath, "UTF-8");
			writer.print(digraph);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private void createGraphViz(String filepath, String exepath) throws IOException {
		String outPath = "./output/out.png";

		String command = exepath + " -Tpng " + filepath + " -o " + outPath;
		Runtime.getRuntime().exec(command);
	}

	public void showImage() {
		ImageIcon image = new ImageIcon("./output/out.png");
		
		this.graphPanel.removeAll();
		JScrollPane scrollPanel = new JScrollPane(new JLabel(image), ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		scrollPanel.setPreferredSize(new Dimension(779, 722));
		scrollPanel.setVisible(true);
		this.graphPanel.add(scrollPanel);
		this.graphPanel.revalidate();
		this.mainpanel.add(graphPanel, "dock east, h 790!, w 790!");
	}

	private void createCheckboxTree() {
		JPanel treePanel = new JPanel();
		
		CheckBoxTree cboxtree = new CheckBoxTree(this.api);
		cboxtree.generate();
		JPanel testPan = new JPanel();
		
		JCheckBox check = new JCheckBox("testing");
		testPan.add(check);
		
		JScrollPane scrollPanel = new JScrollPane(cboxtree, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanel.setPreferredSize(new Dimension(400, 727));
		scrollPanel.setVisible(true);

		
		treePanel.add(scrollPanel);
		this.mainpanel.add(treePanel, "dock west, h 801!, w 400!");
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
				JFileChooser fchooser = new JFileChooser();
				fchooser.showOpenDialog(mainframe);
				File chosen = fchooser.getSelectedFile();
				// TODO: give api the file
				ResultGUI.this.api.readConfigFile();
			}
		});

		JMenuItem exportMenuItem = new JMenuItem("Export");
		exportMenuItem.setMnemonic(KeyEvent.VK_E);
		exportMenuItem.setToolTipText("Export to pdf");
		exportMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.println("Export");
				// TODO: save file somehow
				
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
				String inst = "<html>";
				inst += "Hello!<br />"
						+ "To use this program, you want to start by clicking a checkbox. According to whatever checkbox you click<br/>"
						+ " you will see an image of that instance of whatever design is specified. If you would like to see<br/>"
						+ " every instance of that design, click the checkbox next to the design's name.<br />"
						+ "If you would like to load a new configuration file, then click File > Load new and select the<br/>"
						+ " desired configuration file.<br/>"
						+ "If you would like to save your current image to a specific directory, click File > Export and<br/>"
						+ " select the desired destination."
						+ "<br /> We hope this helps!";
				inst += "</html>";
				JOptionPane instop = new JOptionPane();
				instop.showMessageDialog(ResultGUI.this.mainframe, inst, "Instructions", 1);
			}
		});

		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.setMnemonic(KeyEvent.VK_E);
		aboutMenuItem.setToolTipText("Learn about this application");
		aboutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String about = "<html>";
				about += "Hello!<br />"
						+ "This program is aimed to aid programers that want to know what software patterns are<br/>"
						+ " included in asoftware package. This is especially useful for students taking a software<br/>"
						+ " design course!<br />"
						+ "The framework is entirely built in java and actually uses a few software patterns of<br/>"
						+ " its own.<br/>"
						+ "If you would like to learn more about software patterns, I emplore you to search<br/>"
						+ " the internet and see what you can find."
						+ "<br /> We hope this helps!";
				about += "</html>";
				JOptionPane instop = new JOptionPane();
				instop.showMessageDialog(ResultGUI.this.mainframe, about, "About", 1);
			}
		});

		help.add(instMenuItem);
		help.add(aboutMenuItem);

		menubar.add(file);
		menubar.add(help);

		this.mainframe.setJMenuBar(menubar);
	}

}
