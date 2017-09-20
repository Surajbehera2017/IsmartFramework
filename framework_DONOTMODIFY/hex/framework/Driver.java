/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework;

import hex.dl.common.utils.ObjectShelf;
import hex.framework.RunTime.RunTimeManager;
import hex.framework.RunTime.Runtime;
import hex.framework.RunTime.TestCase;
import hex.framework.browser.BrowserFactory;
import hex.framework.common.ClassIterator;
import hex.framework.common.Media;
import hex.framework.data.ExcelFactory;
import hex.framework.enums.Config;
import hex.framework.logger.Log;
import hex.framework.recordscreen.VideoManager;
import hex.framework.utils.WinFso;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;

//import rrr.poc.testcasefactory.TestCaseFactory;

public class Driver extends Media implements Runnable {
	static String sProjectpath;
	static String sSprint;
	static Iterator rowData;

	public void run() {

		try {
			Initialize();
			List<Map> Config = ExcelFactory.getConfigDataAsList();

			Runtime.setConfigMap(Config.get(0));

			Map Env = ExcelFactory.getEnvData();

			envIterator(Env);

		} catch (Exception e) {
			BrowserFactory.destroyDriver();

		} finally {
			// BrowserFactory.destroyDriver();

			for (int i = 1; i < 50; i++) {
				play("C:\\WINDOWS\\Media\\Windows Ringout.wav");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Log.close();
			System.exit(0);
		}

		try {

			VideoManager.StartRecordingLineA("TestSuiteExecution");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void Initialize() {

		File dirProject = new File(Config.LOCALRRSUITE_PATH.toString());//automationsuite
		File dirTestData = new File(Config.LOCALRRSUITE_DATAPATH.toString());
		File dirReport = new File(Config.LOCALRRSUITE_REPORTSPATH.toString());//reports
		File dirArchive = new File(Config.LOCALRRARCHIVE_PATH.toString());//archive
		File dirResReports = new File(Config.LOCALRRSUITE_REPORT_RESOURCES_PATH.toString());

		WinFso.createFolderNoOverWrite(Config.LOCALRRARCHIVE_PATH.toString());
		if (!WinFso.createFolderNoOverWrite(Config.LOCALRRSUITE_PATH.toString())) {
			String D = LocalDate.now().toString();
			String T = LocalTime.now().toString();
			String snewFileName = (D + T).replaceAll(".:", "");
			try {
				FileUtils.moveDirectoryToDirectory(dirProject,
						new File(Config.LOCALRRARCHIVE_PATH.toString() + snewFileName), true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				RunTimeManager.emergencyAction(e.getMessage());
			}
		}

		dirTestData.mkdirs();
		dirResReports.mkdirs();
		dirReport.mkdirs();

	}

	public static Map dataMapMoveToNextRow() {

		Map.Entry pair = (Map.Entry) rowData.next();
		Map rowMap = (Map) pair.getValue();
		Runtime.setCurrentDataMap(rowMap);
		return rowMap;

	}

	/**
	 * 
	 * Test case Executor
	 * 
	 **/
	public static void tcExecutor(Map mp) {

		// TreeMap<Integer, String> caloriesBurnedSorted = new TreeMap<Integer,
		// String>(mp);

		// /===========
		Set set = mp.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry me = (Map.Entry) iterator.next();
			// System.out.print(me.getKey() + ": ");
			// System.out.println(me.getValue());
		}
		Map<Integer, String> map = new TreeMap<Integer, String>(mp);
		// System.out.println("After Sorting:");
		Set set2 = map.entrySet();
		Iterator iterator2 = set2.iterator();
		while (iterator2.hasNext()) {
			Map.Entry me2 = (Map.Entry) iterator2.next();
			// System.out.print(me2.getKey() + ": ");
			// System.out.println(me2.getValue());
		}

		// ///========
		rowData = mp.entrySet().iterator();

		// Iterator it = iterator2;

		while (rowData.hasNext()) {

			// Map.Entry pair = (Map.Entry) rowData.next();

			// Map rowMap = (Map) pair.getValue();

			Map rowMap = dataMapMoveToNextRow();

			if (rowMap.get("ISREQUIRED").toString().trim().equalsIgnoreCase("YES")) {

				System.out.println(rowMap.get("TESTCASENAME"));
				try {
					execute_Actions(rowMap);
				} catch (Exception e) {

					RunTimeManager.emergencyAction(e.getMessage());
				}

			}

			// it.remove();
		}
	}

	/**
	 * 
	 * Env Iterator
	 * 
	 **/
	public static void envIterator(Map mp) {

		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {

			ObjectShelf.init();

			Map.Entry pair = (Map.Entry) it.next();

			Map rowMap = (Map) pair.getValue();
			if (rowMap.get("ISREQUIRED").toString().trim().equalsIgnoreCase("YES")) {
				
				// BrowserFactory.setsDriverName(rowMap.get("BROWSER").toString());

				Map TD = ExcelFactory.getTDS1Data();

				Runtime.setCurrentEnvMap(rowMap);

				tcExecutor(TD);
				TD = null;

			}

			// it.remove();
		}
	}

	private static boolean execute_Actions(Map RowMap) throws Exception {

		List<String> c = ClassIterator.getClasseNameInPackage("hex.dl.scripts.testcaseindex");

		for (String s : c) {
			Object TCFactory = null;
			TCFactory = Class.forName(s).newInstance();

			// if (sSprint.equals("POC")) {
			// TCFactory = new hex.dl.scripts.testcasefactory.TestCaseFactory();
			// }
			//
			// TCFactory = new hex.dl.scripts.testcasefactory.TestCaseFactory();

			Method[] TCMethods = TCFactory.getClass().getMethods();
			for (int i = 0; i < TCMethods.length; i++) {
 
				if (TCMethods[i].getName().equals(RowMap.get("TESTCASEID").toString())) {

					Runtime.setCurrentDataMap(RowMap);
					try {

						TCMethods[i].invoke(TCFactory);

					} catch (Exception e) {
						e.printStackTrace();

						if (TestCase.isTCStartedExecution()) {
							RunTimeManager.emergencyAction(e.getMessage());
						} else {
							RunTimeManager.emergencyAction(e.getMessage(), true);
						}
						// BrowserFactory.destroyDriver();
						Log.endtest();
					}

					return true;
				}
			}
		}
		return false;
	}

}
