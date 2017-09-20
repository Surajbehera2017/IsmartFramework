/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.enums;

public enum Config {

	BROWSER_MOZILLA("MOZILLA"), BROWSER_CHROME("GC"), BROWSER_IE("IE"), AUTOMATIONSUITE_NAME(
			"AutomationSuite"), DATASHEET_NAME("MasterData.xlsx"), DATASHEET_ABSOLUTE_PATH(
					"ARTIFACTS\\RESOURCES\\DATA\\"), REPORTS_ABSOLUTE_PATH("ARTIFACTS\\RESOURCES\\REPORTS\\"),

	LOCALRRSUITE_PATH("D:\\AutomationSuite"),

	LOCALRRSUITE_DATAPATH("D:\\AutomationSuite\\TestData\\"),

	LOCALRRSUITE_REPORTSPATH("D:\\AutomationSuite\\Reports\\"), LOCALRRSUITE_REPORT_RESOURCES_PATH(
			"D:\\AutomationSuite\\Reports\\Resources\\"), LOCALRRSUITE_REPORT_RESOURCES_FOLDERNAME(
					"Resources\\"), LOCALRRARCHIVE_PATH("D:\\AutomationArchive\\"), REPORTFILE_NAME(
							"Reports.html"), TESTDATA_MAP("MAP"), TESTDATA_LIST("LIST"), CHROMEDRIVER(
									"chromedriver.exe"), IE64DRIVER("IEDriverServer.exe"), IE32DRIVER(
											"IEDriverServer.exe"), DRIVERSPATH("Lib\\"), IE64PATH("64\\"), IE32PATH(
													"32\\");

	private final String text;

	/**
	 * @param text
	 */
	private Config(final String text) {
		this.text = text.toString();
	}

	@Override
	public String toString() {
		return text.toString();
	}
}
