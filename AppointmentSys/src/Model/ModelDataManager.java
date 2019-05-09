package Model;

import java.util.List;

import Common.Appointment;
import View.MainFrame;

public class ModelDataManager {
	private MainFrame frame;
	private AppointQueue appointQueue;
	private Appointment currentAppointment;
	public ModelDataManager() {
		
	}
	public ModelDataManager(MainFrame frame) {
		this.frame = frame;		
	}
	public void init()
	{
		appointQueue = new AppointQueue(5);
		currentAppointment = appointQueue.getNextAppointment();
		if(currentAppointment==null)
			frame.queueShow(appointQueue.toStringArray());
		else
			frame.TAreportShow(currentAppointment);
	}
	public int presentReport() {
		int response = appointQueue.presentHandle(currentAppointment.getID());
		if(response == 0)
			frame.queueShow(appointQueue.toStringArray());
		return response;
	}
    public int absenceReport() {
    	int response = appointQueue.absenceHandle(currentAppointment.getID());
    	if(response == 0) {
    		currentAppointment = appointQueue.getNextAppointment();
    		if(currentAppointment==null)
    			frame.queueShow(appointQueue.toStringArray());
    		else
    			frame.TAreportShow(currentAppointment);
    	}    		
		return response;		
	}
    public int absenceFinishReport() {
    	int response = appointQueue.absenceHandle(currentAppointment.getID());
    	if(response == 0)
			frame.queueShow(appointQueue.toStringArray());
		return response;
		
	}
    public String getBanList() {
    	String banList = appointQueue.getBanListString();
    	if(banList == null)
    		return null;
    	else
    		return banList;
    }
	public AppointQueue getAppointQueue() {
		return appointQueue;
	}
	public void setAppointQueue(AppointQueue appointQueue) {
		this.appointQueue = appointQueue;
	}
	public Appointment getCurrentAppointment() {
		return currentAppointment;
	}
	public void setCurrentAppointment(Appointment currentAppointment) {
		this.currentAppointment = currentAppointment;
	}
}
