package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class LoadConfigButton extends JButton {
	private Object api;
	
	public LoadConfigButton(JFrame mainframe, Object api) {
		this.api = api;
		
		this.setText("Load Configuration");
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fchooser = new JFileChooser();
				fchooser.showOpenDialog(mainframe);
				File chosen = fchooser.getSelectedFile();
				// TODO: give api the file
			}
		});
	}
}
