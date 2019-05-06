package edu.buffalo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.TimeZone;

/**
 * This class generates random number of reservations using generateReservations menthod.
 * @author miki
 *
 */
public class ReservationGenerator {
	Queue<Reservation> reservationQueue;
	DateFormat dateFormat;
	

	/**
	 * This method generates random number of reservations in between 1 to 4.
	 * @return
	 */
	public Queue<Reservation> generateReservations(int low, int high, List<String> shuffledQuestionList,
			List<String> shuffledTimeList, List<String> shuffledNameList, Hashtable<String, String> nameTable)
	{
		reservationQueue = new LinkedList<Reservation>();
		Random randomNumber = new Random();
		int numberOfReservation = randomNumber.nextInt(high-low) + low;	
    	
		for(int i=0; i<numberOfReservation; i++)
		{
	    	String reservationTime = shuffledTimeList.get(i);
			Reservation reservation = new Reservation(i, reservationTime, new Student(shuffledNameList.get(i),
					nameTable.get(shuffledNameList.get(i)), null, shuffledQuestionList.get(i)));
			reservationQueue.add(reservation);
		}		
		return reservationQueue;
	}

	
	
}
