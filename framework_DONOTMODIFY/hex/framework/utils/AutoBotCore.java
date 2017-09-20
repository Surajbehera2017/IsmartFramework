/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.utils;

import java.awt.AWTException;
import java.awt.Robot;

import hex.framework.browser.BrowserFactory;

import org.openqa.selenium.Keys;

import autoitx4java.AutoItX;

public class AutoBotCore extends Thread {

	public static void main(String[] args) {

	}

	public static void handleIECredentialpopupOnlyPassword(String sPassword) {
		// if(BrowserFactory.getsDriverName())
		AutoItX x = new AutoItX();
		String title = "Windows Security";

		for (int i = 0; i < 60; i++) {

			x.sleep(1000);

			if (x.winExists(title)) {
				x.winActivate(title, "");
				x.winWaitActive(title, "", 20);
				x.controlSend(title, "", "[Class:Edit;Instance:1]", "{DEL 2}", false);

				x.controlClick(title, "", "[Class:Edit;Instance:1]", "left", 1);

				x.controlSend(title, "", "[Class:Edit;Instance:1]", "{DEL 10}", false);

				x.controlSend(title, "", "[Class:Edit;Instance:1]", "{BACKSPACE 10}", false);

				x.controlSend(title, "", "[Class:Edit;Instance:1]", sPassword, true);
				x.controlSend(title, "", "[Class:Edit;Instance:1]", "{ENTER}", false);
				System.out.println("Done");
				i = 30;
			}
		}

	}

	public static void handleGCCredentialpopupOnlyPassword(String sUsername, String sPassword) {

		AutoItX x = new AutoItX();
		for (int i = 0; i < 5; i++) {

			x.sleep(1000);

			String title = "Error Loading Extension";
			if (x.winExists(title)) {
				x.winActivate(title, "");
				x.winWaitActive(title, "", 20);
				x.controlClick(title, "", "[Class:Button;Text:OK]", "left", 1);

				System.out.println("Done");
				i = 30;
			}
		}

		x.sleep(20000);
		x.send(sUsername);
		x.send("{TAB}", false);
		x.send(sPassword);
		x.send("{ENTER}", false);
	}

}
