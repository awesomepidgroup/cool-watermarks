package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;


public class MainWindow {

	private JFrame frame;
	
	private Toolkit toolkit;
	
	private JMenuBar menuBar;
	
	private JMenu mnFile;
	private JMenu mnHelp;
	
	private JMenuItem mntmOpen;
	private JMenuItem mntmSave;
	private JMenuItem mntmExit;
	private JMenuItem mntmAboutTheAuthors;
	private JMenuItem mntmAbout;
	
	private JToolBar toolBar;
	
	private JButton tbbtnOpen;
	private JButton tbbtnSave;
	private JButton tbbtnPrevious;
	private JButton tbbtnNext;
	private JButton tbbtnToEnd;
		
	private JPanel panel;
	private JPanel panelOriginal;
	private JPanel panelExplanation;
	private JPanel panelResult;
	
	private JLabel lblOriginal;
	private JTextArea textExplanation;
	private JLabel lblResult;
	
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnToEnd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
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
		
		int frameWidth = 0;
		int frameHeight = 0;
		
		int btnYPos = 0;
		int btnHeight = 0;
		
		if (osName.indexOf("mac") >= 0) {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			
			frameWidth = 694;
			frameHeight = 470;
			
			btnYPos = 383;
			btnHeight = 29;
		} else if(osName.indexOf("windows") >= 0) {
			frameWidth = 700;
			frameHeight = 500;
			
			btnYPos = 386;
			btnHeight = 23;
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
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmOpen = new JMenuItem("Open...");
		mnFile.add(mntmOpen);
		
		mntmSave = new JMenuItem("Save as...");
		mnFile.add(mntmSave);
		
		mnFile.addSeparator();
		
		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		mntmAboutTheAuthors = new JMenuItem("About the authors...");
		mnHelp.add(mntmAboutTheAuthors);
		
		mnHelp.addSeparator();
		
		mntmAbout = new JMenuItem("About...");
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
		toolBar.add(tbbtnOpen);
		
		tbbtnSave = new JButton();
		tbbtnSave.setToolTipText("Save");
		tbbtnSave.setIcon(iconSave);
		toolBar.add(tbbtnSave);
		
		toolBar.addSeparator();
		
		tbbtnPrevious = new JButton();
		tbbtnPrevious.setToolTipText("Previous step");
		tbbtnPrevious.setIcon(iconPrevious);
		tbbtnPrevious.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setText("", textExplanation);
			}
			
		});
		toolBar.add(tbbtnPrevious);
		
		tbbtnNext = new JButton();
		tbbtnNext.setToolTipText("Next step");
		tbbtnNext.setIcon(iconNext);
		tbbtnNext.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				deleteImage(lblResult);
			}
			
		});
		toolBar.add(tbbtnNext);
		
		tbbtnToEnd = new JButton();
		tbbtnToEnd.setToolTipText("To end");
		tbbtnToEnd.setIcon(iconToEnd);
		tbbtnToEnd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				deleteImage(lblOriginal);
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
				
		panelExplanation = new JPanel();
		panelExplanation.setBounds(10, 224, 330, 151);
		panelExplanation.setBorder(new TitledBorder(new LineBorder(new Color(150, 150, 150), 1, true), "Explanation of the next step", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelExplanation.setLayout(null);
		panel.add(panelExplanation);
		
		Color backgroundColor = toolBar.getBackground();
		Font normalFont = lblOriginal.getFont();
		textExplanation = new JTextArea();
		textExplanation.setBounds(5, 15, 320, 131);
		textExplanation.setWrapStyleWord(true);
		textExplanation.setLineWrap(true);
		textExplanation.setAutoscrolls(true);
		textExplanation.setEditable(false);
		textExplanation.setBorder(null);
		textExplanation.setBackground(backgroundColor);
		textExplanation.setFont(normalFont);
		panelExplanation.add(textExplanation);
		
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
				setText("Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie.", textExplanation);
			}
			
		});
		panel.add(btnPrevious);
		
		btnNext = new JButton("Next");
		btnNext.setBounds(130, btnYPos, 89, btnHeight);
		btnNext.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setResultImage("testImages" + separator + "result.jpg");
			}
			
		});
		panel.add(btnNext);
		
		btnToEnd = new JButton("To end");
		btnToEnd.setBounds(251, btnYPos, 89, btnHeight);
		btnToEnd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setOriginalImage("testImages" + separator + "original.jpg");
			}
			
		});
		panel.add(btnToEnd);
		
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
			int diffWidth = Math.abs(labelWidth - imageWidth);
			int diffHeight = Math.abs(labelHeight - imageHeight);
			
			if(diffHeight < diffWidth) {
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
	
	private void deleteImage(JLabel label) {
		label.setIcon(null);
	}
	
	private void setText(String text, JTextArea textArea ) {
		textArea.setText(text);
	}
	
	public void setOriginalImage(String imagePath) {
		setImage(imagePath, lblOriginal);
	}
	
	public void setResultImage(String imagePath) {
		setImage(imagePath, lblResult);
	}
	
	public void setExplanationText(String text) {
		setText(text, textExplanation);
	}
	
	public void deleteOriginalImage() {
		deleteImage(lblOriginal);
	}
	
	public void deleteResultImage() {
		deleteImage(lblResult);
	}
	
}
