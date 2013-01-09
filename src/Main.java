import gui.MainWindow;

import java.awt.EventQueue;

/**
 * Main class. Executes the application.
 */
public class Main {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					new MainWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}
	
}
