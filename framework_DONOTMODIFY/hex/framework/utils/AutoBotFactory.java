/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.utils;

import hex.framework.browser.BrowserFactory;
import hex.framework.enums.Config;

public class AutoBotFactory extends Thread {

	// String to hold user name
	private String sUsername;

	// String to hold Password
	private String sPassword;

	public void AuthenticationPopup() {
		if (BrowserFactory.getsDriverName().equalsIgnoreCase(Config.BROWSER_IE.toString()))
			AutoBotCore.handleIECredentialpopupOnlyPassword(sPassword);
		if (BrowserFactory.getsDriverName().equalsIgnoreCase(Config.BROWSER_CHROME.toString()))
			AutoBotCore.handleGCCredentialpopupOnlyPassword(sUsername, sPassword);
	}

	public void run() {
		this.AuthenticationPopup();
	}

	public AutoBotFactory(String sUsername, String sPassword) {
		this.sUsername = sUsername;
		this.sPassword = sPassword;
	}

}
