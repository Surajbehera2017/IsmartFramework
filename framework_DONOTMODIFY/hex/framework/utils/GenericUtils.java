/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

public class GenericUtils {

	private static final String TASKLIST = "tasklist";
	private static String KILL = "\\System32\\taskkill /F /IM ";

	public static void executeCommand() {

		try {
			Process p = Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"dir && ping google.com && echo end\"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getProjectpath() {
//		if(true){
//			return "C:\\SMOKE\\";
//		}
		File dir = new File("");

		try {
			return dir.getCanonicalPath() + "\\";
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;

	}

	private static boolean isProcessRunning_p(String serviceName) throws Exception {

		Process p = Runtime.getRuntime().exec(TASKLIST);
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			// System.out.println(line);
			if (line.contains(serviceName)) {
				return true;
			}
		}
		return false;
	}

	private static void killProcess_p(String serviceName) throws Exception {
		KILL = System.getenv("SystemRoot") + KILL;

		try {
			Runtime.getRuntime().exec(KILL + serviceName);
		} catch (Exception e) {
			System.out.println("TASKKILL====>EXECUTED");
		}
	}

	public static boolean isProcessRunning(String serviceName) throws Exception {

		return isProcessRunning_p(serviceName);

	}

	public static void killProcess(String serviceName) throws Exception {

		killProcess_p(serviceName);
		Runtime.getRuntime().exec("taskkill /F /IM " + serviceName);

	}

	public static String getDateTimeStamp() {
		String D = LocalDate.now().toString();
		String T = LocalTime.now().toString();
		String snewFileName = (D + T).replaceAll("\\W", "");
		return snewFileName;
	}

}

// SyncPipe
