package Model;

import java.util.List;

import Common.Appointment;
import View.MainFrame;

/**
 * @author wyj19
 * Connect the model and view by provide data for view update,  view page jumping and provide Model operation interface for view action 
 */
public class ModelDataManager {
	private MainFrame frame;
	private AppointQueue appointQueue;
	private Appointment currentAppointment;
	public ModelDataManager() {
		
	}
	/**
	 * @param frame
	 * Create the ModelDataManager blind with a frame
	 */
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
	
	/**
	 * @return the response code for the report
	 * support a interface for panel present button action to call
	 */
	public int presentReport() {
		int response = appointQueue.presentHandle(currentAppointment.getID());
		if(response == 0)
			frame.queueShow(appointQueue.toStringArray());
		return response;
	}
	/**
	 * @return the response code for the report
	 * support a interface for panel absent button action to call
	 */
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
    /**
	 * @return the response code for the report
	 * support a interface for panel absent button action to call
	 */
    public int absenceFinishReport() {
    	int response = appointQueue.absenceHandle(currentAppointment.getID());
    	if(response == 0)
			frame.queueShow(appointQueue.toStringArray());
		return response;
		
	}
    /**
     * @return banlist content in String
     * get the banlist content from AppointQueue
     */
    public String getBanList() {
    	String banList = appointQueue.getBanListString();
    	if(banList == null)
    		return null;
    	else
    		return banList;
    }
    
	/**
	 * @return AppointQueue
	 *  get AppointQueue for test
	 */
	public AppointQueue getAppointQueue() {
		return appointQueue;
	}
	/**
	 * @return currentAppointment
	 *  get CurrentAppointment for test
	 */
	public Appointment getCurrentAppointment() {
		return currentAppointment;
	}
	
}
