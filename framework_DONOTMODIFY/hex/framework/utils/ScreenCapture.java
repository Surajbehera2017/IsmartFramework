/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.utils;

import hex.framework.RunTime.Runtime;
import hex.framework.browser.BrowserFactory;
import hex.framework.enums.Config;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenCapture {

	private static String sCapturename;

	public static String takeScreenCapture() {

		String D = LocalDate.now().toString();
		String T = LocalTime.now().toString();
		String snewFileName = (D + T).replaceAll(".:", "");

		if (!(BrowserFactory.getDriver() instanceof TakesScreenshot)) {

			System.out.println("Could't take screen shot");
			return "";
		}

		File scrFile;
		try {

			scrFile = ((TakesScreenshot) BrowserFactory.getDriver()).getScreenshotAs(OutputType.FILE);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		String date = null;
		StringBuilder sTCName = new StringBuilder("");
		try {
			sTCName = (StringBuilder) Runtime.getCurrentDataMap().get("TESTCASEID");
		} catch (Exception e) {
			sTCName.append("");
		}

		try {

			FileUtils.copyFile(scrFile,
					new File(Config.LOCALRRSUITE_REPORT_RESOURCES_PATH + sTCName.toString() + snewFileName + ".jpg"));

		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		return Config.LOCALRRSUITE_REPORT_RESOURCES_PATH.toString() + snewFileName + ".jpg";
	}

}
