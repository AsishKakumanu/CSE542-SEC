package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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

public class ReportPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private JTable queueTable;
	private JLabel emailLabel;
	private JLabel stuNameLabel;
	private JTextField emailTextField;
	private JLabel questionLabel;
	private JLabel timeLabel;
	private JLabel reserveTimelabel;
	private JTextArea questionTextArea;
	private ModelDataManager modelDataManager;
	
	public ReportPanel() {
		
		this.setFont(new Font("Arial", Font.PLAIN, 20));
		this.setBounds(2, 5, 790, 546);
		this.setLayout(null);
		
		emailLabel = new JLabel("E-mail:");
		emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		emailLabel.setBounds(100, 125, 100, 25);
		this.add(emailLabel);

		
		queueTable = new JTable();
		queueTable.setBounds(440, 15, 0, 0);
		this.add(queueTable);
		
		stuNameLabel = new JLabel("Student Name");
		stuNameLabel.setFont(new Font("Arial", Font.PLAIN, 40));
		stuNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stuNameLabel.setBounds(145, 35, 500, 70);
		this.add(stuNameLabel);
		
		emailTextField = new JTextField();
		emailTextField.setFont(new Font("Arial", Font.PLAIN, 20));
		emailTextField.setBackground(Color.WHITE);
		emailTextField.setDisabledTextColor(Color.BLACK);
		emailTextField.setEditable(false);
		emailTextField.setText("email");
		emailTextField.setBounds(200, 125, 450, 25);
		this.add(emailTextField);
		
		questionLabel = new JLabel("Question:");
		questionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		questionLabel.setBounds(100, 200, 100, 25);
		this.add(questionLabel);
		
		
//		scrollPane.setBounds(100, 200, 100, 25);
//		this.add(scrollPane);
		
		questionTextArea = new JTextArea();		
		questionTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(questionTextArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(200, 200, 450, 120);
		this.add(scrollPane);
		questionTextArea.setFont(new Font("Arial", Font.PLAIN, 20));
		questionTextArea.setText("question");
//		questionTextArea.setBounds(200, 200, 450, 120);
		questionTextArea.setLineWrap(true);       
		questionTextArea.setWrapStyleWord(true);
		
		timeLabel = new JLabel("Reserve Time :");
		timeLabel.setForeground(Color.BLACK);
		timeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		timeLabel.setBounds(100, 362, 172, 21);
		this.add(timeLabel);
		
		reserveTimelabel = new JLabel();
		reserveTimelabel.setText("2019/5/8  16:15");
		reserveTimelabel.setFont(new Font("Arial", Font.PLAIN, 20));
		reserveTimelabel.setBounds(255, 362, 390, 21);
		this.add(reserveTimelabel);
		
		JButton btnPresent = new JButton("Present");
		btnPresent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelDataManager.presentReport();
				//present code
			}
		});
		btnPresent.setBounds(200, 430, 150, 60);
		this.add(btnPresent);
		
		JButton btnAbsent = new JButton("Absent");
		btnAbsent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelDataManager.absenceReport();
				//Absent code
			}
		});
		
		btnAbsent.setBounds(440, 430, 150, 60);
		this.add(btnAbsent);
		
		JButton btnAbsentfinish = new JButton("Absent&Finish");
		btnAbsentfinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modelDataManager.absenceFinishReport();
			}
		});
		btnAbsentfinish.setBounds(605, 430, 150, 60);
		add(btnAbsentfinish);
	}
	public ReportPanel(ModelDataManager m) {
		this();
		this.modelDataManager = m;		
	}
	public Boolean viewUpdate(Appointment data) {
//		data.getID();
		String a = data.getName();
		stuNameLabel.setText(data.getName());
		reserveTimelabel.setText(data.getDate().toString());
		emailTextField.setText(data.getEmail());
		questionTextArea.setText(data.getQuestion());
		
		return true;
	}
}
