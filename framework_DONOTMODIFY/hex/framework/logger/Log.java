/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import hex.framework.RunTime.Configuration;
import hex.framework.RunTime.Notification;
import hex.framework.RunTime.TestCase;
import hex.framework.automation.Clickable;
import hex.framework.browser.BrowserFactory;
import hex.framework.common.Media;
import hex.framework.enums.Logstatus;
import hex.framework.recordscreen.VideoManager;
import hex.framework.utils.GenericUtils;
import atu.alm.wrapper.enums.StatusAs;

import com.relevantcodes.extentreports.LogStatus;

public class Log extends Media implements IReporter {
	private static String sPath;

	private static Thread Handler;

	public static synchronized void implicitLog(boolean sStatus, String sStepName, String sActual, boolean Screenshot) {
		Logstatus sLogstatus = (sStatus) ? Logstatus.PASS : Logstatus.FAIL;
		logStep(sLogstatus, sStepName, sActual, Screenshot);
	}

	private static boolean boolScreenCapture = false;

	private static boolean boolPass = true;
	private static boolean runStatus = true;

	public static void startTest() throws Exception {

		Log.startTest(
				BrowserFactory.getsDriverName() + "_" + Configuration.getApplication().toString() + "_"
						+ TestCase.getTestData().get("TESTCASEDESCRIPTION"),
				TestCase.getTestData().get("TESTCASEDESCRIPTION").toString());

		// Steps to be taken for dependent and independent TCs

	}

	public static void startTest(String sName, String sDescription) {
		Notification.Notify(sName, sDescription);
		Reporter.setTestnull();

		sPath = TestCase.getTestCaseId() + "_" + BrowserFactory.getsDriverName() + "_"
				+ GenericUtils.getDateTimeStamp();

		try {
			VideoManager.StartRecording(sPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (Configuration.IsALMReportRequired()) {

			try {
				ALMConnection.createRun(sName + "::Browser::" + BrowserFactory.getsDriverName());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Reporter.startTest(sName, sDescription);
		// Log.logStep(Logstatus.INFO, "Execution Started",
		// Reporter.getTest().addScreencast(Config.LOCALRRSUITE_REPORT_RESOURCES_FOLDERNAME
		// + sPath+ ".avi"));

		Log.logStep(Logstatus.INFO, "Execution Started", "Browser::" + BrowserFactory.getsDriverName());

		Reporter.getTest().assignCategory(BrowserFactory.getsDriverName());
		Reporter.getTest().assignCategory(Configuration.getApplication().toString());

		TestCase.setTCStartedExecution(true);

	}

	public static synchronized void logStep(Logstatus sStatus, String sStepName, String sActual,
			boolean boolScreenShot) {
		boolScreenCapture = (boolScreenShot) ? true : false;
		logStep(sStatus, sStepName, sActual);

		boolScreenCapture = false;
	}

	public static synchronized void logStep(Logstatus status, String sStepName, String sActual) {

		if (Logstatus.PASS.equals(status)) {
			boolPass = true;
			if (Configuration.IsALMReportRequired())
				ALMConnection.addStep(sStepName, sActual, StatusAs.PASSED);
			Reporter.addStep(sStepName, sActual, LogStatus.PASS, boolScreenCapture);
		}

		if (Logstatus.FAIL.equals(status)) {
			runStatus = false;
			boolPass = false;
			if (Configuration.IsALMReportRequired())
				ALMConnection.addStep(sStepName, sActual, StatusAs.FAILED);
			Reporter.addStep(sStepName, sActual, LogStatus.FAIL, true);
		}

		if (Logstatus.INFO.equals(status)) {
			boolPass = true;
			if (Configuration.IsALMReportRequired())
				ALMConnection.addStep(sStepName, sActual, StatusAs.PASSED);
			Reporter.addStep(sStepName, sActual, LogStatus.INFO, boolScreenCapture);
		}

		boolScreenCapture = false;
	}

	public static void endtest() {

		try {
			Reporter.endtest();
			// Reporter.getInstance().flush();
			TestCase.setTCStartedExecution(false);
			try {
				VideoManager.StopRecording();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (runStatus) {

				play("C:\\WINDOWS\\Media\\chimes.wav");
				if (Configuration.IsALMReportRequired())
					try {

						ALMConnection.updateResultForExisting(StatusAs.PASSED);
						ALMConnection.getInstance().close();
						ALMConnection.setNull();

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} else if (!runStatus) {
				// Windows Ringout.wav
				play("C:\\WINDOWS\\Media\\Speech Off.wav");
				if (Configuration.IsALMReportRequired())
					try {
						ALMConnection.updateResultForExisting(StatusAs.FAILED);

						ALMConnection.getInstance().close();
						ALMConnection.setNull();

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				runStatus = true;
			}
			boolPass = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void close() {
		try {
			Reporter.close();

			if (Configuration.IsALMReportRequired())
				ALMConnection.getInstance().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/// To HANDLE RRR APPISSUE
	public static void AppHandler() {

		Runnable r = () -> {
			System.out.println("Thread for handling popup started ");
			while (true) {
				try {
					if (BrowserFactory.getDriver() instanceof WebDriver) {

						if (BrowserFactory.getDriver()
								.findElements(By
										.xpath("//div[contains(@class,'modal-content') and contains(.,'View report')]//button[contains(.,'No')]"))
								.size() != 0) {
							new Clickable(
									By.xpath(
											"//div[contains(@class,'modal-content') and contains(.,'View report')]//button[contains(.,'No')]"),
									0).click();

						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		};

		Handler = new Thread(r);

		Handler.start();

	}

}
