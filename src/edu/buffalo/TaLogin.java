package edu.buffalo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Queue;
import java.util.TimeZone;

import javax.swing.*;

public class TaLogin {

	static Queue<Reservation> reservationQueue;

	// Names & email
	private static Hashtable<String, String> nameTable = new Hashtable<String, String>() {
		{
			put("Miki Padhiary", "mpadh@buffalo.edu");
			put("Atrayee Nag", "anag@buffalo.edu");
			put("Bhagyashree Throat", "bthroat@buffalo.edu");
			put("Ashish Kakumanyu", "akakumanyu@buffalo.edu");
			put("Yijiyang Wang", "ywang@buffalo.edu");
		}
	};

	// Questions
	private static ArrayList<String> questionList = new ArrayList<String>() {
		{
			add("Where is Miki ?");
			add("Where is Atrayee ?");
			add("Where is Bhagyashree ?");
			add("Where is Ashish ?");
			add("Where is Yijiyang ?");
		}
	};

	private static ArrayList<String> finalTimeList = new ArrayList<String>() {
	};

	// CurrentTime
	private static ArrayList<Date> timeList = new ArrayList<Date>() {
		{
			getTime(5);
			getTime(11);
			getTime(0);
		}
	};

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TaLogin();
			}
		});
	}

	public TaLogin() {
		ReservationGenerator reservationGenerator = new ReservationGenerator();
		List<String> shuffledQuestionList;
		List<String> shuffledTimeList;
		List<String> shuffledNameList;
		shuffledQuestionList = getShuffledQuestionList(questionList);
		shuffledTimeList = getShuffledTimeList(finalTimeList);
		for (int i = 0; i < shuffledTimeList.size(); i++) {
			System.out.println(shuffledTimeList.get(i));
		}
		ArrayList keys = new ArrayList<>(nameTable.keySet());
		shuffledNameList = getShuffledNameList(keys);
		reservationQueue = reservationGenerator.generateReservations(0, 4, shuffledQuestionList, shuffledTimeList,
				shuffledNameList, nameTable);

		if (reservationQueue.isEmpty()) {
			System.out.println(reservationQueue.size());
		} else {
			for (Reservation res : reservationQueue) {
				System.out.println(res.getStudent().getStudentName());
				System.out.println(res.getStudent().getStudentEmailId());
				System.out.println(res.getStudent().getStudentQueries());
				System.out.println(res.getReservationTime());
				System.out.println(res.getReservationId());
				System.out.println(res.getStudent().getStudentName());
				System.out.println(
						"------------------------------------------------------------------------------------------");
			}
			displayResults(reservationQueue);
		}
	}

	private void displayResults(Queue<Reservation> reservationQueue) {
		if (reservationQueue.isEmpty()) {
			displayEmptyQueue();
		} else {
			JFrame mainFrame = initializeFrame();
			JLabel studentLabel = new JLabel("Student Details:");
			studentLabel.setFont(new Font("Verdana", Font.BOLD, 30));
			studentLabel.setForeground(Color.white);
			studentLabel.setBounds(500, 80, 500, 100);
			mainFrame.getContentPane().add(studentLabel);

			JLabel studentNameLabel = new JLabel("Student Name:");
			studentNameLabel.setFont(new Font("Verdana", Font.BOLD, 30));
			studentNameLabel.setForeground(Color.white);
			studentNameLabel.setBounds(250, 140, 500, 100);
			mainFrame.getContentPane().add(studentNameLabel);

			JLabel studentEmailLabel = new JLabel("Student Email:");
			studentEmailLabel.setFont(new Font("Verdana", Font.BOLD, 30));
			studentEmailLabel.setForeground(Color.white);
			studentEmailLabel.setBounds(250, 210, 500, 100);
			mainFrame.getContentPane().add(studentEmailLabel);

			JLabel studentQuestionLabel = new JLabel("Student Queries:");
			studentQuestionLabel.setFont(new Font("Verdana", Font.BOLD, 30));
			studentQuestionLabel.setForeground(Color.white);
			studentQuestionLabel.setBounds(250, 280, 500, 100);
			mainFrame.getContentPane().add(studentQuestionLabel);

			JLabel studentScheduledTimeLabel = new JLabel("Scheduled Time:");
			studentScheduledTimeLabel.setFont(new Font("Verdana", Font.BOLD, 30));
			studentScheduledTimeLabel.setForeground(Color.white);
			studentScheduledTimeLabel.setBounds(250, 340, 500, 100);
			mainFrame.getContentPane().add(studentScheduledTimeLabel);

			JLabel currentTimeLabel = new JLabel("Current Time:");
			currentTimeLabel.setFont(new Font("Verdana", Font.BOLD, 30));
			currentTimeLabel.setForeground(Color.white);
			currentTimeLabel.setBounds(250, 410, 500, 100);
			mainFrame.getContentPane().add(currentTimeLabel);

			JLabel studentNameLabelValue = new JLabel(reservationQueue.peek().getStudent().getStudentName());
			studentNameLabelValue.setFont(new Font("Verdana", Font.BOLD, 30));
			studentNameLabelValue.setForeground(Color.white);
			studentNameLabelValue.setBounds(650, 140, 500, 100);
			mainFrame.getContentPane().add(studentNameLabelValue);

			JLabel studentEmailLabelValue = new JLabel(reservationQueue.peek().getStudent().getStudentEmailId());
			studentEmailLabelValue.setFont(new Font("Verdana", Font.BOLD, 30));
			studentEmailLabelValue.setForeground(Color.white);
			studentEmailLabelValue.setBounds(650, 210, 500, 100);
			mainFrame.getContentPane().add(studentEmailLabelValue);

			JLabel studentQuestionLabelValue = new JLabel(reservationQueue.peek().getStudent().getStudentQueries());
			studentQuestionLabelValue.setFont(new Font("Verdana", Font.BOLD, 30));
			studentQuestionLabelValue.setForeground(Color.white);
			studentQuestionLabelValue.setBounds(650, 280, 500, 100);
			mainFrame.getContentPane().add(studentQuestionLabelValue);

			JLabel studentScheduledTimeLabelValue = new JLabel(reservationQueue.peek().getReservationTime());
			studentScheduledTimeLabelValue.setFont(new Font("Verdana", Font.BOLD, 30));
			studentScheduledTimeLabelValue.setForeground(Color.white);
			studentScheduledTimeLabelValue.setBounds(650, 340, 500, 100);
			mainFrame.getContentPane().add(studentScheduledTimeLabelValue);

			JLabel currentTimeLabelValue = new JLabel("");
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date date = new Date();
			currentTimeLabelValue.setText(String.valueOf(dateFormat.format(date)));
			currentTimeLabelValue.setFont(new Font("Verdana", Font.BOLD, 30));
			currentTimeLabelValue.setForeground(Color.white);
			currentTimeLabelValue.setBounds(650, 410, 500, 100);
			mainFrame.getContentPane().add(currentTimeLabelValue);

			JButton absentButton = new JButton("Absent");
			absentButton.setFont(new Font("Verdana", Font.BOLD, 30));
			absentButton.setForeground(Color.black);
			absentButton.setBounds(250, 630, 200, 100);
			mainFrame.getContentPane().add(absentButton);
			absentButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					mainFrame.dispose();
				}
			});
			

			JButton presentButton = new JButton("Present");
			presentButton.setFont(new Font("Verdana", Font.BOLD, 30));
			presentButton.setForeground(Color.black);
			presentButton.setBounds(750, 630, 200, 100);
			mainFrame.getContentPane().add(presentButton);
			presentButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.dispose();
				}
			});
			mainFrame.setVisible(true);
		}

	}

	private void displayEmptyQueue() {
		JFrame mainFrame = initializeFrame();
		JLabel noReservationLabel = new JLabel("No Reservation Found. Please click on exit.");
		noReservationLabel.setFont(new Font("Verdana", Font.BOLD, 30));
		noReservationLabel.setForeground(Color.white);
		noReservationLabel.setBounds(400, 240, 1400, 100);
		mainFrame.getContentPane().add(noReservationLabel);

		JButton exitButton = new JButton("Exit");
		exitButton.setFont(new Font("Verdana", Font.BOLD, 30));
		exitButton.setForeground(Color.black);
		exitButton.setBounds(500, 630, 200, 100);
		mainFrame.getContentPane().add(exitButton);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
			}
		});
		mainFrame.setVisible(true);
	}

	private JFrame initializeFrame() {
		JFrame mainFrame = new JFrame("TA Portal");
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800, 800);
		mainFrame.setBounds(800, 800, 800, 800);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.getContentPane().setBackground(Color.gray);

		JLabel taLabel = new JLabel("Appointment Portal");
		taLabel.setFont(new Font("Verdana", Font.BOLD, 30));
		taLabel.setForeground(Color.white);
		taLabel.setBounds(500, 20, 500, 100);
		mainFrame.getContentPane().add(taLabel);
		return mainFrame;
	}

	/**
	 * This method shuffles the question list array.
	 * 
	 * @param questionList
	 * @return
	 */
	private static List<String> getShuffledQuestionList(ArrayList<String> questionList) {
		Collections.shuffle(questionList);
		return questionList;
	}

	/**
	 * This method shuffles the time list array.
	 * 
	 * @param timeList
	 * @return
	 */
	private static List<String> getShuffledTimeList(ArrayList<String> timeList) {
		Collections.shuffle(timeList);
		return timeList;
	}

	/**
	 * This method shuffles the name list array.
	 * 
	 * @param keys
	 * @return
	 */
	private static List<String> getShuffledNameList(ArrayList<String> keys) {
		Collections.shuffle(keys);
		return keys;
	}

	/**
	 * This method returns the old time after subtracting the minutes passed.
	 * 
	 * @param minutes
	 * @return
	 */
	private static String getTime(int minutes) {
		final long ONE_MINUTE_IN_MILLIS = 60000;
		Calendar newDate = Calendar.getInstance();
		long currentTime = newDate.getTimeInMillis();
		Date newTime = new Date(currentTime - (minutes * ONE_MINUTE_IN_MILLIS));
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		String dateFormatted = String.valueOf(dateFormat.format(newTime));
		finalTimeList.add(dateFormatted);
		return dateFormatted;
	}

}
