package hex.dl.scripts.testcase;

import java.awt.Robot;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.model.Log;
import com.sun.glass.events.KeyEvent;

import hex.dl.common.utils.ADataHelper;
import hex.dl.scripts.objects.StocklandHome.StocklandHome;

import hex.dl.scripts.objects.homepage.Homepage;
import hex.dl.scripts.objects.loginpage.Login;
//import hex.dl.scripts.objects.registaration.DemoRegistrationForm;
import hex.dl.scripts.objects.stocklandHomepage.Enquirepg;
import hex.dl.scripts.objects.stocklandHomepage.Findvillagepg;
import hex.dl.scripts.objects.stocklandHomepage.Retirementlivingpg;
import hex.framework.automation.AWebElement;
import hex.framework.browser.BrowserFactory;
import hex.framework.enums.Logstatus;
import sun.util.logging.resources.logging;

public class Stockland extends ADataHelper {

	public static void  TestScript() throws Exception {

		Robot robot = new Robot();
		
		StocklandHome.lnkCorporate().click(true);
		hex.dl.scripts.objects.stocklandHomepage.Homepage.lnkRetirementLiving().click(true);
		
		Retirementlivingpg.btnSearch().click(true);
		///include search text
		
		Findvillagepg.edtSearch().Set(getData("VILLAGENAME"),Keys.TAB, false);
		
		/*List<WebElement> linkList = BrowserFactory.getDriver().findElements(By.tagName("a"));
		for(WebElement link : linkList)
		{
			System.out.println("the Link text is:" +link.getAttribute("href"));
			if(link.getAttribute("href").contains("north-lakes-retirement-resort"))
			{
				System.out.println("link present...");
			}
			else
			{
				System.out.println("link is not present...");
				BrowserFactory.getDriver().findElement(By.cssSelector("a.page-link.next")).click();
				
				//*[@id="page-nav"]/ul/li[10]/a
			}
		}
		*/
		Findvillagepg.btnSearch().click(true);
		Thread.sleep(3500);
	
		
		Findvillagepg.lnkNorthLakes().click(true);
		Thread.sleep(3500);
		Enquirepg.btnEnquire().click(true);
		Enquirepg.lnkBookVillage().click(true);
		
		Enquirepg.edtFirstName().Set(getData("FIRSTNAME"),false);
		Enquirepg.edtLastName().Set(getData("LASTNAME"), false);
		Enquirepg.edtEmail().Set(getData("EMAIL"), false);
		Enquirepg.edtPhoneNumber().Set(getData("PHONENUMBER"), false);
		Enquirepg.edtAddressLine1().Set(getData("ADDRESSLINE1"), false);
		Enquirepg.edtAddressLine2().Set(getData("ADDRESSLINE2"),Keys.TAB, false);
		
		Enquirepg.edtSuburb().highLightElement();
		Enquirepg.edtSuburb().clearTextBox();
		Enquirepg.edtSuburb().Set(getData("SUBURB"),Keys.TAB, true);
		Enquirepg.edtPostcode().Set(getData("POSTCODE"), true);
		
		//Enquirepg.btnState().click(true);
		Enquirepg.btnEnquiringFor().click(false);
		Enquirepg.chkAgree().click(true);

		
		Enquirepg.btnSubmit().click(false);
		
		String text = Enquirepg.verifySubmitText().getText();
		hex.framework.logger.Log.logStep(Logstatus.INFO, "Verfiying text after Submit","The text after submit is:" +text);
		//Enquirepg.btnClose().click(true);
		
	
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		
		hex.framework.logger.Log.logStep(Logstatus.PASS, "Clicking close button","Close Button has been clicked successfully");
		
	}

	

}

