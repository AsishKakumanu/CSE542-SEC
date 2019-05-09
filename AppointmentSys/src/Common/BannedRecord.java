package Common;

import java.util.Date;

public class BannedRecord {
	private Date date;
	private String email;
	public BannedRecord(String email2, Date d2) {
		// TODO Auto-generated constructor stub
		this.date = d2;
		this.email = email2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date d) {
		this.date = date;
	}
	
}
