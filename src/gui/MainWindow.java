package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;

import process.Navigator;
import process.NavigatorResponse;

/**
 * Desings the GUI and manages the principal thread of
 * the program execution.
 */
public class MainWindow {

	/**
	 * Main attribute of the class. Is the base of everything.
	 */
	private JFrame frame;
	
	/**
	 * Attribute which get the dimensions of the window.
	 */
	private Toolkit toolkit;
	
	/**
	 * Attribute used for the menu bar.
	 */
	private JMenuBar menuBar;
	
	/**
	 * Attributes used to create menu groups.
	 */
	private JMenu mnFile;
	private JMenu mnHelp;
	
	/**
	 * Group of attributes used to manage the
	 * menu items.
	 */
	private JMenuItem mntmOpen;
	private JMenuItem mntmSave;
	private JMenuItem mntmExit;
	private JMenuItem mntmAbout;
	
	/**
	 * Attribute created to manage the tool bar.
	 */
	private JToolBar toolBar;
	
	/**
	 * Group of attributes created to manage the buttons
	 * of the application.
	 */
	private JButton tbbtnOpen;
	private JButton tbbtnSave;
	private JButton tbbtnPrevious;
	private JButton tbbtnNext;
	private JButton tbbtnToEnd;
	
	/**
	 * Group of attributes used to manage the different
	 * panels of our application.
	 */
	private JPanel panel;
	private JPanel panelOriginal;
	private JScrollPane panelExplanation;
	private JPanel panelResult;
	
	/**
	 * Label and text attributes.
	 */
	private JLabel lblOriginal;
	private JTextArea textExplanation;
	private JLabel lblResult;
	
	/**
	 * More attribute to manage buttons.
	 */
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnToEnd;
	
	/**
	 * Attribute to control the logic of the
	 * application.
	 */
	private Navigator navigator;
	
	/**
	 * Attribute to search files in open and save
	 * operations.
	 */
	private JFileChooser fileChooser;

	/**
	 * Default constructor. Create the application.
	 */
	public MainWindow() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		
		String osName = System.getProperty("os.name").toLowerCase();
		
		int frameWidth = 700;
		int frameHeight = 500;
		
		int btnYPos = 386;
		int btnHeight = 23;
		
		if (osName.indexOf("mac") >= 0) {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			
			frameWidth = 694;
			frameHeight = 470;
			
			btnYPos = 383;
			btnHeight = 29;
		} else if(osName.indexOf("linux") >= 0) {
			frameWidth = 694;
			frameHeight = 505;
		} else if(osName.indexOf("windows") >= 0) {
			/*
			 * All the parameters by default.
			 */
		}
		
		final String separator = File.separator;
		Image icon = null;
		try {
			icon = ImageIO.read(new File("resources" + separator + "icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		frame = new JFrame("Cool Watermarks");
		frame.setIconImage(icon);
		
		toolkit = frame.getToolkit();
		
		frame.setSize(frameWidth, frameHeight);
		frame.setResizable(false);
		
		Dimension screenDimension = toolkit.getScreenSize();
		frame.setLocation((screenDimension.width - frame.getWidth())/2, (screenDimension.height - frame.getHeight())/2);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmOpen = new JMenuItem("Open...");
		mntmOpen.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				open();
			}
			
		});
		mnFile.add(mntmOpen);
		
		mntmSave = new JMenuItem("Save as...");
		mntmSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				save();
			}
			
		});
		mnFile.add(mntmSave);
		
		mnFile.addSeparator();
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		mnFile.add(mntmExit);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		mntmAbout = new JMenuItem("About...");
		mntmAbout.addActionListener((new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				about();
			}
		}));
		mnHelp.add(mntmAbout);
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(120, 120, 120)));
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
				
		Icon iconOpen = new ImageIcon("resources" + separator +"open.png");
		Icon iconSave = new ImageIcon("resources" + separator +"save.png");
		Icon iconPrevious = new ImageIcon("resources" + separator +"previous.png");
		Icon iconNext = new ImageIcon("resources" + separator +"next.png");
		Icon iconToEnd = new ImageIcon("resources" + separator +"last.png");
		
		tbbtnOpen = new JButton();
		tbbtnOpen.setToolTipText("Open");
		tbbtnOpen.setIcon(iconOpen);
		tbbtnOpen.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				open();
			}
			
		});
		toolBar.add(tbbtnOpen);
		
		tbbtnSave = new JButton();
		tbbtnSave.setToolTipText("Save");
		tbbtnSave.setIcon(iconSave);
		tbbtnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				save();
			}
			
		});
		toolBar.add(tbbtnSave);
		
		toolBar.addSeparator();
		
		tbbtnPrevious = new JButton();
		tbbtnPrevious.setToolTipText("Previous step");
		tbbtnPrevious.setIcon(iconPrevious);
		tbbtnPrevious.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				previous();
			}
			
		});
		toolBar.add(tbbtnPrevious);
		
		tbbtnNext = new JButton();
		tbbtnNext.setToolTipText("Next step");
		tbbtnNext.setIcon(iconNext);
		tbbtnNext.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				next();
			}
			
		});
		toolBar.add(tbbtnNext);
		
		tbbtnToEnd = new JButton();
		tbbtnToEnd.setToolTipText("To end");
		tbbtnToEnd.setIcon(iconToEnd);
		tbbtnToEnd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				toEnd();
			}
			
		});
		toolBar.add(tbbtnToEnd);
		
		panel = new JPanel();
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
				
		panelOriginal = new JPanel();
		panelOriginal.setBounds(10, 9, 330, 204);
		panelOriginal.setBorder(new TitledBorder(new LineBorder(new Color(150, 150, 150), 1, true), "Original", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOriginal.setLayout(null);
		panel.add(panelOriginal);
				
		lblOriginal = new JLabel();
		lblOriginal.setBounds(5, 15, 320, 184);
		lblOriginal.setVerticalAlignment(JLabel.CENTER);
		lblOriginal.setHorizontalAlignment(JLabel.CENTER);
		panelOriginal.add(lblOriginal);
		
		Color backgroundColor = toolBar.getBackground();
		panelExplanation = new JScrollPane();
		panelExplanation.setBounds(10, 224, 330, 151);
		panelExplanation.setBorder(new TitledBorder(new LineBorder(new Color(150, 150, 150), 1, true), "Explanation of the next step", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelExplanation.setBackground(backgroundColor);
		panelExplanation.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(panelExplanation);
		
		Font normalFont = lblOriginal.getFont();
		textExplanation = new JTextArea();
		textExplanation.setBounds(5, 15, 320, 131);
		textExplanation.setWrapStyleWord(true);
		textExplanation.setLineWrap(true);
		textExplanation.setAutoscrolls(true);
		textExplanation.setEditable(false);
		textExplanation.setBackground(backgroundColor);
		textExplanation.setFont(normalFont);
		DefaultCaret caret = (DefaultCaret) textExplanation.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		panelExplanation.setViewportView(textExplanation);
		
		panelResult = new JPanel();
		panelResult.setBounds(350, 9, 334, 400);
		panelResult.setBorder(new TitledBorder(new LineBorder(new Color(150, 150, 150), 1, true), "Result", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelResult.setLayout(null);
		panel.add(panelResult);
		
		lblResult = new JLabel();
		lblResult.setBounds(5, 15, 324, 380);
		lblResult.setVerticalAlignment(JLabel.CENTER);
		lblResult.setHorizontalAlignment(JLabel.CENTER);
		panelResult.add(lblResult);
		
		btnPrevious = new JButton("Previous");
		btnPrevious.setBounds(10, btnYPos, 89, btnHeight);
		btnPrevious.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				previous();
			}
			
		});
		panel.add(btnPrevious);
		
		btnNext = new JButton("Next");
		btnNext.setBounds(130, btnYPos, 89, btnHeight);
		btnNext.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				next();
			}
			
		});
		panel.add(btnNext);
		
		btnToEnd = new JButton("To end");
		btnToEnd.setBounds(251, btnYPos, 89, btnHeight);
		btnToEnd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				toEnd();
			}
			
		});
		panel.add(btnToEnd);
		
		setButtonsEnabled(false, false, false, false);
		
		if (osName.indexOf("mac") >= 0) {
			LineBorder tbbtnBorder = new LineBorder(backgroundColor, 4, true);
			
			tbbtnOpen.setBackground(backgroundColor);
			tbbtnOpen.setBorder(tbbtnBorder);
			
			tbbtnSave.setBackground(backgroundColor);
			tbbtnSave.setBorder(tbbtnBorder);
			
			tbbtnPrevious.setBackground(backgroundColor);
			tbbtnPrevious.setBorder(tbbtnBorder);
			
			tbbtnNext.setBackground(backgroundColor);
			tbbtnNext.setBorder(tbbtnBorder);
			
			tbbtnToEnd.setBackground(backgroundColor);
			tbbtnToEnd.setBorder(tbbtnBorder);
		}
	
	}
	
	/**
	 * Method which sets the state of the button according to his
	 * param value.
	 * @param save
	 * the value for the save button.
	 * @param previous
	 * the value for the previous button.
	 * @param next
	 * the value for the next button.
	 * @param toEnd
	 * the value for the toEnd button.
	 */
	private void setButtonsEnabled(Boolean save, Boolean previous, Boolean next, Boolean toEnd) {
		mntmSave.setEnabled(save);
		tbbtnSave.setEnabled(save);
		
		tbbtnPrevious.setEnabled(previous);
		btnPrevious.setEnabled(previous);
		
		tbbtnNext.setEnabled(next);
		btnNext.setEnabled(next);
		
		tbbtnToEnd.setEnabled(toEnd);
		btnToEnd.setEnabled(toEnd);
	}
	
	/**
	 * Modifies the GUI according to the NavigatorResponse object.
	 * @param response
	 * the object used to get the appropriate information to modify
	 * the GUI.
	 */
	private void setGUIFromResponse(NavigatorResponse response) {
		setButtonsEnabled(response.getSave(), response.getPrevious(), response.getNext(), response.getToEnd());
		setText(response.getStepExplanation(), textExplanation);
		if(response.getImagePath() == "") {
			deleteImage(lblResult);
		} else {
			setImage(response.getImagePath(), lblResult);
		}
	}
	
	/**
	 * This method is calling when an "open" event is executed.
	 */
	private void open() {
		fileChooser = new JFileChooser();
		int isValid = fileChooser.showOpenDialog(frame);
		
		if(isValid == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getSelectedFile().getAbsolutePath();
			navigator = new Navigator(path);
			
			NavigatorResponse response = navigator.open();
			
			setImage(path, lblOriginal);
			setGUIFromResponse(response);
		}
	}
	
	/**
	 * This method is calling when an "save" event is executed.
	 */
	private void save() {
		fileChooser = new JFileChooser();
		FileFilter pngFilter = new FileNameExtensionFilter("PNG file", "png");
		fileChooser.addChoosableFileFilter(pngFilter);
		
		int isValid = fileChooser.showSaveDialog(frame);
		
		if(isValid == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getSelectedFile().getAbsolutePath() + ".png"; //CUIDADO CON ESTO
			navigator.save(path);
		}
	}
	
	/**
	 * This method is calling when an "previous" event is executed.
	 */
	private void previous() {
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		NavigatorResponse response = navigator.previous();
		setGUIFromResponse(response);
		
		frame.setCursor(Cursor.getDefaultCursor());
	}
	
	/**
	 * This method is calling when an "next" event is executed.
	 */
	private void next() {
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		NavigatorResponse response = navigator.next();
		setGUIFromResponse(response);
		
		frame.setCursor(Cursor.getDefaultCursor());
	}
	
	/**
	 * This method is calling when an "toEnd" event is executed.
	 */
	private void toEnd() {
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		NavigatorResponse response = navigator.toEnd();
		setGUIFromResponse(response);
		
		frame.setCursor(Cursor.getDefaultCursor());
	}
	
	/**
	 * This method creates the Dialog for the About menu.
	 */
	private void about() {
		AboutDialog dialog = new AboutDialog();
		dialog.setLocationRelativeTo(frame);
		dialog.setVisible(true);
	}
	
	/**
	 * Sets the image at the icon of the label. 
	 * @param imagePath
	 * the path of the image to use.
	 * @param label
	 * label which contains the icon to be modified.
	 */
	private void setImage(String imagePath, JLabel label) {
		Image image = null;
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		double imageAspect = (double) imageHeight / imageWidth;
		
		int labelWidth = label.getWidth();
		int labelHeight = label.getHeight();
		double labelAspect = (double) labelHeight / labelWidth;
		
		int finalImageWidth = 0;
		int finalImageHeight = 0;
		
		boolean higherLabelWidth = false;
		boolean higherLabelHeight = false;
		
		if(imageWidth <= labelWidth) {
			higherLabelWidth = true;
		}
		
		if(imageHeight <= labelHeight) {
			higherLabelHeight = true;
		}
		
		if(!higherLabelHeight && !higherLabelWidth) {
			
			if(imageAspect < labelAspect) {
				finalImageWidth = labelWidth;
				finalImageHeight = (int) (finalImageWidth * imageAspect);
			} else {
				finalImageHeight = labelHeight;
				finalImageWidth = (int) (finalImageHeight / imageAspect);
			}
			
		} else if(!higherLabelHeight && higherLabelWidth) {
			finalImageHeight = labelHeight;
			finalImageWidth = (int) (finalImageHeight / imageAspect);
		} else if(higherLabelHeight && !higherLabelWidth) {
			finalImageWidth = labelWidth;
			finalImageHeight = (int) (finalImageWidth * imageAspect);
		} else if(higherLabelHeight && higherLabelWidth) {
			finalImageWidth = imageWidth;
			finalImageHeight = imageHeight;
		}
		
		image = image.getScaledInstance(finalImageWidth, finalImageHeight, Image.SCALE_DEFAULT);
		label.setIcon(new ImageIcon(image));
	}
	
	/**
	 * Removes the image of the label.
	 * @param label
	 * label used to remove an image.
	 */
	private void deleteImage(JLabel label) {
		label.setIcon(null);
	}
	
	/**
	 * Method used to set the text of an area.
	 * @param text
	 * value for the area new text.
	 * @param textArea
	 * area to be modified
	 */
	private void setText(String text, JTextArea textArea ) {
		textArea.setText(text);
	}
}
