/*Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 */
package hex.framework.automation;

import hex.framework.browser.BrowserFactory;
import hex.framework.enums.Element;
import hex.framework.logger.Log;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

/**
 * <b>AWebElement is an abstract base class for All the below classes:-<b>
 * 
 * <ul>
 * <li>CheckBox
 * <li>ChildObjects
 * <li>Clickable
 * <li>Dropdown
 * <li>Editable
 * <li>WebObject
 * <li>WebTable
 * </ul>
 * 
 * @author Arun Shanmugam
 * @version %I%, %G%
 * @since 1.0
 */
public abstract class AWebElement extends ASynchronize {

	protected WebElement element;
	protected List<WebElement> elementsList;
	protected By byLocator;
	protected String sPassDesc;
	protected String sStepName;
	protected boolean boolStatus = false;
	protected String sFailDesc;
	protected String sDetails;
	protected boolean boolLogWithScreenShot = false;
	protected Element enumElement = Element.Optional;

	/*
	 * Returns EnumElement
	 */
	protected synchronized Element getEnumElement() {
		return enumElement;
	}

	protected synchronized void setEnumElement(Element enumElement) {
		this.enumElement = enumElement;
	}

	protected synchronized boolean getBoolLogWithScreenShot() {
		return boolLogWithScreenShot;
	}

	protected synchronized void setBoolLogWithScreenShot(boolean boolLogWithScreenShot) {
		this.boolLogWithScreenShot = boolLogWithScreenShot;
	}

	protected synchronized boolean getBoolStatus() {
		return boolStatus;
	}

	protected synchronized void setBoolStatus(boolean boolStatus) {
		this.boolStatus = boolStatus;
	}

	protected synchronized String getsDetails() {
		return sDetails;
	}

	protected synchronized void setsDetails(String sDetails) {
		this.sDetails = sDetails;
	}

	// Constructor
	protected AWebElement(WebElement ele) {
		this.ajaxSync(30);
		this.setElement(ele);
		this.init();

	}

	private void init() {
		this.setBoolLogWithScreenShot(false);
		this.setBoolStatus(false);
		this.setsDetails("TEST**");
		this.setsPassDesc("TEST**");
		this.setsStepName("TEST**");
		this.setsFailDesc(
				"Unable to perform the operation, Please refer the Screenshot / or Screen cast for more details");
	}

	// Constructor
	protected AWebElement(By by, int waitFor) {
		this.ajaxSync(30);
		this.setByLocator(by);

		this.setElement(this.fluentWait(this.getByLocator(), waitFor));
		this.init();
	}

	// Constructor
	protected AWebElement(By by, int waitFor, Element... E) {
		boolean boolCollection = false;
		this.ajaxSync(20);
		this.setByLocator(by);

		for (Element item : E) {

			if (item.equals(Element.GetFirstVisible)) {
				this.setElementsList(this.fluentWaits(this.getByLocator()));
				this.setElement(this.getFirstvisibleElementFromList());
				boolCollection = true;
			}
			if (item.equals(Element.ReturnAsCollection)) {
				this.setElementsList(this.fluentWaits(this.getByLocator()));
				// this.setElement(this.getFirstvisibleElementFromList());
				boolCollection = true;
			}
			if (item.equals(Element.Mandatory) || item.equals(Element.Optional)) {
				this.setEnumElement(item);
			}
		}

		if (!boolCollection) {
			this.setElement(this.fluentWait(this.getByLocator(), waitFor));
		}

		this.init();
	}

	// Constructor
	protected AWebElement(By by, Element... E) {
		boolean boolCollection = false;
		this.ajaxSync(20);
		this.setByLocator(by);

		for (Element item : E) {

			if (item.equals(Element.GetFirstVisible)) {
				this.setElementsList(this.fluentWaits(this.getByLocator()));
				this.setElement(this.getFirstvisibleElementFromList());
				boolCollection = true;
			}
			if (item.equals(Element.ReturnAsCollection)) {
				this.setElementsList(this.fluentWaits(this.getByLocator()));
				// this.setElement(this.getFirstvisibleElementFromList());
				boolCollection = true;
			}
			if (item.equals(Element.Mandatory) || item.equals(Element.Optional)) {
				this.setEnumElement(item);
			}
		}

		if (!boolCollection) {
			this.setElement(this.fluentWait(this.getByLocator()));
		}

		this.init();
	}

	protected synchronized String getsFailDesc() {
		return sFailDesc;
	}

	public synchronized void setsFailDesc(String sFailDesc) {
		this.sFailDesc = sFailDesc;
	}

	protected synchronized String getsPassDesc() {
		return sPassDesc;
	}

	public synchronized AWebElement setsPassDesc(String sPassDesc) {
		this.sPassDesc = sPassDesc;
		return this;
	}

	protected synchronized String getsStepName() {
		return sStepName;
	}

	public synchronized AWebElement setsStepName(String sStepName) {
		this.sStepName = sStepName;
		return this;
	}

	public synchronized By getByLocator() {
		return byLocator;
	}

	protected synchronized void setByLocator(By byLocator) {
		this.byLocator = byLocator;
	}

	protected synchronized void setElement(WebElement element) {
		this.element = element;
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	/**
	 * @Description Verify the presence of element and log report
	 * 
	 * @param boolLog
	 * @throws Exception
	 */
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public boolean verifyObjectPresent(boolean boolLog) throws Exception {
		this.setEnumElement(Element.Optional);
		this.setBoolLogWithScreenShot(boolLog);
		if (this.element == null) {
			this.setBoolStatus(false);
		} else {
			this.setBoolStatus(this.isDisplayed());
		}

		this.setsFailDesc("Not found or mismatch");
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());
		this.internalLogger();
		this.errorHandler();
		return this.getBoolStatus();
	}

	public boolean verifyObjectNOTPresent(boolean boolLog) throws Exception {
		this.setEnumElement(Element.Optional);
		this.setBoolLogWithScreenShot(boolLog);

		if (this.element == null) {
			this.setBoolStatus(true);
		} else {
			this.setBoolStatus((this.isDisplayed()) ? false : true);
		}

		this.setsFailDesc("Object Found");
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());
		this.internalLogger();
		this.errorHandler();
		return this.getBoolStatus();
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	/**
	 * @Description Verify the presence of element is enabled
	 * 
	 * @param boolLog
	 * @throws Exception
	 */
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public boolean verifyObjectEnabled(boolean boolLog) throws Exception {
		this.setEnumElement(Element.Optional);
		this.setBoolLogWithScreenShot(boolLog);

		if (this.element == null) {
			this.setBoolStatus(true);
		} else {
			this.setBoolStatus(this.getElement().isEnabled());
		}

		this.setsStepName("Verify that element should be enabled");
		this.setsFailDesc("Fail: Element is in disabled status");
		this.setsPassDesc("Pass: Element is in enabled status");
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());
		this.internalLogger();
		this.errorHandler();
		return this.getBoolStatus();
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	/**
	 * @Description Verify the presence of element is disabled
	 * 
	 * @param boolLog
	 * @throws Exception
	 */
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public boolean verifyObjectDisabled(boolean boolLog) throws Exception {
		this.setEnumElement(Element.Optional);
		this.setBoolLogWithScreenShot(boolLog);

		if (this.element == null) {
			this.setBoolStatus(true);
		} else {
			this.setBoolStatus((this.getElement().isEnabled()) ? false : true);

		}

		this.setsStepName("Verify that element should be disabled");
		this.setsFailDesc("Fail: Element is in enabled status");
		this.setsPassDesc("Pass: Element is in disabled status");
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());
		this.internalLogger();
		this.errorHandler();
		return this.getBoolStatus();
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	/**
	 * @Description Verify the presence of text in element and log report
	 * 
	 * @param boolLog
	 *            , sText
	 * @throws Exception
	 */
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public boolean verifyObjectText(boolean boolLog, String sText) throws Exception {
		this.setEnumElement(Element.Optional);
		boolean bool = false;

		try {
			this.setBoolLogWithScreenShot(boolLog);

			if (this.getElement().getText().contains(sText)) {
				bool = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setBoolStatus(bool);
		this.setsFailDesc("Not found or mismatch");
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());
		Log.implicitLog(this.getBoolStatus(), sText + ">>" + this.getsStepName(), sText + ">>" + this.getsDetails(),
				this.getBoolLogWithScreenShot());
		this.errorHandler();
		return this.getBoolStatus();
	}

	public boolean verifyObjectAttribute(String sAttribute, String sTextToVerify, boolean boolLog) throws Exception {
		this.setEnumElement(Element.Optional);
		boolean bool = false;

		try {
			this.setBoolLogWithScreenShot(boolLog);

			if (this.getElement().getAttribute(sAttribute).contains(sTextToVerify)) {
				bool = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setBoolStatus(bool);
		this.setsFailDesc("Not found or mismatch");
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());
		Log.implicitLog(this.getBoolStatus(), sTextToVerify + ">>" + this.getsStepName(),
				sTextToVerify + ">>" + this.getsDetails(), this.getBoolLogWithScreenShot());
		this.errorHandler();
		return this.getBoolStatus();
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	/**
	 * @Description Verify the presence of text in element and log report
	 * 
	 * @param boolLog
	 *            , sText
	 * @throws Exception
	 */
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public boolean verifyObjectTextOnlyAlphaNumeric(boolean boolLog, String sText) throws Exception {
		this.setEnumElement(Element.Optional);
		boolean bool = false;

		try {
			this.setBoolLogWithScreenShot(boolLog);

			if (this.getElement().getText().replaceAll("[^A-Za-z0-9]", "")
					.equalsIgnoreCase(sText.replaceAll("[^A-Za-z0-9]", ""))) {
				bool = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setBoolStatus(bool);
		this.setsFailDesc("Not found or mismatch");
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());
		String sText1 = this.getElement().getText();
		Log.implicitLog(this.getBoolStatus(), sText + ">>" + this.getsStepName(), sText1 + ">>" + this.getsDetails(),
				this.getBoolLogWithScreenShot());
		this.errorHandler();
		return this.getBoolStatus();
	}

	public boolean isDisplayed() {
		if (this.element == null) {
			return false;
		}

		if (!this.eleExist()) {
			return false;
		}
		return (this.getElement().isDisplayed()) ? true : false;

	}

	public boolean isDisabled() {
		if (this.element == null) {
			return false;
		}

		if (!this.eleExist()) {
			return false;
		}
		return (!this.getElement().isEnabled()) ? true : false;

	}

	// Fluent waits
	protected List<WebElement> fluentWaits(final By locator) {
		List<WebElement> foo = null;
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(BrowserFactory.getDriver())
					.withTimeout(50, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);

			foo = wait.until(new Function<WebDriver, List<WebElement>>() {
				public List<WebElement> apply(WebDriver driver) {
					return driver.findElements(locator);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return foo;
	};

	// Fluent wait
	protected WebElement fluentWait(final By locator) {

		WebElement foo = null;
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(BrowserFactory.getDriver())
					.withTimeout(50, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);

			foo = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(locator);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return foo;
	}

	// Fluent wait
	protected WebElement fluentWait(final By locator, int WaitFor) {

		WebElement foo = null;
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(BrowserFactory.getDriver())
					.withTimeout(WaitFor, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);

			foo = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(locator);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return foo;
	}

	public boolean eleExist() {

		if (this.element == null) {
			return false;
		}

		boolean boolExist = false;

		try {
			if (BrowserFactory.getDriver().findElements(this.getByLocator()).size() != 0) {
				boolExist = true;
			}
		} catch (Exception e) {

			try {
				if (this.getElement().isDisplayed()) {
					boolExist = true;
				}
			} catch (Exception x) {
				x.printStackTrace();
			}
		}

		return boolExist;
	}

	public Actions focusONElement() {

		Actions builder = new Actions(BrowserFactory.getDriver());
		builder.moveToElement(this.element).build().perform();
		return builder;

	}

	public WebElement getElement() {

		return this.element;

	}

	public String getText() {
		this.ajaxSync(25);
		return (element.isDisplayed()) ? this.element.getText() : "";
	}

	// Highlight Element
	public void highLightElement() {

		JavascriptExecutor js = (JavascriptExecutor) BrowserFactory.getDriver();

		js.executeScript("arguments[0].setAttribute('style','border: solid 2px red')", element);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}

		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white')", element);

	}

	// Check element status
	public boolean isReady() {
		this.ajaxSync(30);
		if (this.element == null) {
			return false;
		}

		if (!this.eleExist()) {
			return false;
		}

		for (int z = 0; z < 10; z++) {
			if (!element.isEnabled()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return (element.isDisplayed() && element.isEnabled() && element != null) ? true : false;
	}

	// NEWADDITION////////%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public void moveToElementClick(boolean boolScreenshot) {
		boolean boolStatus = false;

		this.ajaxSync(20);

		if (this.isReady()) {

			this.ajaxSync(20);

			Actions builder = new Actions(BrowserFactory.getDriver());
			builder.moveToElement(this.element).click().build().perform();
			// builder.moveToElement(this.element).clickAndHold(this.element).release(this.element);
			boolStatus = true;

		}

		this.setBoolLogWithScreenShot(boolScreenshot);
		this.setBoolStatus(boolStatus);
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());
		this.internalLogger();
	}

	public void moveToElementDBLClick(boolean boolScreenshot) {
		boolean boolStatus = false;

		this.ajaxSync(20);

		if (this.isReady()) {

			this.ajaxSync(20);

			Actions builder = new Actions(BrowserFactory.getDriver());
			builder.moveToElement(this.element).doubleClick().build().perform();
			boolStatus = true;

		}

		this.setBoolLogWithScreenShot(boolScreenshot);
		this.setBoolStatus(boolStatus);
		this.setsDetails((this.getBoolStatus()) ? this.getsPassDesc() : this.getsFailDesc());
		this.internalLogger();

		// return boolStatus;
	}

	public boolean removeReadOnlyAttribute() {
		boolean boolStatus = false;

		this.ajaxSync(20);

		if (this.isDisplayed()) {

			this.ajaxSync(20);
			JavascriptExecutor executor = (JavascriptExecutor) BrowserFactory.getDriver();
			executor.executeScript("arguments[0].removeAttribute('readonly');", this.element);
			boolStatus = true;

		}

		return boolStatus;
	}

	public boolean removeDisabledAttribute() {
		boolean boolStatus = false;

		this.ajaxSync(20);

		if (this.isDisplayed()) {

			this.ajaxSync(20);
			JavascriptExecutor executor = (JavascriptExecutor) BrowserFactory.getDriver();
			executor.executeScript("arguments[0].removeAttribute('disabled');", this.element);
			boolStatus = true;

		}

		return boolStatus;
	}

	public synchronized List<WebElement> getElementsList() {
		return elementsList;
	}

	protected synchronized void setElementsList(List<WebElement> elementsList) {
		this.elementsList = elementsList;
	}

	protected WebElement getFirstvisibleElementFromList() {
		WebElement ele = null;
		List<WebElement> eles = this.getElementsList();

		for (WebElement x : eles) {
			this.setElement(x);

			if (this.isDisplayed()) {

				ele = x;

				break;

			}

		}

		return ele;
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	/**
	 * @Description Common method to call log report
	 * 
	 * @param boolLog
	 *            , sText
	 */
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	protected void internalLogger() {
		Log.implicitLog(this.getBoolStatus(), this.getsStepName(), this.getsDetails(), this.getBoolLogWithScreenShot());
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	/**
	 * @Description Throw an exception inorder to exit the test in case of
	 *              mandatory element
	 * 
	 * @param boolLog
	 *            , sText
	 */
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	protected void errorHandler() throws Exception {
		if (this.getEnumElement().equals(Element.Mandatory)) {
			if (!this.getBoolStatus()) {
				throw new Exception("Hex Framework mandatory Element Exception");
			}
		}
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	/**
	 * @Description Change element text by using java script exe
	 * 
	 * @param boolLog
	 *            sText
	 */
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public boolean ForceChangeElementText(String value, boolean bLogWithScreenShot) throws Exception {
		this.setBoolLogWithScreenShot(bLogWithScreenShot);
		try {
			JavascriptExecutor executor = (JavascriptExecutor) BrowserFactory.getDriver();
			executor.executeScript("arguments[0].value = '" + value + "';", this.getElement());

			this.getElement().sendKeys(Keys.SPACE);
			this.setBoolStatus(true);
		} catch (Exception e) {
			this.setBoolStatus(false);
			e.printStackTrace();
		}

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

}
