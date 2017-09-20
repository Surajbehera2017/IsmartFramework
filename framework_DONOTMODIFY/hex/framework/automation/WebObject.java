/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.automation;

import hex.framework.enums.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebObject extends AWebElement implements IWebElement {

	// private WebElement element;
	public WebObject(WebElement ele) {
		super(ele);
		// TODO Auto-generated constructor stub
	}

	public WebObject(By by) {
		super(by);
		// TODO Auto-generated constructor stub
	}

	public WebObject(By by, int waitfor) {
		super(by);
		// TODO Auto-generated constructor stub
	}

	public WebObject(By by, Element... E) {
		super(by, E);
		// TODO Auto-generated constructor stub
	}

	public WebObject(By by, int waitfor, Element... E) {
		super(by, waitfor, E);
		// TODO Auto-generated constructor stub
	}
}
