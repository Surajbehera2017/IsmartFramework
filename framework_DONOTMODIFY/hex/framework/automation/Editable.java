/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.automation;

import hex.framework.browser.BrowserFactory;
import hex.framework.enums.Element;
import hex.framework.logger.Log;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Editable extends AWebElement implements IWebElement {

	// private WebElement element;
	public Editable(WebElement ele) {
		super(ele);
		// this.element = ele;
	}

	public Editable(By by) {
		// TODO Auto-generated constructor stub

		super(by);
		// this.element = this.fluentWait(by);
	}

	public Editable(By by, int i, Element... E) {
		// TODO Auto-generated constructor stub

		super(by, i, E);
		// this.elementsList = this.fluentWaits(by);
	}

	public Editable(By by, Element... e) {
		// TODO Auto-generated constructor stub

		super(by, e);
		// this.element = this.fluentWait(by);
	}

	public boolean Set(String value) {
		boolean boolStatus = false;

		if (this.isReady()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// this.highLightElement();
			this.clearTextBox();

			// this.element.click();
			this.element.sendKeys(value);
			

			boolStatus = true;
		}
		return boolStatus;

	}

	public boolean Set(String value, boolean bLogWithScreenShot) throws Exception {
		this.setBoolLogWithScreenShot(bLogWithScreenShot);
		this.setBoolStatus(Set(value));
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());
		if (!this.getBoolStatus()) {
			Log.implicitLog(this.getBoolStatus(), this.getsStepName(), this.getsDetails(), bLogWithScreenShot);
		} else {
			Log.implicitLog(this.getBoolStatus(), this.getsStepName(), this.getsDetails() + "==>" + value,
					bLogWithScreenShot);
		}
		this.errorHandler();
		return this.getBoolStatus();
	}

	public boolean Set(String value, Keys k, boolean bLogWithScreenShot) throws Exception {
		this.setBoolLogWithScreenShot(bLogWithScreenShot);
		this.setBoolStatus(Set(value, k));
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());
		if (!this.getBoolStatus()) {
			Log.implicitLog(this.getBoolStatus(), this.getsStepName(), this.getsDetails(), bLogWithScreenShot);
		} else {
			Log.implicitLog(this.getBoolStatus(), this.getsStepName(), this.getsDetails() + "==>" + value,
					bLogWithScreenShot);
		}
		this.errorHandler();

		return this.getBoolStatus();
	}

	public String getvalue() {
		String sText = null;
		try {

			JavascriptExecutor executor = (JavascriptExecutor) BrowserFactory.getDriver();
			sText = (String) executor.executeScript("return arguments[0].value;", this.getElement()).toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sText.trim();
	}

	public void clearTextBox() {
		try {
			if (this.isReady()) {
				JavascriptExecutor executor = (JavascriptExecutor) BrowserFactory.getDriver();
				executor.executeScript("arguments[0].value = '';", this.getElement());
			

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean Set(String value, Keys k) {
		boolean blnStatus = Set(value);
		if (blnStatus) {
			// this.moveToElementClick(false);
			element.sendKeys(k);
		
		}

		return blnStatus;
	}
}