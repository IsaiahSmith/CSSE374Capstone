package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import api.UmlGeneratorApi;

public class LoadConfigButton extends JButton {
	private UmlGeneratorApi api;
	
	public LoadConfigButton(JFrame mainframe, UmlGeneratorApi api) {
		this.api = api;
		
		this.setText("Load Configuration");
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fchooser = new JFileChooser();
				fchooser.showOpenDialog(mainframe);
				File chosen = fchooser.getSelectedFile();
				// TODO: give api the file
				LoadConfigButton.this.api.readConfigFile();
			}
		});
	}
}
