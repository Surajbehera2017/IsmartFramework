/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.RunTime;

import java.util.List;
import java.util.Map;

import hex.framework.data.ExcelFactory;

public class TestCase extends TDHelper {
	// Flag for logging errors
	public static boolean isTCStartedExecution;

	public static String getTestScenarioName() {
		return getCurrentDataMap().get("SCENARIONAME").toString();
	}

	public static String getTestCaseDescription() {
		return getCurrentDataMap().get("TESTCASEDESCRIPTION").toString();
	}

	public static String getTestCaseId() {

		return getCurrentDataMap().get("TESTCASEID").toString();
	}

	public static String getTestExecutionFlow() {
		return getCurrentDataMap().get("EXECUTIONFLOW").toString();
	}

	public static String getTestIsRequired() {
		return getCurrentDataMap().get("ISREQUIRED").toString();
	}

	public static String getApplicationDomain() {
		return getCurrentDataMap().get("DOMAIN").toString();
	}

	public static Map getTestData() {
		return Runtime.getCurrentDataMap();
	}

	public static synchronized boolean isTCStartedExecution() {
		return isTCStartedExecution;
	}

	public static synchronized void setTCStartedExecution(boolean isTCStartedExecution) {
		TestCase.isTCStartedExecution = isTCStartedExecution;
	}

	public static String sURL() {
		String sApp = getCurrentEnvMap().get("APPLICATION").toString();
		String url = getConfigMap().get(sApp).toString();
		return url;
	}

	public static boolean isIndependent() {

		return (getCurrentDataMap().get("MODE").toString().equalsIgnoreCase("INDEPENDENT"));
	}

	public static boolean isDependent() {

		return (getCurrentDataMap().get("MODE").toString().equalsIgnoreCase("DEPENDENT"));
	}

	

	public static boolean isMozilla() {

		return (getCurrentEnvMap().get("BROWSER").toString().equalsIgnoreCase("MOZILLA") ? true : false);

	}

	public static boolean isIE() {

		return (getCurrentEnvMap().get("BROWSER").toString().equalsIgnoreCase("IE") ? true : false);

	}

	public static boolean isChrome() {

		return (getCurrentEnvMap().get("BROWSER").toString().equalsIgnoreCase("GC") ? true : false);

	}
}
