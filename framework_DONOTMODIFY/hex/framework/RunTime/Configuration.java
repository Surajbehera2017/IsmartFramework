/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.RunTime;

import hex.framework.browser.BrowserFactory;

public class Configuration extends TDHelper{

	public static String getALMURL() {
		return getConfigMap().get("ALMURL").toString();
	}

	public static String getALMUserName() {
		return getConfigMap().get("ALMUSERNAME").toString();
	}

	public static String getALMPassword() {
		return getConfigMap().get("ALMPASSWORD").toString();
	}

	public static boolean IsUpdateResultsInALM() {
		return Boolean.parseBoolean(getConfigMap().get("UPDATERESULTSINALM").toString());
	}

	public static boolean IsvoiceAssistance() {
		return Boolean.parseBoolean(getConfigMap().get("VOICEASSISTANT").toString());
	}

	public static boolean IsVideoCapture() {
		return Boolean.parseBoolean(getConfigMap().get("VIDEOCAPTURE").toString());
	}

	public static boolean IsPopupAssistance() {
		return Boolean.parseBoolean(getConfigMap().get("POPUPMSGS").toString());
	}

	public static String getALMDomain() {
		return getConfigMap().get("ALMDOMAIN").toString();
	}

	public static String getALMProject() {
		return getConfigMap().get("ALMPROJECT").toString();
	}

	public static String getALMTestSetFolderpath() {
		// ***************Older version********************
		// String Folder = "";
		// if
		// (getCurrentEnvMap().get("BROWSER").toString().contains("IE"))
		// {
		// Folder = "IE11";
		// } else if (getCurrentEnvMap().get("BROWSER").toString()
		// .contains("GC")) {
		// Folder = "Google Chrome";
		// } else if (getCurrentEnvMap().get("BROWSER").toString()
		// .contains("MOZILLA")) {
		// Folder = "Mozilla Firefox";
		// }
		//
		// return getCurrentDataMap().get("ALMTESTSETPATH").toString()
		// + "\\" + Folder;
		// ********************************************************************

		String Folder = "";
		if (getCurrentEnvMap().get("BROWSER").toString().contains("IE")) {

			Folder = getCurrentDataMap().get("ALMTESTSETPATH_IE").toString();

		} else if (getCurrentEnvMap().get("BROWSER").toString().contains("GC")) {

			Folder = getCurrentDataMap().get("ALMTESTSETPATH_GC").toString();

		} else if (getCurrentEnvMap().get("BROWSER").toString().contains("MOZILLA")) {

			Folder = getCurrentDataMap().get("ALMTESTSETPATH_FF").toString();
		}

		return Folder;

	}

	public static int getALMTestsetId() {

		return (int) Double.parseDouble(getCurrentDataMap().get("ALMTESTSETID").toString());
	}

	public static String getALMTestCaseName() {
		return getCurrentDataMap().get("ALMTESTCASENAME").toString();
	}

	public static String getIsFinalRegression() {
		return getCurrentEnvMap().get("FINALREGRESSION").toString();
	}

	public static String getALMTestSetName() {

		String tsName = "";
		String sApplication = "";
		// String sApplication = getCurrentEnvMap().get("APPLICATION")
		// .toString();

		if (getCurrentDataMap().get("ALMTESTSETNAME").toString().trim().equals("NA")) {
			boolean isFinalRegression = getIsFinalRegression().toString().trim().equalsIgnoreCase("YES");

			if (isFinalRegression) {
				if (getCurrentEnvMap().get("BROWSER").toString().contains("IE")) {
					tsName = " Regression-IE11";
				} else if (getCurrentEnvMap().get("BROWSER").toString().contains("GC")) {
					tsName = " Regression-Chrome";
				} else if (getCurrentEnvMap().get("BROWSER").toString().contains("MOZILLA")) {
					tsName = " Regression-Mozilla";
				}
			}

		} else {

			tsName = getCurrentDataMap().get("ALMTESTSETNAME").toString();
		}
		return sApplication + tsName;

	}

	public static boolean IsALMReportRequired() {
		return (getCurrentDataMap().get("ALMREPORTREQUIRED").toString().equals("YES")) ? true : false;
	}

	public static String getApplication() {
		return getCurrentEnvMap().get("APPLICATION").toString();
	}

}
