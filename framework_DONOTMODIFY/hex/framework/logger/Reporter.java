/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.logger;

import hex.framework.enums.Config;
import hex.framework.utils.ScreenCapture;
import atu.alm.wrapper.enums.StatusAs;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reporter {
	private static ExtentReports extent;
	// private static ExtentTest extentTest;

	private static ExtentTest extTest = null;

	public static ExtentReports getInstance() {
		if (extent == null) {
			extent = new ExtentReports(Config.LOCALRRSUITE_REPORTSPATH.toString() + Config.REPORTFILE_NAME.toString(),
					true);

			// optional
			extent.config().documentTitle("Stockland Automation Report").reportName("Stockland Regression Suite")
					.reportHeadline("");

			// optional
			extent.addSystemInfo("Selenium Version", "2.51").addSystemInfo("Environment", "QA");
		}
		return extent;
	}

	public static ExtentTest startTest(String sName, String sDesc) {

		if (extTest == null) {
			extTest = getInstance().startTest(sName, sDesc);

		}
		return extTest;
	}

	// returns test object
	public static ExtentTest getTest() {
		return extTest;

	}

	public static void setTestnull() {
		extTest = null;

	}

	public static void endtest() {

		extent.endTest(extTest);
		extent.flush();

	}

	public static void addStep(String stepName, String actual, LogStatus as, boolean sScreenShot) {
		// sScreenShot=false;

		if ((LogStatus.FAIL.equals(as)) || sScreenShot) {
			getTest().log(as, stepName, actual + getTest().addScreenCapture(ScreenCapture.takeScreenCapture()));
		} else {
			getTest().log(as, stepName, actual);
		}
	}

	public static void close() {

		if (extent != null) {
			extent.flush();
			extent.close();
			extent = null;
		}
	}
}
