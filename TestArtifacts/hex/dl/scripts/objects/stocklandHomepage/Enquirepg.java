package hex.dl.scripts.objects.stocklandHomepage;

import org.openqa.selenium.By;

import hex.framework.automation.Clickable;
import hex.framework.automation.Editable;
import hex.framework.enums.Element;

public class Enquirepg {

	public static Clickable btnEnquire() {
		Clickable Obj = new Clickable(By.linkText("Enquire"), Element.Mandatory);
		Obj.setsStepName("Clicking on the Enquire Button");
		Obj.setsPassDesc("Enquire Button has been clicked successfully");
		return Obj;

	}

	public static Clickable lnkBookVillage() {
		Clickable Obj = new Clickable(By.xpath("//div[@class='enquiry-content']/h4"), Element.Mandatory);
		Obj.setsStepName("Clicking on the Book Village Visit");
		Obj.setsPassDesc("Book Village Visit0 has been clicked successfully");
		return Obj;
	}

	public static Editable edtFirstName() {
		Editable Obj = new Editable(By.id("FirstName"), Element.Mandatory);
		Obj.setsStepName("Entering First Name");
		Obj.setsPassDesc("First Name has been entered successfully");
		return Obj;
	}

	public static Editable edtLastName() {
		Editable Obj = new Editable(By.id("LastName"), Element.Mandatory);
		Obj.setsStepName("Entering Last Name");
		Obj.setsPassDesc("Last Name has been entered successfully");
		return Obj;
	}

	public static Editable edtEmail() {
		Editable Obj = new Editable(By.id("Email"), Element.Mandatory);
		Obj.setsStepName("Entering Email");
		Obj.setsPassDesc("Email has been entered successfully");
		return Obj;
	}

	public static Editable edtPhoneNumber() {
		Editable Obj = new Editable(By.id("PhoneNumber"), Element.Mandatory);
		Obj.setsStepName("Entering Phone Number");
		Obj.setsPassDesc("Phone Number has been entered successfully");
		return Obj;
	}

	public static Editable edtAddressLine1() {
		Editable Obj = new Editable(By.id("AddressLine1"), Element.Mandatory);
		Obj.setsStepName("Entering Address Line1");
		Obj.setsPassDesc("Address Line1 has been entered successfully");
		return Obj;
	}
	
	public static Editable edtAddressLine2() {
		Editable Obj = new Editable(By.id("AddressLine2"), Element.Mandatory);
		Obj.setsStepName("Entering Address Line2");
		Obj.setsPassDesc("Address Line2 has been entered successfully");
		return Obj;
	}
	
	//*[@id="Suburb"]
	public static Editable edtSuburb() {
		Editable Obj = new Editable(By.xpath("//label[contains(text(),'Suburb')]/following-sibling::input"), Element.Mandatory);
		Obj.setsStepName("Entering Suburb");
		Obj.setsPassDesc("Suburb has been entered successfully");
		return Obj;
	}

	public static Editable edtPostcode() {
		Editable Obj = new Editable(By.id("Postcode"), Element.Mandatory);
		Obj.setsStepName("Entering Postcode");
		Obj.setsPassDesc("Postcode has been entered successfully");
		return Obj;
	}

	// input[@value='NSW']
	public static Clickable btnState() {
		Clickable Obj = new Clickable(By.xpath("//input[@value='NSW']"), Element.Mandatory);
		Obj.setsStepName("Clicking on the State Button");
		Obj.setsPassDesc("State Button has been clicked successfully");
		return Obj;
	}

	public static Clickable btnEnquiringFor() {
		Clickable Obj = new Clickable(By.xpath("//input[@value='Myself']"), Element.Mandatory);
		Obj.setsStepName("Clicking on the EnquiringFor Button");
		Obj.setsPassDesc("EnquiringFor Button has been clicked successfully");
		return Obj;
	}

	public static Clickable verifySubmitText()
	{
		Clickable Obj = new Clickable(By.xpath("//div[@id='mainAckForm']/form/div/p"), Element.Mandatory);
		Obj.setsStepName("Clicking on the EnquiringFor Button");
		Obj.setsPassDesc("EnquiringFor Button has been clicked successfully");
		return Obj;
	}

	public static Clickable chkAgree() {
		Clickable Obj = new Clickable(By.xpath("//input[@data-checkbox-class-name='dynamicCheckbox']"), Element.Mandatory);
		Obj.setsStepName("Clicking on the I Agree Checkbox");
		Obj.setsPassDesc("I Agree Checkbox has been clicked successfully");
		return Obj;
	}

	public static Clickable btnSubmit() {
		Clickable Obj = new Clickable(By.id("makeEnquiryBtn"), Element.Mandatory);
		Obj.setsStepName("Clicking on the Submit Button");
		Obj.setsPassDesc("Submit Button has been clicked successfully");
		return Obj;
	}

	
                 
    
	public static Clickable btnClose() {
		Clickable Obj = new Clickable(By.xpath("//h5[contains(text(),'Enquiry About')]/button[@class='icon-close close']"), Element.Mandatory);
		Obj.setsStepName("Clicking on the Close Button");
		Obj.setsPassDesc("Close Button has been clicked successfully");
		return Obj;
	}

}
