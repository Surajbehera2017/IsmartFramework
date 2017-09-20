package hex.dl.scripts.objects.stocklandHomepage;

import org.openqa.selenium.By;

import hex.framework.automation.Clickable;
import hex.framework.enums.Element;

public class Retirementlivingpg {

	public static Clickable btnSearch() 
	{
		Clickable Obj = new Clickable(By.id("searchButton"), Element.Mandatory);
		Obj.setsStepName("Clicking on the Search Button");
		Obj.setsPassDesc("Search Button has been clicked successfully");
		return Obj;
	}

}
