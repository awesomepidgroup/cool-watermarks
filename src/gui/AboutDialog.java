package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;

public class AboutDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AboutDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); 
		
		Icon image = new ImageIcon("resources" + File.separator + "icon64.png");
		JLabel labelImage = new JLabel(image);
		labelImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(labelImage);
		
		JLabel labelText = new JLabel("Pepe");
		labelText.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(labelText);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(120, 120, 120)));
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
			
		});
		buttonPanel.add(okButton);
				
	}

}
