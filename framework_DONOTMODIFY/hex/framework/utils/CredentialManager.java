
/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.utils;

import hex.framework.enums.Logstatus;
import hex.framework.logger.Log;

import java.io.IOException;

public class CredentialManager {

	public static void addCredentials(String strServer, String strUserName, String strPassword) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Process p = Runtime.getRuntime()
					// .exec("cmd /c start cmd.exe /K \"dir && exit\"");

					// .exec("cmd /c start cmd.exe /k \"cmdkey /add:" +strServer
					// + " /user:" +strUserName + " /pass:" + strPassword) ;
					.exec("cmd /c start cmd.exe /k \"cmdkey /add:" + strServer + " /user:" + strUserName + " /pass:"
							+ strPassword + "&& exit");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.logStep(Logstatus.PASS, "Changing Credentials:",
				" <table border=" + 1 + "><tr><th>ServerName</th><th>UserName</th><th>Password</th></tr><tr><td>"
						+ strServer + "</td><td>" + strUserName + "</td><td>" + strPassword + "</td></tr></table>");

	}

	public static void deleteCredentials(String strServer) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Process p = Runtime.getRuntime()
					// .exec("cmd /c start cmd.exe /K \"dir && exit\"");

					// .exec("cmd /c start cmd.exe /k \"cmdkey /add:" +strServer
					// + " /user:" +strUserName + " /pass:" + strPassword) ;
					.exec("cmd /c start cmd.exe /k \"cmdkey /delete:" + strServer + "&& exit");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.logStep(Logstatus.PASS, "Deleting Credentials",
				"<table border=" + 1 + "><tr><th>ServerName</th></tr><tr><td>" + strServer + "</td></tr></table>");

	}

}
