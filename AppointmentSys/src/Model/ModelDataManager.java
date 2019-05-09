package Model;

import java.util.List;

import Common.Appointment;
import View.MainFrame;

public class ModelDataManager {
	private MainFrame frame;
	AppointQueue appointQueue;
	Appointment currentAppointment;
	public ModelDataManager() {
		
	}
	public ModelDataManager(MainFrame frame) {
		appointQueue = new AppointQueue(5);
		this.frame = frame;
		
	}
	public void init()
	{
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
}
