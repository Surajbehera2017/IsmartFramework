/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.automation;

import java.util.List;

import hex.framework.enums.Element;
import hex.framework.logger.Log;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SpecialDropdown extends AWebElement implements IWebElement {
	private By OptionLocator;

	private By getOptionLocator() {
		return OptionLocator;
	}

	private void setOptionLocator(By optionLocator) {
		OptionLocator = optionLocator;
	}

	public SpecialDropdown(WebElement ele, By optionsLocator) {
		super(ele);
		this.setOptionLocator(optionsLocator);
		// this.element = ele;
		// TODO Auto-generated constructor stub
	}

	public SpecialDropdown(By by, By optionsLocator) {
		// TODO Auto-generated constructor stub

		super(by);
		// element = this.fluentWait(by);
		this.setOptionLocator(optionsLocator);

	}

	public SpecialDropdown(By by, By optionsLocator, Element... e) {
		// TODO Auto-generated constructor stub

		super(by, e);
		// element = this.fluentWait(by);
		this.setOptionLocator(optionsLocator);

	}

	public SpecialDropdown(By by, By optionsLocator, int timeout, Element... e) {
		// TODO Auto-generated constructor stub

		super(by, timeout, e);
		// element = this.fluentWait(by);
		this.setOptionLocator(optionsLocator);

	}

	public SpecialDropdown(By by, By optionsLocator, int timeout) {
		// TODO Auto-generated constructor stub

		super(by, timeout);
		// element = this.fluentWait(by);
		this.setOptionLocator(optionsLocator);

	}

	public boolean selectByVisibleText(String value, boolean bLogWithScreenShot) throws Exception {

		this.setBoolLogWithScreenShot(bLogWithScreenShot);
		this.setBoolStatus(selectByVisibleText(value));
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());
		if (!this.getBoolStatus()) {
			Log.implicitLog(this.getBoolStatus(), this.getsStepName(), this.getsDetails(), bLogWithScreenShot);
		} else {
			Log.implicitLog(this.getBoolStatus(), this.getsStepName(), this.getsDetails(), bLogWithScreenShot);
		}
		this.errorHandler();

		return this.getBoolStatus();
	}
	public boolean verifySelectedItem(String value, String reportdesc, boolean bLogWithScreenShot) throws Exception {

		this.setBoolLogWithScreenShot(bLogWithScreenShot);

		this.setBoolStatus(this.getSelectedItem().trim().equalsIgnoreCase(value));

		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());
		if (!this.getBoolStatus()) {
			Log.implicitLog(this.getBoolStatus(), reportdesc, "Selected Item mismatch with the expected value",
					bLogWithScreenShot);
		} else {
			Log.implicitLog(this.getBoolStatus(), reportdesc,
					"Selected Item has been verified" + "==>" + this.getSelectedItem(), bLogWithScreenShot);
		}
		this.errorHandler();

		return this.getBoolStatus();
	}
	private String getSelectedItem() {
		String s1 = "";
		if (this.element != null) {
			s1 =this.getElement().getText().trim();
		}

		return s1;
	}

	public boolean selectByVisibleText(String value) {
		boolean boolStatus = false;
		if (this.isReady()) {
			// this.highLightElement();
			this.ajaxSync(20);

			try {
				this.moveToElementClick(false);
				Thread.sleep(1000);
				this.getallOptions().forEach(x -> {


					if (x.getText().trim().equals(value.trim())) {

						x.click();
						
						
					}

				});
				boolStatus = true;
			} catch (Exception e) {

				boolStatus = false;
				e.printStackTrace();
				// throw e;

			}

		}
		return boolStatus;

	}

	public List<WebElement> getallOptions() {
		List<WebElement> oSpecialOptions = null;
		if (this.element.isDisplayed()) {
			try {
				oSpecialOptions = new ChildObjects(this.getOptionLocator()).getElements();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}

		return oSpecialOptions;
	}
}
