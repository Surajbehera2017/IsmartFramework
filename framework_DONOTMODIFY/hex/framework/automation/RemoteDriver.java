/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.automation;

import hex.framework.enums.DriverType;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author shanmar
 *
 */
public class RemoteDriver {

	private static WebDriver FFWDINSTANCE;

	// Singleton for firefox driver
	public static synchronized WebDriver getFFDriver() throws InstantiationException {

		if ((FFWDINSTANCE != null) && (FFWDINSTANCE instanceof WebDriver)) {
			return FFWDINSTANCE;
		} else {

			// /////////////////////////

			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			cap.setPlatform(Platform.VISTA);
			// //////////////////////////

			FFWDINSTANCE = null;

			FirefoxProfile myprofile = new FirefoxProfile();

			// myprofile.setPreference(key, value);

			myprofile.setPreference("network.automatic-ntlm-auth.trusted-uris", "2013.eyplaybook.com");
			System.out.print("------NETWORK NTLM");
			try {
				try {
					FFWDINSTANCE = new RemoteWebDriver(new URL("http://10.212.106.220:5566/wd/hub"), cap);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IllegalArgumentException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FFWDINSTANCE.manage().window().maximize();
			return FFWDINSTANCE;

		}
	}
}
