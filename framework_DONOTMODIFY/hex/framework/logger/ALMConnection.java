/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.logger;

import hex.framework.RunTime.Configuration;
import hex.framework.RunTime.RunTimeManager;
import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.ITestCase;
import atu.alm.wrapper.ITestCaseRun;
import atu.alm.wrapper.classes.Run;
import atu.alm.wrapper.classes.RunFactory;
import atu.alm.wrapper.classes.TSTest;
import atu.alm.wrapper.classes.TSTestFactory;
import atu.alm.wrapper.classes.TestSet;
import atu.alm.wrapper.classes.TestSetFolder;
import atu.alm.wrapper.classes.TestSetTreeManager;
import atu.alm.wrapper.collection.ListWrapper;
import atu.alm.wrapper.enums.StatusAs;
import atu.alm.wrapper.exceptions.ALMServiceException;

public class ALMConnection {

	private static ALMServiceWrapper oConnection;

	private static ITestCase almTest;
	private static ITestCaseRun loginRun;

	public static void updateTestStatus(StatusAs sStatus) {

		// ReportManager.getTest().getRunStatus();

		try {
			almTest = getInstance().updateResult(Configuration.getALMTestSetFolderpath(),
					Configuration.getALMTestSetName(), Configuration.getALMTestsetId(),
					Configuration.getALMTestCaseName(), sStatus);

		} catch (ALMServiceException e) {
			RunTimeManager.emergencyAction(e.getMessage());
			e.printStackTrace();
		}

	}

	public static void setNull() {
		oConnection = null;
		almTest = null;
		loginRun = null;

	}

	public static ITestCase getTest()/*     */ throws ALMServiceException
	/*     */ {
		/* 129 */TestSetTreeManager testSetTreeManager = getInstance().getAlmObj().getTestSetTreeManager();
		/*     */
		/* 131 */TestSetFolder testSetFolder = testSetTreeManager
				.getNodeByPath(Configuration.getALMTestSetFolderpath());
		/*     */
		/* 133 */TestSet testSet = testSetFolder.findTestSet(Configuration.getALMTestSetName(),
				Configuration.getALMTestsetId());
		/* 134 */TSTestFactory tsTestFactory = testSet.getTSTestFactory();
		/* 135 */ListWrapper<TSTest> listWrapper = tsTestFactory.getNewList();
		/* 136 */for (TSTest tsTest : listWrapper) {
			/* 137 */if (Configuration.getALMTestCaseName().equals(tsTest.getTestName())) {
				/* 138 */
				/* 140 */return tsTest;
				/*     */}
			/*     */}
		/* 143 */throw new ALMServiceException(
				"The Given Test Name \"" + Configuration.getALMTestCaseName() + "\" Not Found");
		/*     */}

	public static void updateResultForExisting(StatusAs as)/*     */ throws ALMServiceException
	/*     */ {

		RunFactory runFactory = getTest().getRunFactory();

		/* 177 */Run run = new Run(runFactory.addedRun);
		/* 178 */run.setStatus(as);

		/* 180 */run.post();

	}

	public static void updateAsNotCompleted() {

		// ReportManager.getTest().getRunStatus();

		try {
			almTest = getInstance().updateResult(Configuration.getALMTestSetFolderpath(),
					Configuration.getALMTestSetName(), Configuration.getALMTestsetId(),
					Configuration.getALMTestCaseName(), StatusAs.NOT_COMPLETED);

		} catch (ALMServiceException e) {
			RunTimeManager.emergencyAction(e.getMessage());
			e.printStackTrace();
		}

	}

	public static void createRun(String sRun) {

		try {
			loginRun = getInstance().createNewRun(getTest(), sRun, StatusAs.NOT_COMPLETED);
		} catch (ALMServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ALMServiceWrapper getInstance() {

		if (oConnection == null) {

			String sURL = Configuration.getALMURL().toString();
			String sUname = Configuration.getALMUserName().toString();
			String sPassword = Configuration.getALMPassword().toString();
			String sDomain = Configuration.getALMDomain();
			String sProject = Configuration.getALMProject();
			// String sTestSetPath =
			oConnection = new ALMServiceWrapper(sURL);
			try {
				oConnection.connect(sUname, sPassword, sDomain, sProject);
			} catch (ALMServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return oConnection;
	}

	public static void addStep(String sStepname, String actual, StatusAs as) {

		try {
			getInstance().addStep(loginRun, sStepname, as, sStepname, "", actual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
