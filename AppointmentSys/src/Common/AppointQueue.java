package Common;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class AppointQueue {
	private Queue<Appointment> queue;
	private Date d;
	private long seed;
	private List <Appointment> processingAppointment;
	private List <BannedRecord> banList;
	Random ran;
	AppointQueue(){
		processingAppointment = new ArrayList<Appointment>();
		banList = new ArrayList<BannedRecord>();
		queue = new LinkedList<Appointment>();

	}
    AppointQueue(int maxLen){
    	// Names & email
    	this();
    	Hashtable<String,String> nameTable= new Hashtable<String, String>(){
            {
                put("Steve Rogers","steverog@buffalo.edu");
                put("Tony Stark","tonystar@buffalo.edu");
                put("Nick Fury","nickfury@buffalo.edu");
                put("Dr.Strange","dstrange@buffalo.edu");
                put("Bruce Banner","bruceban@buffalo.edu");
                put("Scott Lang","scottlan@buffalo.edu");

            }
        };

        // Questions
        ArrayList<String> questionList = new ArrayList<String>(){
            {
                add("Where is nick fury ?");
                add("Where is thanos ?");
                add("Tell me more about time heist");
                add("What is a pim particle");
                add("Who sacrificed himself/herself to revert the changes made by thanos ?");
            }
        };
        // Shuffling members
        ArrayList keys = new ArrayList<>(nameTable.keySet());
        Collections.shuffle(keys);

        // Shuffling questions
        Collections.shuffle(questionList);
		d = new Date();
		
		seed  = d .getTime();
		ran = new Random(seed);
		int len = getRandomNum(maxLen);
		System.out.println(len);
		for (int i = 0; i< len ;i++) {			
			String name = keys.get(i).toString();
            String email = nameTable.get(keys.get(i));
            String question = questionList.get(i).toString();
            Appointment newAppointment = new Appointment(i,d,email,name,question);
            
			int lateDate = getRandomNum(2);
			switch(lateDate) {
			case 0:
				newAppointment.setDate(new Date(d.getTime()-5*60000));
				System.out.println("date5 "+new Date(d.getTime()-5*60000).toString());
				break;
			case 1:
				newAppointment.setDate(new Date(d.getTime()-11*60000));
				System.out.println("date11 "+new Date(d.getTime()-11*60000).toString());
				break;
			case 2: 
				;
				break;
				
			}
			System.out.println("date new "+newAppointment.getDate());
			int blankQuestion = getRandomNum(1);
			switch(blankQuestion) {
			case 0:
				newAppointment.setQuestion("");
				break;
			case 1:
				;
				break;
			}
			queue.offer(newAppointment);
		}
		
				
	}
	
	

	private int getRandomNum(int max)	{
		
		
		int n = ran.nextInt()%(max+1);
		if(n<0)
			n=-n;
		return n;
	}
	public Appointment getNextAppointment() {
		if(queue.isEmpty()) {
			System.out.println("null");			
			System.out.println(queue.size());		
			
			return null;
		}
		Appointment firstAppointment = queue.remove();
		System.out.println(firstAppointment.getName());
		processingAppointment.add(firstAppointment);
		return firstAppointment;
	}
	public int absenceHandle(int ID) {
		if(processingAppointment.isEmpty())
			return 1;
		
		for(Appointment   appointment : processingAppointment)    {   
		       if(appointment.getID() == ID) {
		    	   d = new Date();
		    	   if(d.getTime() - appointment.getDate().getTime()<10*60000) {
		    		   queue.add(appointment);
		    		   processingAppointment.remove(appointment);
		    	   }else
		    	   {
		    		   //ban code add here
		    		   BannedRecord b = new BannedRecord(appointment.getEmail(),d);
		    		   banList.add(b);
		    		   processingAppointment.remove(appointment);		    		   		    		   
		    	   }
		    	   return 0; // Successful processed
		       }
		   }
		return 2;// can't find the corresponding Appointment by ID 
		
	}
	public int presentHandle(int ID) {
		if(processingAppointment.isEmpty())
			return 1;	//no processing Appointment	
		for(Appointment   appointment : processingAppointment)    {   
		       if(appointment.getID() == ID) {   	   
		    	   processingAppointment.remove(appointment);	
		    	   return 0;		    	   
		       }
		   }
		return 2; // can't find the corresponding Appointment by ID 
		
	}
	public List <BannedRecord> getBanList() {
		return banList;
	}
	public void setBanList(List <BannedRecord> banList) {
		this.banList = banList;
	}
	public String[][] toStringArray(){
		if(queue.isEmpty()) {
			System.out.println("queue empty");
			System.out.println(queue.size());		
			
			return null;
		}
		String[][] queueData = new String[queue.size()][5];
		int i = 0;
		for (Appointment queElem : queue) { 
			queueData[i][0] = ""+queElem.getID();			
			queueData[i][1] = queElem.getName();
			queueData[i][2] = queElem.getEmail();
			queueData[i][3] = queElem.getDate().toString();
			queueData[i][4] = queElem.getQuestion();	
			i++;
			}
//		System.out.println(queueData[1][0]+" "+queueData[1][1]);
		System.out.println("queue "+queueData.length);
		return queueData;
	}
	public String getBanListString(){
		if(banList.isEmpty()) {
			System.out.println("queue banlist");
			System.out.println(queue.size());				
			return null;
		}
		String banListString = "";
		
		for (BannedRecord record : banList) { 
			banListString += "Email: " + record.getEmail() + "|Date: " + record.getDate().toString() +"\n";					
			}
		return banListString;
	}

}
