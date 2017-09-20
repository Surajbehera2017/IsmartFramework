/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/

package hex.framework.automation;

import hex.framework.browser.BrowserFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public abstract class ASynchronize {

	public void defaultAjaxSync() {
		this.ajaxWait(60);
	}

	public static void ajaxSync(int timeoutInSeconds) {
		try {
			ajaxWait(timeoutInSeconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void ajaxWait(int timeoutInSeconds) {

		WebDriver driver = BrowserFactory.getDriver();

		try {
			if (driver instanceof JavascriptExecutor) {
				JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

				for (int i = 0; i < timeoutInSeconds; i++) {

					Object numberOfAjaxConnections = jsDriver.executeScript("return window.openHTTPs");
					// return should be a number
					if (numberOfAjaxConnections instanceof Long) {
						Long n = (Long) numberOfAjaxConnections;
						// System.out.println("Number of active calls: " + n);
						if (n.longValue() == 0L)
							break;
					} else {
						// If its not a number page might have been loaded
						// freshly indicating the monkey
						// path is replased or we havent yet done the patch.
						monkeyPatchXMLHttpRequest();
					}
					Thread.sleep(1000);
				}
			} else {
				System.out.println("Web driver: " + driver + " cannot execute javascript");
			}
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	private static void monkeyPatchXMLHttpRequest() {
		WebDriver driver = BrowserFactory.getDriver();
		try {
			if (driver instanceof JavascriptExecutor) {
				JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
				Object numberOfAjaxConnections = jsDriver.executeScript("return window.openHTTPs");
				if (numberOfAjaxConnections instanceof Long) {
					return;
				}
				String script = "  (function() {" + "var oldOpen = XMLHttpRequest.prototype.open;"
						+ "window.openHTTPs = 0;"
						+ "XMLHttpRequest.prototype.open = function(method, url, async, user, pass) {"
						+ "window.openHTTPs++;" + "this.addEventListener('readystatechange', function() {"
						+ "if(this.readyState == 4) {" + "window.openHTTPs--;" + "}" + "}, false);"
						+ "oldOpen.call(this, method, url, async, user, pass);" + "}" + "})();";
				jsDriver.executeScript(script);
			} else {
				System.out.println("Web driver: " + driver + " cannot execute javascript");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
