package hex.dl.scripts.objects.homepage;
import org.openqa.selenium.By;

import hex.framework.automation.Clickable;
import hex.framework.automation.Dropdown;
import hex.framework.automation.Editable;
import hex.framework.automation.SpecialDropdown;
import hex.framework.enums.Element;

public class Homepage {

	public static Editable edtFlyFrom() {
		Editable Obj = new Editable(By.xpath("//input[@id='jbBookerDepart']"), Element.Optional);
		Obj.setsStepName("Entering value in Fly From field");
		Obj.setsPassDesc("Fly From has been entered successfully");
		return Obj;
	}
	
	
	public static Editable edtFlyTo() {
		Editable Obj = new Editable(By.xpath("//input[@id='jbBookerArrive']"), Element.Mandatory);
		Obj.setsStepName("Entering value in Fly To field");
		Obj.setsPassDesc("Fly To has been entered successfully");
		return Obj;
	}
	
	public static Editable edtDepDate() {
		Editable Obj = new Editable(By.xpath("//input[@id='jbBookerCalendarDepart']"), Element.Mandatory);
		Obj.setsStepName("Entering value in Departure Date field");
		Obj.setsPassDesc("Departure Date has been entered successfully");
		return Obj;
	}
	
	public static Editable edtArrDate() {
		Editable Obj = new Editable(By.xpath("//input[@id='jbBookerCalendarReturn']"), Element.Mandatory);
		Obj.setsStepName("Entering value in Arrival Date field");
		Obj.setsPassDesc("Arrival Date has been entered successfully");
		return Obj;
	}
	
	public static SpecialDropdown drpNoOfSeat() {
		SpecialDropdown Obj = new SpecialDropdown(By.xpath("//div[@name='numAdults']"),By.xpath("//div[@name='numAdults']//li"), Element.Mandatory);
		Obj.setsStepName("Selecting value in No of seats field");
		Obj.setsPassDesc("No of seats has been selected successfully");
		return Obj;
	}
	
	public static Clickable btnFind() {
		Clickable Obj = new Clickable(By.xpath("//input[@value='Find it']"), Element.Mandatory);
		Obj.setsStepName("Clicking on the Find field");
		Obj.setsPassDesc("Find Button has been clicked successfully");
		return Obj;
	}
}
