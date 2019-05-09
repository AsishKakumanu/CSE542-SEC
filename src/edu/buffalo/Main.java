package edu.buffalo;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TaLogin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
