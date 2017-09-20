package hex.dl.scripts.objects.stocklandHomepage;

import org.openqa.selenium.By;

import hex.framework.automation.Clickable;
import hex.framework.automation.Editable;
import hex.framework.enums.Element;

public class Findvillagepg {

	
	
	public static Editable edtSearch() {
		Editable Obj = new Editable(By.id("autocompleteSearch"), Element.Mandatory);
		Obj.setsStepName("Entering Search value");
		Obj.setsPassDesc("Search Value has been entered successfully");
		return Obj;
	}
	

	
	public static Clickable btnSearch() 
	{
		Clickable Obj = new Clickable(By.id("SearchRefine"), Element.Mandatory);
		Obj.setsStepName("Clicking on the Search Refine Button");
		Obj.setsPassDesc("Search Refine Button has been clicked successfully");
		return Obj;
	}

	public static Clickable lnkNorthLakes() 
	{
		Clickable Obj = new Clickable(By.xpath("//a[@href='/retirement-living/qld/north-lakes-retirement-resort']"), Element.Mandatory);
		Obj.setsStepName("Clicking on the North Lakes Retirement Resort");
		Obj.setsPassDesc("North Lakes Retirement Resort has been clicked successfully");
		return Obj;
	}

}
