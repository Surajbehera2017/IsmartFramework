package hex.dl.scripts.objects.StocklandHome;

import org.openqa.selenium.By;

import hex.framework.automation.Clickable;
import hex.framework.automation.Editable;
import hex.framework.enums.Element;

public class StocklandHome {

	public static Clickable lnkCorporate() {
		Clickable Obj = new Clickable(By.linkText("Corporate"), Element.Optional);
		Obj.setsStepName("Clcicking on the Corporate link");
		Obj.setsPassDesc("Corporate link clicked successfully");
		return Obj;
	}
	
}
