/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.automation;

import java.util.List;

import hex.framework.browser.BrowserFactory;
import hex.framework.enums.Element;
import hex.framework.logger.Log;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CheckBox extends AWebElement implements IWebElement {

	public CheckBox(WebElement ele) {
		super(ele);
		// this.element = ele;

		// TODO Auto-generated constructor stub
	}

	public CheckBox(By by) {
		// TODO Auto-generated constructor stub

		super(by);
		// this.element = this.fluentWait(by);
	}

	// //////////////////////////////////////////////////////NewAddition////////////////////////////////////////////////////////////

	public CheckBox(By by, Element... E) {
		// TODO Auto-generated constructor stub

		super(by, E);
		// this.elementsList = this.fluentWaits(by);
	}

	public boolean check(boolean bLogWithScreenShot) throws Exception {
		this.setBoolLogWithScreenShot(bLogWithScreenShot);
		this.setBoolStatus(check());
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());

		this.internalLogger();
		this.errorHandler();

		return this.getBoolStatus();
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public boolean check() {
		boolean boolStatus = false;

		this.ajaxSync(20);

		if (this.isReady()) {

			if (!this.isChecked()) {
				JavascriptExecutor executor = (JavascriptExecutor) BrowserFactory.getDriver();
				executor.executeScript("arguments[0].click();", this.element);
				boolStatus = true;
			} else {

			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.ajaxSync(40);

		}
		return boolStatus;
	}

	public boolean unCheck(boolean bLogWithScreenShot) throws Exception {
		this.setBoolLogWithScreenShot(bLogWithScreenShot);
		this.setBoolStatus(unCheck());
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());

		this.internalLogger();
		this.errorHandler();

		return this.getBoolStatus();
	}

	public boolean unCheck() {
		boolean boolStatus = false;

		this.ajaxSync(20);

		if (this.isReady() && this.isChecked()) {

			JavascriptExecutor executor = (JavascriptExecutor) BrowserFactory.getDriver();
			executor.executeScript("arguments[0].click();", this.element);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.ajaxSync(40);

			boolStatus = true;

		}
		return boolStatus;
	}

	public boolean Forcecheck(boolean bLogWithScreenShot) throws Exception {
		this.setBoolLogWithScreenShot(bLogWithScreenShot);
		this.setBoolStatus(Forcecheck());
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());

		this.internalLogger();
		this.errorHandler();

		return this.getBoolStatus();
	}

	public boolean Forcecheck() {
		boolean boolStatus = false;

		this.ajaxSync(20);

		if (!this.isChecked()) {

			JavascriptExecutor executor = (JavascriptExecutor) BrowserFactory.getDriver();
			executor.executeScript("arguments[0].click();", this.element);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.ajaxSync(40);

			boolStatus = true;

		}
		return boolStatus;
	}

	public boolean ForceUncheck(boolean bLogWithScreenShot) throws Exception {
		this.setBoolLogWithScreenShot(bLogWithScreenShot);
		this.setBoolStatus(ForceUncheck());
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());

		this.internalLogger();
		this.errorHandler();

		return this.getBoolStatus();
	}

	public boolean ForceUncheck() {
		boolean boolStatus = false;

		this.ajaxSync(20);

		if (!this.isChecked()) {

			this.Forceclick();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.ajaxSync(40);

			boolStatus = true;

		}
		return boolStatus;
	}

	public boolean isChecked() {

		Boolean isChecked = false;
		this.ajaxSync(20);

		if (this.element != null) {
			try {

				String jsScript = "var inpfields = arguments[0];"

						+ "return inpfields.checked;";
				JavascriptExecutor executor = (JavascriptExecutor) BrowserFactory.getDriver();
				isChecked = (Boolean) executor.executeScript("return arguments[0].checked;", this.element);

			} catch (Exception e) {

			}

		}

		return isChecked;
	}

	private boolean Forceclick() {
		boolean boolStatus = false;

		this.ajaxSync(20);

		if (this.getElement() != null) {

			JavascriptExecutor executor = (JavascriptExecutor) BrowserFactory.getDriver();
			executor.executeScript("arguments[0].click();", this.element);
			boolStatus = true;
		}
		return boolStatus;
	}

}
