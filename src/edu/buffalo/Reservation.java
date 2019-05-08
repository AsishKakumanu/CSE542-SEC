package edu.buffalo;

/**
 * This is a POJO class for reservation. The reservation class will have the following properties student, reservationId, 
 * reservationTime and reservationStatus.
 * @author miki
 *
 */
public class Reservation {
	private Student student;
	private int reservationId;
	private String reservationTime;
	private String reservationStatus;
	
	//Constructor for reservation class.
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
	
	protected String getReservationStatus() {
		return reservationStatus;
	}
	protected void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	
}
