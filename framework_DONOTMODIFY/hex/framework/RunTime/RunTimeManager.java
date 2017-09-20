/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.RunTime;

import hex.framework.enums.Logstatus;
import hex.framework.logger.Log;
import hex.framework.logger.Reporter;

import com.relevantcodes.extentreports.LogStatus;

public class RunTimeManager {

	public static void emergencyAction(String sMessage) {

		Log.logStep(Logstatus.UNKNOWN, "######EXCEPTION OCCURED######", sMessage, true);

	}

	public static void emergencyAction(String sMessage, boolean blncreateTestRecord) {

		// Create Test Run for HTML report / ALM
		Log.startTest("Exception occured", "Detected Exception");

		Log.logStep(Logstatus.FAIL, "######EXCEPTION OCCURED######", sMessage, true);
		Log.endtest();

	}

}
