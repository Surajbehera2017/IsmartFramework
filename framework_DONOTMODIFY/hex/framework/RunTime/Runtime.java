/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.RunTime;

import hex.framework.Driver;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Runtime {

	private static Map currentEnvMap;
	private static Map currentdataMap;
	private static Map ConfigMap;

	public static Map getCurrentEnvMap() {
		return currentEnvMap;
	}

	public static void setCurrentEnvMap(Map envMap) {
		Runtime.currentEnvMap = null;
		Runtime.currentEnvMap = envMap;
	}

	public static void setCurrentDataMap(Map rowMap) {
		Runtime.currentdataMap = null;

		Runtime.currentdataMap = mapValuePairs(rowMap);
		System.out.println(currentdataMap.toString());

	}

	private static Map mapValuePairs(Map map) {

		Map<String, String> TDMAP = new HashMap();

		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			String sTDKey = (String) pair.getKey();
			String sTDVal = (String) pair.getValue();
			TDMAP.put(sTDKey, sTDVal);

			if (sTDVal.contains(":=")) {

				String arrComplex[] = sTDVal.split(";");

				for (int i = 0; i < arrComplex.length; i++) {

					String[] sValPair = arrComplex[i].split(":=");

					String sEntity = sValPair[0];
					String sVal = sValPair[1];

					TDMAP.put(sEntity, sVal);

				}

			}
			// System.out.println(map.toString());

		}
		return TDMAP;
	}

	public static Map getCurrentDataMap() {

		return currentdataMap;
	}

	public static Map getConfigMap() {
		return ConfigMap;
	}

	public static void setConfigMap(Map currentConfigMap) {
		Runtime.ConfigMap = null;
		Runtime.ConfigMap = currentConfigMap;
	}

	public static synchronized void TestDataMapMoveToNext() {
		Driver.dataMapMoveToNextRow();
	}

}


