package hex.dl.scripts.testcase;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import hex.dl.common.utils.ADataHelper;
import hex.dl.scripts.objects.StocklandHome.StocklandHome;

import hex.dl.scripts.objects.homepage.Homepage;
import hex.dl.scripts.objects.loginpage.Login;
//import hex.dl.scripts.objects.registaration.DemoRegistrationForm;

public class DLTC001 extends ADataHelper {

	public static void  TestScript() throws Exception {
		
		/*Homepage.edtFlyFrom().Set(getData("FROMCITY"),Keys.TAB, true);
		Homepage.edtFlyTo().Set(getData("TOCITY"),Keys.TAB,true);
		Homepage.edtDepDate().Set(getData("DATE"),Keys.TAB,true);
		Homepage.edtArrDate().Set(getData("RETURNDATE"),Keys.TAB,true);
		Homepage.drpNoOfSeat().selectByVisibleText(getData("NOSEAT"), true);
		//Verifying after selecting the dropdown
		Homepage.drpNoOfSeat().verifySelectedItem(getData("NOSEAT"), "Verifying selected item", true);
		Homepage.btnFind().click(true);
		//Choosing the Flight amount
		int strowcount = flightselection.wbtSelectFly().getRowCount();
		List<WebElement> strvalue = flightselection.wbtSelectFly().getRowList();
		System.out.println(strowcount); 
		System.out.println(strvalue);*/
		StocklandHome.lnkCorporate().click(true);
	}

	

}
