/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.browser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import hex.framework.RunTime.RunTimeManager;
import hex.framework.RunTime.TestCase;
import hex.framework.data.ExcelFactory;
import hex.framework.enums.Config;
import hex.framework.enums.DriverType;
import hex.framework.utils.GenericUtils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserCore {

	private static WebDriver FFWDINSTANCE;
	private static WebDriver IEWDINSTANCE;
	private static WebDriver GCDINSTANCE;

	private BrowserCore() {
	}

	/**
	 * @Author Arun Shanmugam
	 * @Description: Public method to return firefox driver
	 * @Return returns firefox instance if already exists else create new
	 *         firefox instance
	 * @Revision: N/A
	 * @DateofCreation: 29/07/2015
	 **/

	public static synchronized void setNull() {

		try {

			if (FFWDINSTANCE != null) {
				FFWDINSTANCE.quit();
				FFWDINSTANCE = null;
			}

			if (IEWDINSTANCE != null) {
				IEWDINSTANCE.quit();
				IEWDINSTANCE = null;
			}
			if (GCDINSTANCE != null) {
				GCDINSTANCE.quit();
				GCDINSTANCE = null;
			}
			cleanDriversProcess();
			KillIE();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// Singleton for firefox driver
	public static synchronized WebDriver getFFDriver() {

		if ((FFWDINSTANCE != null) && (FFWDINSTANCE instanceof WebDriver)) {
			return FFWDINSTANCE;
		} else {

			cleanDriversProcess();
			// /////////////////////////

			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			cap.setPlatform(Platform.VISTA);
			// //////////////////////////

			FFWDINSTANCE = null;
			FirefoxProfile desiredCapabilities = new FirefoxProfile();

			// myprofile.setPreference(key, value);

			desiredCapabilities.setPreference("network.automatic-ntlm-auth.trusted-uris",
					TestCase.getApplicationDomain());

			System.out.print("------NETWORK NTLM");
			try {
				FFWDINSTANCE = DriverType.Firefox.getDriver().getDeclaredConstructor(FirefoxProfile.class)
						.newInstance(desiredCapabilities);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				RunTimeManager.emergencyAction(e.getMessage());
				e.printStackTrace();
			}
			FFWDINSTANCE.manage().window().maximize();

			return FFWDINSTANCE;

		}
	}

	// Single ton for IE driver
	public static synchronized WebDriver getIEDriver() {

		if ((IEWDINSTANCE != null) && (IEWDINSTANCE instanceof WebDriver)) {
			return IEWDINSTANCE;
		} else {

			cleanDriversProcess();
			IEWDINSTANCE = null;

			// ////////////////////////////////////////////////////////

			final String[] ZONES = {
					"\"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\1\"",
					"\"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\"",
					"\"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\3\"",
					"\"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\4\"" };
			for (String zone : ZONES) {
				ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "reg", "add", zone, "/v", "2500", "/t", "REG_DWORD",
						"/d", "0", "/f");
				try {
					pb.start();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}

			// ///////////////////////////////////////////////////////////

			// enableIEProtectModeOfAllZones();
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability("unexpectedAlertBehaviour", "ignore");
			// ieCapabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,
			// false);
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			String IEDriver = GenericUtils.getProjectpath() + "\\Lib\\32\\IEDriverServer.exe";
			// Config.DRIVERSPATH + Config.IE64PATH + Config.IE64DRIVER;
			// String IEDriver =
			// "C:\\RRTestAutomation\\RRAutomationSuite\\Lib\\32\\IEDriverServer.exe";
			System.out.println(IEDriver);
			File file = new File(IEDriver);
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

			try {
				IEWDINSTANCE = new InternetExplorerDriver(ieCapabilities);// DriverType.InternetExplorer.getDriver().getDeclaredConstructor(DesiredCapabilities.class).newInstance(ieCapabilities);
			} catch (IllegalArgumentException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			IEWDINSTANCE.manage().window().maximize();

			return IEWDINSTANCE;
		}
	}

	// Single ton for GC driver
	public static synchronized WebDriver getGCDriver() {

		if ((GCDINSTANCE != null) && (GCDINSTANCE instanceof WebDriver)) {
			return GCDINSTANCE;
		} else {

			cleanDriversProcess();
			GCDINSTANCE = null;

			try {

				String GCDriver = GenericUtils.getProjectpath() + Config.DRIVERSPATH + Config.CHROMEDRIVER;
				File file = new File(GCDriver);
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				ChromeOptions options = new ChromeOptions();

				options.addArguments("--start-maximized");
				options.addArguments("--disable-extensions");

				// options.addArguments("user-data-dir=C:/Users/shanmar/AppData/Local/Google/Chrome/User
				// Data");

				// GCDINSTANCE =
				// DriverType.Chrome.getDriver().getDeclaredConstructor(String.class,ChromeOptions.class).newInstance(GCDriver,options);

				GCDINSTANCE = new ChromeDriver(options);
				GCDINSTANCE.manage().window().maximize();
			} catch (IllegalArgumentException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return GCDINSTANCE;
		}
	}

	private static void cleanDriversProcess() {
		try {
			if (GenericUtils.isProcessRunning("IEDriverServer.exe")) {
				GenericUtils.killProcess("IEDriverServer.exe");
			}
			if (GenericUtils.isProcessRunning("chromedriver.exe")) {
				GenericUtils.killProcess("chromedriver.exe");
			}

		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void KillIE() {
		try {
			if (GenericUtils.isProcessRunning("iexplore.exe")) {
				GenericUtils.killProcess("iexplore.exe");
			}
			if (GenericUtils.isProcessRunning("iexplore.exe")) {
				GenericUtils.killProcess("iexplore.exe");
			}
			if (GenericUtils.isProcessRunning("Chrome.exe")) {
				GenericUtils.killProcess("Chrome.exe");
			}

		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
