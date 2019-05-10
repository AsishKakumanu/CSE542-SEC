/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Common.Appointment;
import Model.ModelDataManager;
import View.MainFrame;

/**
 * @author wyj19
 *
 */
class AbsentHandleTest {

	/**
	 * @throws java.lang.Exception
	 */
	static MainFrame frame;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		frame = new MainFrame();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		do{
			frame.init();			 
		}while(frame.getModelDataManager().getCurrentAppointment() == null);		
	
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		frame = new MainFrame();
	}

	@Test
	void banListAppendTest() {	
		Date d = new Date();
		int flag = 0;
		if(d.getTime() - frame.getModelDataManager().getCurrentAppointment().getDate().getTime() <10*60000)
			flag = 1;
		frame.getModelDataManager().absenceFinishReport();
		if(flag == 1)
			assertNull(frame.getModelDataManager().getBanList());  // if attend in 10min won't be banned
		else
			assertEquals(frame.getModelDataManager().getAppointQueue().getBanList().size(),1); // if attend in too late banned
	}
	@Test
	void queueRemoveTest() {
		Date d = new Date();
		int flag = 0;
		int len =frame.getModelDataManager().getAppointQueue().getQueue().size();		
//		System.out.println("len = "+len);
		if(d.getTime() - frame.getModelDataManager().getCurrentAppointment().getDate().getTime() <10*60000)
			flag = 1;
		frame.getModelDataManager().absenceFinishReport();
		if(flag == 1)
			len = len+1;	// queue len increase one(re-Add) if attend in 10min late 
//		System.out.println("len 2 = "+len);
//		System.out.println("size = "+frame.getModelDataManager().getAppointQueue().getQueue().size());
		assertEquals(len,frame.getModelDataManager().getAppointQueue().getQueue().size()); // check the queue len if attend too late the len won't add one
	}	
	@Test
	void queueReAddTest() {
		Date d = new Date();
		int flag = 0;
		int len =frame.getModelDataManager().getAppointQueue().getQueue().size();
		int curID = frame.getModelDataManager().getCurrentAppointment().getID();
//		System.out.println("curID = "+curID);
		if(d.getTime() - frame.getModelDataManager().getCurrentAppointment().getDate().getTime() <10*60000)
			flag = 1;
		frame.getModelDataManager().absenceFinishReport();
		Appointment lastAppointment = null;
		// get the tail element in the queue which is re-Add if in 10min late, it will be the last report Appointment ID
		for (Appointment x : frame.getModelDataManager().getAppointQueue().getQueue()) { 
			lastAppointment = x;   			
        } 
		if(flag == 1)
			assertEquals(curID,lastAppointment.getID()); //compare the last report id and the tail appointment id
		else
			assertEquals(frame.getModelDataManager().getAppointQueue().getQueue().size(),len); // check the queue len if attend too late the len won't add one
		
	}	
	@Test
	void reportWhenEmptyTest() {
		int curID = frame.getModelDataManager().getCurrentAppointment().getID();
		int response0 = frame.getModelDataManager().getAppointQueue().absenceHandle(curID);
		int response = frame.getModelDataManager().getAppointQueue().absenceHandle(curID); // report same ID twice, already process once list empty now
		assertEquals(0,response0); // success report
		assertEquals(1,response);  // response the empty Processing Appointment List error code
	}
	@Test
	void wrongRrportIDTest() {
		int curID = frame.getModelDataManager().getCurrentAppointment().getID();
		int testID = curID+1;   // the test report id is not in the processing List where curID is locate in. The test id is not.
		int response = frame.getModelDataManager().getAppointQueue().absenceHandle(testID);
		assertEquals(2,response); //response the Processing Appointment List Wrong ID code
	}


}
