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
import Common.BannedRecord;
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
	void checkEmptyBanListNotIncreaseWhenAbsentLongerThan10Min() {	
		Date d = new Date();
		d = new Date(d.getTime() - 11*60000);
		frame.getModelDataManager().getCurrentAppointment().setDate(d); //prepare the appointment time
		assertNull(frame.getModelDataManager().getBanList());// the ban list should be empty at begin
		
		frame.getModelDataManager().absenceFinishReport();//do report
		
		assertNotNull(frame.getModelDataManager().getBanList());// if attend in too late banned		
		assertEquals(frame.getModelDataManager().getAppointQueue().getBanList().size(),1); // the ban list should only have one record now
	}
	void checkNonEmptyBanListNotIncreaseWhenAbsentLongerThan10Min() {	
		Date d = new Date();
		d = new Date(d.getTime() - 11*60000);
		frame.getModelDataManager().getCurrentAppointment().setDate(d); //prepare the appointment time
		frame.getModelDataManager().getAppointQueue().getBanList().add(new BannedRecord("test@ub.edu", new Date()));// ban list should have one record now		
		int preSize = frame.getModelDataManager().getAppointQueue().getBanList().size();
		assertEquals(1,preSize);// check ban list have one record now
		
		frame.getModelDataManager().absenceFinishReport(); //do report
		
		assertNotNull(frame.getModelDataManager().getBanList());
		assertNotEquals(preSize,frame.getModelDataManager().getAppointQueue().getBanList().size()); // the ban list should have two record now
		assertEquals(2,frame.getModelDataManager().getAppointQueue().getBanList().size()); // the ban list should have two record now

	}
	@Test
	void checkEmptyBanListWronglyIncreaseWhenAbsentWhithin10Min() {	
		Date d = new Date();
		d = new Date(d.getTime() - 5*60000);
		frame.getModelDataManager().getCurrentAppointment().setDate(d); //prepare the appointment time
		int preSize = frame.getModelDataManager().getAppointQueue().getBanList().size();
		assertNull(frame.getModelDataManager().getBanList());// the ban list should be empty at begin

		frame.getModelDataManager().absenceFinishReport();//do report
		
		assertNull(frame.getModelDataManager().getBanList());// the ban list should still empty now
		assertNotEquals(frame.getModelDataManager().getAppointQueue().getBanList().size(),preSize+1); 
		assertEquals(frame.getModelDataManager().getAppointQueue().getBanList().size(),preSize); 
	}
	@Test
	void checkNonEmptyBanListWronglyIncreaseWhenAbsentWhithin10Min() {	
		Date d = new Date();
		d = new Date(d.getTime() - 5*60000);
		frame.getModelDataManager().getCurrentAppointment().setDate(d); //prepare the appointment time
		frame.getModelDataManager().getAppointQueue().getBanList().add(new BannedRecord("test@ub.edu", new Date()));// ban list should have one record now		
		int preSize = frame.getModelDataManager().getAppointQueue().getBanList().size();
		assertEquals(1,preSize);// check ban list have one record now
		frame.getModelDataManager().absenceFinishReport();//do report
		assertNotNull(frame.getModelDataManager().getBanList());
		assertEquals(preSize,frame.getModelDataManager().getAppointQueue().getBanList().size()); // the ban list should still have one record now
	}
	@Test
	void checkQueueNotReAddWhenAbsentWhithin10Min() {
		Date d = new Date();
		d = new Date(d.getTime() - 5*60000);	
		frame.getModelDataManager().getCurrentAppointment().setDate(d); //prepare the appointment time
		int len =frame.getModelDataManager().getAppointQueue().getQueue().size();//before report the queue len will pre-minus one by poll to process list
		
		frame.getModelDataManager().absenceFinishReport();//do report
		
		assertNotEquals(len,frame.getModelDataManager().getAppointQueue().getQueue().size()); // test queue len not same as begin 
		assertEquals(len + 1,frame.getModelDataManager().getAppointQueue().getQueue().size()); // queue len increase one(re-Add) when attend in 10min late 
	}	
	@Test
	void checkQueueReAddWhenAbsentLongerThan10Min() {
		Date d = new Date();
		d = new Date(d.getTime() - 11*60000);		
		frame.getModelDataManager().getCurrentAppointment().setDate(d); //prepare the appointment time
		int len =frame.getModelDataManager().getAppointQueue().getQueue().size();//before report the queue len will pre-minus one by poll to process list
		
		frame.getModelDataManager().absenceFinishReport();//do report
		
		assertNotEquals(len + 1,frame.getModelDataManager().getAppointQueue().getQueue().size()); // test queue len shuold not increase one
		assertEquals(len,frame.getModelDataManager().getAppointQueue().getQueue().size()); // test queue should same as begin the appointment just removed from processing list
	}	
	@Test
	void checkQueueElemReAddCorrectly() {
		Date d = new Date();
		d = new Date(d.getTime() - 5*60000);	
		frame.getModelDataManager().getCurrentAppointment().setDate(d); //prepare the appointment time		
		int curID = frame.getModelDataManager().getCurrentAppointment().getID();

		frame.getModelDataManager().absenceFinishReport(); //do report
		
		Appointment lastAppointment = null;
		// get the tail element in the queue which is re-Add if in 10min late, it should be the last report Appointment ID
		for (Appointment x : frame.getModelDataManager().getAppointQueue().getQueue()) { 
			lastAppointment = x;   			
        } 
		
		assertEquals(curID,lastAppointment.getID()); //compare the last report id and the tail appointment id
	}	
	@Test
	void reportWhenEmptyTest() {
		int curID = frame.getModelDataManager().getCurrentAppointment().getID();
		int response0 = frame.getModelDataManager().getAppointQueue().absenceHandle(curID);
		assertEquals(0,response0); // success report
		
		int response = frame.getModelDataManager().getAppointQueue().absenceHandle(curID); // report same ID twice, already process once list empty now
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
