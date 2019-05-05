package edu.buffalo;

import java.util.Date;

public class Reservation {
	private Student student;
	private int reservationId;
	private String reservationTime;
	
	public Reservation(int reservationId, String reservationTime, Student student)
	{
		this.reservationId = reservationId;
		this.reservationTime = reservationTime;
		this.student = student;
	}
	protected Student getStudent() {
		return student;
	}
	protected void setStudent(Student student) {
		this.student = student;
	}
	
	protected int getReservationId() {
		return reservationId;
	}
	protected void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}	
	
	protected String getReservationTime() {
		return reservationTime;
	}
	protected void setReservationTime(String reservationTime) {
		this.reservationTime = reservationTime;
	}
	
}
