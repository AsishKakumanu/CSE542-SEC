package edu.buffalo;


import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.*;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

import edu.buffalo.Reservation;
import edu.buffalo.ReservationGenerator;
import edu.buffalo.Student;

public class ReservationTest {

	ReservationGenerator revGen = new ReservationGenerator();
	@Test
	public void testReservation() {
		//fail("Not yet implemented");
		int low = 2;
		int high = 4;
		List<String> shuffledQuestionList = new ArrayList<String>();
		
		shuffledQuestionList.add("Where is Miki ?");
		shuffledQuestionList.add("Where is Atrayee ?");
		shuffledQuestionList.add("Where is Bhagyashree ?");
		shuffledQuestionList.add("Where is Ashish ?");
		shuffledQuestionList.add("Where is Yijiyang ?");
		
		List<String> shuffledTimeList = new ArrayList<String>();
		shuffledTimeList.add("05/06/2019 14:12:58");
		shuffledTimeList.add("05/06/2019 14:15:58");
		shuffledTimeList.add("05/06/2019 14:16:58");
		shuffledTimeList.add("05/06/2019 14:19:58");
		shuffledTimeList.add("05/06/2019 14:20:58");
		
		List<String> shuffledNameList = new ArrayList<String>();
		shuffledNameList.add("Miki");
		shuffledNameList.add("Asish");
		shuffledNameList.add("Atrayee");
		shuffledNameList.add("Bhagyashree");
		shuffledNameList.add("Yijiyang");
		
		
		Hashtable<String, String> nameTable = new Hashtable<String, String>();
		nameTable.put("Miki Padhiary", "mpadh@buffalo.edu");
		nameTable.put("Atrayee Nag", "anag@buffalo.edu");
		nameTable.put("Bhagyashree Throat", "bthroat@buffalo.edu");
		nameTable.put("Ashish Kakumanyu", "akakumanyu@buffalo.edu");
		nameTable.put("Yijiyang Wang", "ywang@buffalo.edu");
		
		Queue<Reservation> reservationQueue = new LinkedList<Reservation>();
		
		Reservation reservation = new Reservation(0, "05/06/2019 14:20:58", new Student("Miki", nameTable.get(0), null,  "Where is miki"));
		reservationQueue.add(reservation);
		revGen.generateReservations(low, high, shuffledQuestionList, shuffledTimeList, shuffledNameList, nameTable);
		assertNotNull(reservationQueue);
		
	}

}
