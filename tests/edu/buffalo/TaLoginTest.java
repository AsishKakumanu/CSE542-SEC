package edu.buffalo;

import static org.junit.Assert.*;

import java.awt.Container;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;

import org.junit.Test;

public class TaLoginTest {
	
	TaLogin taLogin = new TaLogin();
	Queue<Reservation> reservationQueue = new LinkedList<Reservation>();
	
	Student stud1 = new Student("Atrayee", "atrayeen@buffalo.edu", null, "Where is Atrayee");
	Student stud2 = new Student("Miki", "miki@buffalo.edu", null, "Where is Miki");
	Student stud3 = new Student("Asish", "asish@buffalo.edu", null, "Where is Asish");
	Student stud4 = new Student("Bhagyashree", "bthorat@buffalo.edu", null, "Where is Bhagyashree");
	Student stud5 = new Student("Yijiang", "yijiang@buffalo.edu", null, "Where is Yijiang");
	
	Reservation res1 = new Reservation(1, "05/06/2019 14:20:58", stud1);
	Reservation res2 = new Reservation(2, "05/06/2019 14:25:58", stud2);
	Reservation res3 = new Reservation(3, "05/06/2019 14:40:58", stud3);
	Reservation res4 = new Reservation(4, "05/06/2019 14:50:58", stud4);
	Reservation res5 = new Reservation(5, "05/06/2019 15:10:58", stud5);
	
	@Test
	public void testCheckIfStudentIsBanned() {
		
		String scheduledReservationTime = "05/06/2019 14:20:58";
		boolean isBanned = taLogin.checkReservationPassedTime(scheduledReservationTime);
		assertEquals(true, isBanned);
		
	}
	
	@Test
	public void testCheckQueueDetailsWhenQueueIsEmpty() {

		String queueDetails = taLogin.checkReservationQueue(reservationQueue);
		assertTrue(queueDetails.isEmpty());
		
	}
	
	@Test
	public void testCheckQueueDetailsWhenQueueIsNotEmpty() {

		reservationQueue.add(res1);
		String result = taLogin.checkReservationQueue(reservationQueue);
		assertFalse(result.isEmpty());
		
	}
	
	@Test
	public void testRemoveReservationWhenStudentIsBanned() {
		
		reservationQueue.add(res1);
		reservationQueue.add(res2);
		reservationQueue.add(res3);
		reservationQueue.add(res4);
		reservationQueue.add(res5);
		
		Queue<Reservation> updatedQueue = taLogin.changeReservationStatus(res1, true, reservationQueue);
		
		assertFalse(updatedQueue.contains(res1));
	}
	
	@Test
	public void testMoveToEndOfQueueWhenAbsent() {
		
		reservationQueue.add(res1);
		reservationQueue.add(res2);
		reservationQueue.add(res3);
		reservationQueue.add(res4);
		reservationQueue.add(res5);
		
		Queue<Reservation> updatedQueue = taLogin.changeReservationStatus(res1, false, reservationQueue);
		
		assertEquals(updatedQueue.peek(), res2);
	}

}
