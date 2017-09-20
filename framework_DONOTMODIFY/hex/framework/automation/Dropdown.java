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

public class Dropdown extends AWebElement implements IWebElement {
	// private WebElement element;
	public Dropdown(WebElement ele) {
		super(ele);
		// this.element = ele;
		// TODO Auto-generated constructor stub
	}

	public Dropdown(By by) {
		// TODO Auto-generated constructor stub

		super(by);
		// element = this.fluentWait(by);
	}

	public Dropdown(By by, Element... e) {
		// TODO Auto-generated constructor stub

		super(by, e);
		// element = this.fluentWait(by);
	}

	public boolean selectByValue(String value, boolean bLogWithScreenShot) throws Exception {

		this.setBoolLogWithScreenShot(bLogWithScreenShot);
		this.setBoolStatus(selectByValue(value));
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());
		if (!this.getBoolStatus()) {
			Log.implicitLog(this.getBoolStatus(), this.getsStepName(), this.getsDetails(), bLogWithScreenShot);
		} else {
			Log.implicitLog(this.getBoolStatus(), this.getsStepName(),
					this.getsDetails() + "==>" + this.getSelectedItem(), bLogWithScreenShot);
		}
		this.errorHandler();

		return this.getBoolStatus();
	}

	public boolean selectByVisibleText(String value, boolean bLogWithScreenShot) throws Exception {

		this.setBoolLogWithScreenShot(bLogWithScreenShot);
		this.setBoolStatus(selectByVisibleText(value));
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());
		if (!this.getBoolStatus()) {
			Log.implicitLog(this.getBoolStatus(), this.getsStepName(), this.getsDetails(), bLogWithScreenShot);
		} else {
			Log.implicitLog(this.getBoolStatus(), this.getsStepName(),
					this.getsDetails() + "==>" + this.getSelectedItem(), bLogWithScreenShot);
		}
		this.errorHandler();

		return this.getBoolStatus();
	}

	public String selectByIndex(int index, boolean bLogWithScreenShot) throws Exception {
		this.setBoolLogWithScreenShot(bLogWithScreenShot);
		this.setBoolStatus(selectByIndex(index));
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());
		if (!this.getBoolStatus()) {
			Log.implicitLog(this.getBoolStatus(), this.getsStepName(), this.getsDetails(), bLogWithScreenShot);
		} else {
			Log.implicitLog(this.getBoolStatus(), this.getsStepName(),
					this.getsDetails() + "==>" + this.getSelectedItem(), bLogWithScreenShot);
		}
		this.errorHandler();

		return this.getSelectedItem();
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

	public boolean selectByValue(String value) {
		boolean boolStatus = false;
		if (this.isReady()) {
			// this.highLightElement();
			this.ajaxSync(20);

			try {
				getDropdownObj().selectByValue(value);
				boolStatus = true;
			} catch (Exception e) {
				boolStatus = false;
				e.printStackTrace();
			}

		}
		return boolStatus;

	}

	public boolean selectByVisibleText(String value) {
		boolean boolStatus = false;
		if (this.isReady()) {
			// this.highLightElement();
			this.ajaxSync(20);

			try {
				getDropdownObj().selectByVisibleText(value);

				boolStatus = true;
			} catch (Exception e) {

				boolStatus = false;
				e.printStackTrace();
				// throw e;

			}

		}
		return boolStatus;

	}

	public boolean selectByIndex(int index) {
		boolean boolStatus = false;
		if (this.isReady()) {
			// this.highLightElement();
			// element.click();

			try {
				getDropdownObj().selectByIndex(index);
				boolStatus = true;

			} catch (Exception e) {
				boolStatus = false;

				e.printStackTrace();
			}
		}
		return boolStatus;

	}

	public String getSelectedItem() {

		String s1 = "";
		if (this.element != null) {
			s1 = getDropdownObj().getFirstSelectedOption().getText();
		}

		return s1;
	}

	public Select getDropdownObj() {
		Select sel1 = null;
		if (this.element.isDisplayed()) {
			sel1 = new Select(this.element);
		}

		return sel1;
	}

	public List<WebElement> getallOptions() {
		List<WebElement> sel1 = null;
		if (this.element.isDisplayed()) {
			sel1 = new Select(this.element).getOptions();
		}

		return sel1;
	}
}
