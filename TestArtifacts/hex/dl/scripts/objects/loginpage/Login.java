package hex.dl.scripts.objects.loginpage;

import org.openqa.selenium.By;

import hex.framework.automation.Editable;
import hex.framework.enums.Element;

public class Login {

	public static Editable edtUserName() {
		Editable Obj = new Editable(By.xpath("//input[@name='userName']"), Element.Mandatory);
		Obj.setsStepName("Entering value in user name field");
		Obj.setsPassDesc(" User name has been entered successfully");
		return Obj;
	}

}
