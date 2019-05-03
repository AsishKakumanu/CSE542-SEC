import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;

public class mainWindow {
	
	// Counter for Queue Button
	int btn_counter = 0;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow();
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
	public mainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		

		// Headers of the table
		String[] columns = new String[] { "Name", "Email ID", "Question", "Time" };

		int numberOfReservations = randomReservations(1, 4);
		System.out.println("No.of.reservations : " + numberOfReservations);
		HashMap<String, reservation> reservationHashMap = new HashMap<String, reservation>(numberOfReservations);

		// Names & email
		Hashtable<String, String> nameTable = new Hashtable<String, String>() {
			{
				put("Steve Rogers", "steverog@buffalo.edu");
				put("Tony Stark", "tonystar@buffalo.edu");
				put("Nick Fury", "nickfury@buffalo.edu");
				put("Dr.Strange", "dstrange@buffalo.edu");
				put("Bruce Banner", "bruceban@buffalo.edu");
				put("Scott Lang", "scottlan@buffalo.edu");

			}
		};

		// Questions
		ArrayList<String> questionList = new ArrayList<String>() {
			{
				add("Where is nick fury ?");
				add("Where is thanos ?");
				add("Tell me more about time heist");
				add("What is a pim particle");
				add("Who sacrificed himself/herself to revert the changes made by thanos ?");
			}
		};

		// CurrentTime
		ArrayList<Integer> timeList = new ArrayList<Integer>() {
			{
				add(5);
				add(11);
				add(0);
			}
		};

		// Shuffling members
		ArrayList keys = new ArrayList<>(nameTable.keySet());
		Collections.shuffle(keys);

		// Shuffling questions
		Collections.shuffle(questionList);

		// Adding random number of members to hashmap
		for (int i = 0; i < numberOfReservations; i++) {
			String name = keys.get(i).toString();
			String email = nameTable.get(name);
			String question = questionList.get(i).toString();

			// Current Time
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss E dd/MM/yyyy");
			Collections.shuffle(timeList);
			calendar.add(Calendar.MINUTE, -1 * timeList.get(1));

			// Adding random hours to the current time for each reservation.
			int randomHours = randomReservations(1, 170);
			calendar.add(Calendar.HOUR, randomHours);
			String time = dateFormat.format(calendar.getTime());

			// Adding final values to reservation & map.
			reservation newReservation = new reservation(name, email, question, time);
			reservationHashMap.put(name, newReservation);
		}

		// Print out :: Map Size & Map Values
		System.out.println("Size of map : " + reservationHashMap.size());
		for (Map.Entry<String, reservation> entry : reservationHashMap.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			System.out.println(" Name : " + key + " | email : " + ((reservation) value).emailAddress + " | question : "
					+ ((reservation) value).getQuestion() + " | time : " + ((reservation) value).getReservedTime());
		}

		JTable table = new JTable(reservationHashMap.size(), columns.length);
		int row = 0;
		for(Entry<String, reservation> entry: reservationHashMap.entrySet()){
		      table.setValueAt(entry.getValue().studentName,row,0);
		      table.setValueAt(entry.getValue().emailAddress,row,1);
		      table.setValueAt(entry.getValue().question,row,2);
		      table.setValueAt(entry.getValue().reservedTime,row,3);
		      JTableHeader th = table.getTableHeader();
		      TableColumnModel tcm = th.getColumnModel();
		      TableColumn tc0 = tcm.getColumn(0);
		      TableColumn tc1 = tcm.getColumn(1);
		      TableColumn tc2 = tcm.getColumn(2);
		      TableColumn tc3 = tcm.getColumn(3);
		      tc0.setHeaderValue( "Name" );
		      tc1.setHeaderValue("Email ID");
		      tc2.setHeaderValue("Question");
		      tc3.setHeaderValue("Reserved time");
		      th.repaint();
		      row++;
		 }
		
		

		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JFrame newframe = new JFrame();
		newframe.setBounds(100, 100, 800, 800);

		JButton btnQueue = new JButton("Queue");
		btnQueue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_counter++;				
				JScrollPane scrollPane_table = new JScrollPane(table);
				scrollPane_table.add(table.getTableHeader());
				scrollPane_table.setBounds(54, 47, 700, 700);
				if (!(btn_counter % 2 == 0)) {
					//frame.getContentPane().add(scrollPane_table);
					
					newframe.getContentPane().add(scrollPane_table);
					scrollPane_table.setVisible(true);
					newframe.setVisible(true);
				}
				else {
					newframe.setVisible(false);
					
					
				}
				
			}
		});
		btnQueue.setBounds(877, 6, 117, 29);
		frame.getContentPane().add(btnQueue);
		
		
	}

	// To generate random number of reservations everytime.
	private static int randomReservations(int minimum, int maximum) {
		int numberOfReservations = 0;
		try {
			if (!(minimum > maximum)) {
				Random randomReservations = new Random();
				numberOfReservations = randomReservations.nextInt((maximum - minimum) + 1) + minimum;
			}
		} catch (Exception e) {
			System.out.println("Method: @randomReservations :::: Exception Raised ");
			e.printStackTrace();
		}
		return numberOfReservations;
	}

	public static class reservation {
		public String studentName;
		public String emailAddress;
		public String question;
		public String reservedTime;

		public reservation(String studentName, String emailAddress, String question, String reservedTime) {
			this.studentName = studentName;
			this.emailAddress = emailAddress;
			this.question = question;
			this.reservedTime = reservedTime;
		}

		public String getStudentName() {
			return studentName;
		}

		public void setStudentName(String studentName) {
			this.studentName = studentName;
		}

		public String getEmailAddress() {
			return emailAddress;
		}

		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public String getReservedTime() {
			return reservedTime;
		}

		public void setReservedTime(String reservedTime) {
			this.reservedTime = reservedTime;
		}
	}
}
