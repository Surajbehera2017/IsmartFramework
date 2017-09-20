/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.automation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import hex.framework.browser.BrowserFactory;
import hex.framework.enums.Element;

public class ChildObjects extends AWebElement implements IWebElement {

	protected By byChild = null;

	public synchronized By getByChild() {
		return byChild;
	}

	public synchronized void setByChild(By byChild) {
		this.byChild = byChild;
	}

	public ChildObjects(By by, By child) {

		super(by);
		this.setByChild(child);

	}

	public ChildObjects(By by, Element... e) {

		super(by, e);

	}
	public ChildObjects(By by) {

		super(by);

	}
	public List<WebElement> getElements() {
		List<WebElement> lst = null;
		try {
			this.ajaxSync(50);
			lst = this.fluentWaits(this.byLocator);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

	public List<WebElement> childObjects(By locator) {
		List<WebElement> lst = null;
		try {
			this.ajaxSync(50);
			lst = this.getElement().findElements(locator);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

}
