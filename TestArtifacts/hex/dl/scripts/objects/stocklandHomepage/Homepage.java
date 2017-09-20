package hex.dl.scripts.objects.stocklandHomepage;

import org.openqa.selenium.By;

import hex.framework.automation.Clickable;
import hex.framework.automation.Editable;
import hex.framework.enums.Element;

public class Homepage {
	public static Clickable lnkCorporate() {
		Clickable Obj = new Clickable(By.linkText(" Corporate"), Element.Mandatory);
		Obj.setsStepName("Clicking on the Corporate Link");
		Obj.setsPassDesc("Corporate Link has been clicked successfully");
		return Obj;
	}

	public static Clickable lnkRetirementLiving() {
		Clickable Obj = new Clickable(By.linkText("Retirement Living"), Element.Mandatory);
		Obj.setsStepName("Clicking on the Retirement Living");
		Obj.setsPassDesc("Retirement Living has been clicked successfully");
		return Obj;
	}

	// div[@class='enquiry-content']/h4[contains[text(),'Book Village Visit']

}
