package edu.buffalo;

import static org.junit.Assert.*;

import java.awt.Container;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;

import org.junit.Test;

public class TaLoginTest {
	
	TaLogin taLogin = new TaLogin();
	Container container = new Container();
	JFrame mainFrame = new JFrame();
	Queue<Reservation> reservationQueue = new LinkedList<Reservation>();
	Student stud1 = new Student("Atrayee", "atrayeen@buffalo.edu", null, "Where is Atrayee");
	Reservation res1 = new Reservation(1, "05/06/2019 14:20:58", stud1);
	
//	@Test
//	public void testTaLoginPresent() {
//		reservationQueue.add(res1);
//		taLogin.presentButton(reservationQueue, mainFrame);
//		assertEquals(reservationQueue.size(), 1);
//		
//	}
	
	@Test
	public void testCheckReservationPassedTime() {
		
		String scheduledReservationTime = "05/06/2019 14:20:58";
		boolean isBanned = taLogin.checkReservationPassedTime(scheduledReservationTime);
		assertEquals(true, isBanned);
		
	}

}
