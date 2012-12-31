package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import java.io.File;
import java.io.IOException;

public class MainWindow {

	private JFrame frame;
	
	JLabel lblOriginal;
	
	private Icon iconOpen;
	private Icon iconSave;
	private Icon iconPrevious;
	private Icon iconNext;
	private Icon iconLast;

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
		
		frame = new JFrame("Cool Watermarks");
		
		Toolkit toolkit;
		toolkit = frame.getToolkit();
		
		frame.setSize(700, 500);
		frame.setResizable(false);
		
		Dimension size = toolkit.getScreenSize();
		frame.setLocation((size.width - frame.getWidth())/2, (size.height - frame.getHeight())/2);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("File");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmAbrir = new JMenuItem("Open...");
		mnArchivo.add(mntmAbrir);
		
		JMenuItem mntmGuardar = new JMenuItem("Save as...");
		mnArchivo.add(mntmGuardar);
		
		mnArchivo.addSeparator();
		
		JMenuItem mntmSalir = new JMenuItem("Exit");
		mnArchivo.add(mntmSalir);
		
		JMenu mnAyuda = new JMenu("Help");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmSobreLosAutores = new JMenuItem("About the authors...");
		mnAyuda.add(mntmSobreLosAutores);
		
		mnAyuda.addSeparator();
		
		JMenuItem mntmAcercaDe = new JMenuItem("About...");
		mnAyuda.add(mntmAcercaDe);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(120, 120, 120)));
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
				
		String sep = File.separator;
		iconOpen = new ImageIcon("resources" + sep +"open.png");
		iconSave = new ImageIcon("resources" + sep +"save.png");
		iconPrevious = new ImageIcon("resources" + sep +"previous.png");
		iconNext = new ImageIcon("resources" + sep +"next.png");
		iconLast = new ImageIcon("resources" + sep +"last.png");
				
		JButton btnAbrir = new JButton();
		btnAbrir.setIcon(iconOpen);
		toolBar.add(btnAbrir);
		
		JButton btnGuardar = new JButton();
		btnGuardar.setIcon(iconSave);
		toolBar.add(btnGuardar);
		
		toolBar.addSeparator();
		
		JButton btnPasoAnterior = new JButton();
		btnPasoAnterior.setIcon(iconPrevious);
		toolBar.add(btnPasoAnterior);
		
		JButton btnSiguientePaso = new JButton();
		btnSiguientePaso.setIcon(iconNext);
		toolBar.add(btnSiguientePaso);
		
		JButton btnTodosLosPasos = new JButton();
		btnTodosLosPasos.setIcon(iconLast);
		toolBar.add(btnTodosLosPasos);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
				
		lblOriginal = new JLabel();
		lblOriginal.setBounds(10, 11, 330, 204);
		lblOriginal.setBorder(new TitledBorder(new LineBorder(new Color(150, 150, 150), 1, true), "Original", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(lblOriginal);
	
		JLabel lblExplicacion = new JLabel();
		lblExplicacion.setBounds(10, 226, 330, 139);
		lblExplicacion.setBorder(new TitledBorder(new LineBorder(new Color(150, 150, 150), 1, true), "Explanation of the next step", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(lblExplicacion);
				
		JLabel lblResultado = new JLabel();
		lblResultado.setBounds(350, 11, 334, 388);
		lblResultado.setBorder(new TitledBorder(new LineBorder(new Color(150, 150, 150), 1, true), "Result", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(lblResultado);
		
		JButton btnAnterior = new JButton("Previous");
		btnAnterior.setBounds(10, 376, 89, 23);
		panel.add(btnAnterior);
		
		JButton btnSiguiente = new JButton("Next");
		btnSiguiente.setBounds(133, 376, 89, 23);
		panel.add(btnSiguiente);
		
		JButton btnTodos = new JButton("To end");
		btnTodos.setBounds(251, 376, 89, 23);
		btnTodos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setOriginalImage("original.jpg");
			}

		});
		panel.add(btnTodos);
	}
	
	public void setOriginalImage(String path) {
		Icon iconOriginal = new ImageIcon(path);
		Graphics graphics = lblOriginal.getGraphics();
		Image image = null;
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int iconHeight = iconOriginal.getIconHeight();
		int iconWidth = iconOriginal.getIconWidth();
		double iconAspect = (double) iconHeight / iconWidth;
		System.out.println("Imagen: " + iconWidth + " " + iconHeight);
		
		int labelHeight = lblOriginal.getHeight();
		int labelWidth = lblOriginal.getWidth();
		double labelAspect = (double) labelHeight / labelWidth;
		System.out.println("Label: " + labelWidth + " " + labelHeight);
		
		boolean alturaLabelMayor = false;
		if(iconHeight < labelHeight) {
			alturaLabelMayor = true;
		}
		
		boolean anchuraLabelMayor = false;
		if(iconWidth < labelWidth) {
			anchuraLabelMayor = true;
		}
		
		int diffHeight = Math.abs(labelHeight - iconHeight);
		int diffWidth = Math.abs(labelWidth - iconWidth);
		
		int finalLabelHeight = 0;
		int finalLabelWidth = 0;
		
		if(!alturaLabelMayor && !anchuraLabelMayor) {			
			System.out.println("Imagen mayor que el label");
			
			if(diffHeight < diffWidth) {
				System.out.println("La diferencia de anchura es mayor");
				finalLabelHeight = (int) (iconWidth * labelAspect);
				finalLabelWidth = labelWidth;
			} else {
				System.out.println("La diferencia de altura es mayor");
				finalLabelHeight = labelHeight;
				finalLabelWidth = (int) (iconHeight * labelAspect);
				
				image = image.getScaledInstance(finalLabelWidth, finalLabelHeight - 20, Image.SCALE_AREA_AVERAGING);
				graphics.drawImage(image, ((labelWidth - finalLabelWidth) / 2 ), 15, null);
			}
			
		} else if(!alturaLabelMayor && anchuraLabelMayor) {
			diffHeight = labelHeight - iconHeight;
			diffWidth = labelWidth - iconWidth;
			
			if(diffHeight < diffWidth) {
				System.out.println("La diferencia de anchura es mayor");
			} else {
				System.out.println("La diferencia de altura es mayor");
			}
		} else if(alturaLabelMayor && !anchuraLabelMayor) {
			diffHeight = labelHeight - iconHeight;
			diffWidth = labelWidth - iconWidth;
			
			if(diffHeight < diffWidth) {
				System.out.println("La diferencia de anchura es mayor");
			} else {
				System.out.println("La diferencia de altura es mayor");
			}
		} else if(alturaLabelMayor && anchuraLabelMayor) {
			diffHeight = labelHeight - iconHeight;
			diffWidth = labelWidth - iconWidth;
			
			if(diffHeight < diffWidth) {
				System.out.println("La diferencia de anchura es mayor");
			} else {
				System.out.println("La diferencia de altura es mayor");
			}
		}
		
		//image = image.getScaledInstance(finalLabelWidth - 10, finalLabelHeight - 20, Image.SCALE_AREA_AVERAGING);
		//graphics.drawImage(image, ((labelWidth - finalLabelWidth) / 2 ) - 5, ((labelHeight - finalLabelHeight) / 2) - 15, null);
	}
	
}
