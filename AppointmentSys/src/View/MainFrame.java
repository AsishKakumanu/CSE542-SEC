package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import Common.Appointment;
import Model.ModelDataManager;


/**
 * @author wyj19
 * provide main frame and runtime interface 
 */
public class MainFrame extends JFrame {


	
	private QueDisplayPanel queDisplayPanel;
	private ReportPanel reportJPanel;
	private CardLayout c;
	private JPanel mainJPanel;
	private ModelDataManager modelDataManager;
//	AppointQueue appointQueue;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();	
					frame.init();
					
//					frame.reportJPanel.viewUpdate(frame.appointQueue.getNextAppointment());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the this.
	 */
	public MainFrame() {
		modelDataManager = new ModelDataManager(this);
//		appointQueue = new AppointQueue(5);
		this.setName("MainFrame");
		this.setResizable(false);
		this.setBounds(100, 100, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		c = new CardLayout();
		
		mainJPanel = new JPanel();	
		mainJPanel.setBounds(2, 5, 790, 546);
		mainJPanel.setLayout(c);
		this.add(mainJPanel);
		
        reportJPanel = new ReportPanel(modelDataManager);
        mainJPanel.add("TAreport",reportJPanel);
        
        queDisplayPanel  = new QueDisplayPanel(modelDataManager);
        mainJPanel.add("queue",queDisplayPanel);
//        c.show(mainJPanel,"TAreport");
//        modelDataManager.init();
	}
	public void init() {
		modelDataManager.init();
	}
	public void TAreportShow(Appointment data) {
		reportJPanel.viewUpdate(data);
		c.show(mainJPanel,"TAreport");
	}
	public void queueShow(String[][] data) {
		queDisplayPanel.viewUpdate(data);
		c.show(mainJPanel,"queue");
	}

	public ModelDataManager getModelDataManager() {
		return modelDataManager;
	}

	public void setModelDataManager(ModelDataManager modelDataManager) {
		this.modelDataManager = modelDataManager;
	}
}
