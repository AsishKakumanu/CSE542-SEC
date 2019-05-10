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
	void BanListAppendTest() {	
		Date d = new Date();
		int flag = 0;
		if(d.getTime() - frame.getModelDataManager().getCurrentAppointment().getDate().getTime() <10*60000)
			flag = 1;
		frame.getModelDataManager().absenceFinishReport();
		if(flag == 1)
			assertNull(frame.getModelDataManager().getBanList());
		else
			assertEquals(frame.getModelDataManager().getBanList().length(),1);
	}
	@Test
	void QueueRemoveTest() {
		Date d = new Date();
		int flag = 0;
		int len =frame.getModelDataManager().getAppointQueue().getQueue().size();		
		System.out.println("len = "+len);
		if(d.getTime() - frame.getModelDataManager().getCurrentAppointment().getDate().getTime() <10*60000)
			flag = 1;
		frame.getModelDataManager().absenceFinishReport();
		if(flag == 1)
			len = len+1;	
		System.out.println("len 2 = "+len);
		System.out.println("size = "+frame.getModelDataManager().getAppointQueue().getQueue().size());
		assertEquals(len,frame.getModelDataManager().getAppointQueue().getQueue().size());
	}	
	@Test
	void QueueReAddTest() {
		Date d = new Date();
		int flag = 0;
		int len =frame.getModelDataManager().getAppointQueue().getQueue().size();
		int curID = frame.getModelDataManager().getCurrentAppointment().getID();
		System.out.println("curID = "+curID);
		if(d.getTime() - frame.getModelDataManager().getCurrentAppointment().getDate().getTime() <10*60000)
			flag = 1;
		frame.getModelDataManager().absenceFinishReport();
		Appointment lastAppointment = null;
		for (Appointment x : frame.getModelDataManager().getAppointQueue().getQueue()) { 
			lastAppointment = x; 
			System.out.println("xID = "+x.getID());
        } 
		if(flag == 1)
			assertEquals(curID,lastAppointment.getID());
		else
			assertEquals(frame.getModelDataManager().getAppointQueue().getQueue().size(),len);
		
	}
	
	void WrongRrportIDTest() {
		
		
		fail("Not yet implemented");
	}


}
