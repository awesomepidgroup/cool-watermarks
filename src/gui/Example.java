package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Example implements Runnable {

	private JFrame frame;
	private Toolkit tk;
	private JToolBar tb;
	private JButton chickenButton;
	private JMenuBar mb;
	private JMenuItem m11;
	private JMenuItem m22;
	private JPanel panel;
	private JLabel label;
	private JTextField tf;
	private JButton send;
	private JButton reset;
	private JTextArea ta;

	public static void main(String args[]) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		if (System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0) {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
		}

		SwingUtilities.invokeLater(new Example());

	}

	public void run() {

		// Creating the frame and the toolkit
		frame = new JFrame("Java Menubar Example");
		tk = frame.getToolkit();

		// Creating the menu bar
		mb = new JMenuBar();
		
		// Creating the toolbar
		tb = new JToolBar();
		tb.setFloatable(false);
		
		chickenButton = new JButton("Close");
		chickenButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
			
		});
		
		tb.add(chickenButton);
		
		// Creating the file menu
		JMenu m1 = new JMenu("File");

		// Creatin the options for the file menu
		m11 = new JMenuItem("Open");
		m22 = new JMenuItem("Save as");

		// Adding options to the file menu
		m1.add(m11);
		m1.add(m22);

		// Creating the help menu
		JMenu m2 = new JMenu("Help");

		// Adding menus to the menu bar
		mb.add(m1);
		mb.add(m2);

		// Creating the panel
		panel = new JPanel();

		// Creating components of the panel
		label = new JLabel("Enter Text");
		tf = new JTextField(10);
		send = new JButton("Send");
		reset = new JButton("Reset");

		// Adding handlers to buttons
		m11.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame,
						"Eggs are not supposed to be green.", "Error message",
						JOptionPane.ERROR_MESSAGE);
			}

		});

		send.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tk.beep();
			}

		});

		// Adding components to the panel
		panel.add(label);
		panel.add(tf);
		panel.add(send);
		panel.add(reset);

		// Text area at the Center
		ta = new JTextArea();

		// Adding components and properties to the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setResizable(false);
		Dimension size = tk.getScreenSize();
		frame.setLocation((size.width - frame.getWidth())/2, (size.height - frame.getHeight())/2);
		if (System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0) {
			frame.setJMenuBar(mb);
		} else {
			frame.getContentPane().add(BorderLayout.NORTH, mb);
		}
		frame.getContentPane().add(BorderLayout.NORTH, tb);
		frame.getContentPane().add(BorderLayout.CENTER, ta);
		frame.getContentPane().add(BorderLayout.SOUTH, panel);
		frame.setVisible(true);
	}

}