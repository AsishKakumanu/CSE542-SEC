package edu.buffalo;


import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

public class ReservationTest {

	ReservationGenerator revGen = new ReservationGenerator();
	List<String> shuffledQuestionList = new ArrayList<String>();
	List<String> shuffledTimeList = new ArrayList<String>();
	List<String> shuffledNameList = new ArrayList<String>();
	Hashtable<String, String> nameTable = new Hashtable<String, String>();
	Queue<Reservation> reservationQueue = new LinkedList<Reservation>();
	
	@Test
	public void testReservationWhenQueueIsNotEmpty() {
		int low = 0;
		int high = 1;
		
		shuffledQuestionList.add("Where is Miki ?");
		shuffledTimeList.add("05/06/2019 14:12:58");
		shuffledNameList.add("Miki");
		nameTable.put("Miki Padhiary", "mpadh@buffalo.edu");
		
		Reservation reservation = new Reservation(0, "05/06/2019 14:20:58", new Student("Miki", nameTable.get(0), null,  "Where is miki"));
		reservationQueue.add(reservation);
		
		revGen.generateReservations(low, high, shuffledQuestionList, shuffledTimeList, shuffledNameList, nameTable);
		assertEquals(reservationQueue.size(), 1);
		
	}
	
	
	@Test
	public void testReservationWhenQueueIsEmpty() {
		int low = 0;
		int high = 1;
		revGen.generateReservations(low, high, shuffledQuestionList, shuffledTimeList, shuffledNameList, nameTable);
		assertEquals(reservationQueue.size(), 0);
	}

}
