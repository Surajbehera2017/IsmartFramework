/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.logger;

public interface IReporter {

	static void getInstance() {
	}

	static void startTest(String sName, String sDescription) {
	}

	static void logStep(String sStatus, String sActual, String sExpected, boolean isScreenShot) {
	}

	static void logStep(String sStatus, String sActual, String sExpected) {
	}

	static void endtest() {
	}
}
