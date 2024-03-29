package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;

import util.Print;

/**
 * Class which creates and manages the dialog
 * about the "about" event.
 * 
 * @author	Camacho Sosa, José Manuel
 * @author	Muñoz Ríos, Gabriel
 * @author	Pozo Nuñez, José Antonio
 * @version	1.0, 10 Jan 2013
 */
public class AboutDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * This attribute will be the panel to show.
	 */
	private final JPanel contentPanel = new JPanel();

	/**
	 * Default constructor. Create the dialog.
	 */
	public AboutDialog() {
		Print.log("Initiating the about dialog.");
		
		String separator = File.separator;
		Image icon = null;
		try {
			icon = ImageIO.read(new File("resources" + separator + "icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setIconImage(icon);
		setTitle("About Cool Watermarks");
		setResizable(false);
		
		int width = 350;
		int height = 288;
		
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.indexOf("mac") >= 0) {
			height = 285;
		} else if(osName.indexOf("linux") >= 0) {
			height  = 300;
		} else if(osName.indexOf("windows") >= 0) {
			// All the parameters by default.
		}
		
		setSize(width, height);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); 
		
		Icon image = new ImageIcon("resources" + separator + "icon64.png");
		JLabel labelImage = new JLabel(image);
		labelImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(labelImage);
		
		JLabel labelTextName = new JLabel("Cool Watermarks");
		labelTextName.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(labelTextName);
		
		JLabel labelTextVersion = new JLabel("Version 1.0");
		labelTextVersion.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(labelTextVersion);
		
		JLabel labelTextBlank1 = new JLabel(" ");
		labelTextBlank1.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(labelTextBlank1);
		
		JLabel labelTextAuthors = new JLabel("Authors:");
		labelTextAuthors.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(labelTextAuthors);
		
		JLabel labelTextJosema = new JLabel("Camacho Sosa, José Manuel - @josemazo");
		labelTextJosema.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(labelTextJosema);
		
		JLabel labelTextGabi = new JLabel("Muñoz Ríos, Gabriel - @Gabi_mu_ri");
		labelTextGabi.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(labelTextGabi);
		
		JLabel labelTextAntonio = new JLabel("Pozo Núñez, José Atonio - @CurroAntonio86");
		labelTextAntonio.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(labelTextAntonio);
		
		JLabel labelTextBlank2 = new JLabel(" ");
		labelTextBlank2.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(labelTextBlank2);
		
		JLabel labelTextLicense = new JLabel("This software is released under the MIT license");
		labelTextLicense.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPanel.add(labelTextLicense);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(120, 120, 120)));
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Print.log("Closing the about dialog from the OK button.");
				dispose();
			}
			
		});
		buttonPanel.add(okButton);
	}

}
