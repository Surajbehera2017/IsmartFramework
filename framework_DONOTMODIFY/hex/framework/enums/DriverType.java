/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.enums;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public enum DriverType {
	Firefox(FirefoxDriver.class), InternetExplorer(InternetExplorerDriver.class), Chrome(ChromeDriver.class), Other(
			null);

	Class<? extends WebDriver> driverClass;

	DriverType(Class<? extends WebDriver> driverClass) {
		this.driverClass = driverClass;
	}

	public Class<? extends WebDriver> getDriver() {
		return this.driverClass;
	}

}
