package Common;
import java.util.Date;

public class Appointment {
	private int ID;
	private Date date;
	private String email;
	private String name;
	private String question;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	Appointment(){
		
	}
	public Appointment(int ID,Date date, String email, String name, String question){
		this.setID(ID);
		this.date = date;
		this.email = email;
		this.name = name;
		this.question = question;
		
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
}
