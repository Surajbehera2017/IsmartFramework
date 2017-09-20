/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.browser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import hex.framework.RunTime.Runtime;
import hex.framework.automation.ASynchronize;
import hex.framework.enums.Config;
import hex.framework.enums.Logstatus;
import hex.framework.logger.Log;

public class BrowserFactory extends ASynchronize {

	private static String sDriverName = "";

	public static WebDriver getDriver() {

		if (getsDriverName().equalsIgnoreCase(Config.BROWSER_MOZILLA.toString())) {
			return BrowserCore.getFFDriver();
		} else if (getsDriverName().equalsIgnoreCase(Config.BROWSER_CHROME.toString())) {
			return BrowserCore.getGCDriver();
		} else if (getsDriverName().equalsIgnoreCase(Config.BROWSER_IE.toString())) {
			return BrowserCore.getIEDriver();
		}

		return BrowserCore.getFFDriver();
	}

	public static synchronized String getsDriverName() {
		sDriverName = Runtime.getCurrentEnvMap().get("BROWSER").toString();
		return sDriverName;
	}

	// Below method will be called from the driver to set Name
	public static synchronized void setsDriverName(String oDriver) {

		if (sDriverName.equals(""))
			BrowserFactory.sDriverName = oDriver;
	}

	// Destroy WebDriver -FF-GC-IE(Current Instance)
	public static void destroyDriver() {

		BrowserCore.setNull();

	}

	public static void get(String URL) {
		getDriver().get(URL);
	}

	public static void navigateTo(String URL) {

		try {
			destroyDriver();
			getDriver().navigate().to(URL);

			Log.logStep(Logstatus.PASS, "Launching Browser::" + getsDriverName(),
					"Browser has been launched successfully", false);
		} catch (Exception e) {

			System.out.print("EXCEPTION HANDLED");
			e.printStackTrace();

			try {
				destroyDriver();
				getDriver().navigate().to(URL);

			} catch (Exception x) {
				System.out.print("IE EXCEPTION HANDLED");

				// x.printStackTrace();
				destroyDriver();
				getDriver().navigate().to(URL);
			}
		}

		try {
			Thread.sleep(5000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void oWait(int iMilliseconds) {

		try {
			Thread.sleep(iMilliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
