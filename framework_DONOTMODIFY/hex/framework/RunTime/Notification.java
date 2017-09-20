/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.RunTime;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.util.concurrent.TimeUnit;

public class Notification {

	private static TrayIcon trayIcon;

	public static synchronized void Notify(String Header, String Message) {
		if (SystemTray.isSupported()) {

			getInstance().displayMessage(Header, Message, TrayIcon.MessageType.INFO);

		}

	}

	private static TrayIcon getInstance() {
		TrayIcon temp = null;

		if (trayIcon instanceof TrayIcon) {
			temp = trayIcon;
		} else {
			SystemTray tray = SystemTray.getSystemTray();

			Image image = Toolkit.getDefaultToolkit().getImage("icon.gif");
			trayIcon = new TrayIcon(image, "Notification");
			temp = trayIcon;
			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				System.err.println(e);
			}
		}

		return temp;

	}

}
