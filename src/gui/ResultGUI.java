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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import nodes.Model;

public class ResultGUI {

	private JFrame mainframe;
	private JPanel mainpanel;
	private List<Class<? extends Model>> classes;

	public ResultGUI(JFrame mainframe, JPanel mainpanel, Model model) throws IOException {
		this.mainframe = mainframe;
		this.mainpanel = mainpanel;
		this.mainframe.setTitle("Design Parser - Results");
		this.mainframe.setSize(1200, 800);
		populatePanel(model);
	}

	private void populatePanel(Model model) throws IOException {
		// this.classes = model.getClasses();
		String filepath = "./output/testGUI.dot";
		createMenuBar();
		createCheckboxTree();
		// makeFile(this.classes.encodeToDot(), filepath);
		createGraphViz(filepath, "\"C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot\"");
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
		
//		showImage();
	}

	private void showImage() {
		ImageIcon image = new ImageIcon("./output/out.png");

		JPanel graphPanel = new JPanel();
		JScrollPane scrollPanel = new JScrollPane(new JLabel(image), ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		scrollPanel.setPreferredSize(new Dimension(779, 722));
		scrollPanel.setVisible(true);
		graphPanel.add(scrollPanel);
		this.mainpanel.add(graphPanel, "dock east, h 790!, w 790!");
	}

	private void createCheckboxTree() {
		JPanel treePanel = new JPanel();

		JScrollPane scrollPanel = new JScrollPane(new CheckBoxTree(), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
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
