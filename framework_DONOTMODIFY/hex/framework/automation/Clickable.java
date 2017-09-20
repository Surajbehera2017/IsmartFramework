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

public class Clickable extends AWebElement implements IWebElement {

	public boolean click(boolean bLogWithScreenShot) throws Exception {
		this.setBoolLogWithScreenShot(bLogWithScreenShot);
		this.setBoolStatus(click());
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());

		this.internalLogger();
		this.errorHandler();

		return this.getBoolStatus();
	}

	public Clickable(WebElement ele) {
		super(ele);
		// this.element = ele;

		// TODO Auto-generated constructor stub
	}

	public Clickable(By by) {
		// TODO Auto-generated constructor stub

		super(by);
		// this.element = this.fluentWait(by);
	}

	// //////////////////////////////////////////////////////NewAddition////////////////////////////////////////////////////////////

	public Clickable(By by, Element... E) {
		// TODO Auto-generated constructor stub

		super(by, E);
		// this.elementsList = this.fluentWaits(by);
	}

	public Clickable(By by, int i, Element... E) {
		// TODO Auto-generated constructor stub

		super(by, i, E);
		// this.elementsList = this.fluentWaits(by);
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public boolean click() {
		boolean boolStatus = false;

		this.ajaxSync(20);

		if (this.isReady()) {

			JavascriptExecutor executor = (JavascriptExecutor) BrowserFactory.getDriver();
			executor.executeScript("arguments[0].click();", this.element);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.ajaxSync(40);

			// this.element.click();
			// Actions builder = new Actions(BrowserFactory.getDriver());
			// builder.moveToElement( this.element ).click( this.element );
			// builder.perform();

			boolStatus = true;

		}
		return boolStatus;
	}

	public boolean Forceclick(boolean bLogWithScreenShot) throws Exception {
		this.setBoolLogWithScreenShot(bLogWithScreenShot);
		this.setBoolStatus(Forceclick());
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());

		this.internalLogger();
		this.errorHandler();

		return this.getBoolStatus();
	}

	public boolean Forceclick() {
		boolean boolStatus = false;

		this.ajaxSync(20);

		if (this.getElement() != null) {

			JavascriptExecutor executor = (JavascriptExecutor) BrowserFactory.getDriver();
			executor.executeScript("arguments[0].click();", this.element);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.ajaxSync(40);

			boolStatus = true;

		}
		return boolStatus;
	}

}
