/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExcelFactory {

	private static IDataManager Envexcel;
	private static Map EnvMap;
	private static IDataManager TDexcel;
	private static Map TDMap;
	private static Map unmodifiableMap;
	private static IDataManager ConfigExcel;
	private static Map ConfigMap;

	public static void main(String[] args) {

		// IExcelCore excel1 = new ExcelCore();
		// IExcelCore excel = new ExcelCore();
		// printMap(excel.setExcel().loadExcel().getEnvMap());
		// printMap(excel1.setExcel().loadExcel().getConfigMap());
		// excel = null;
		// excel1 = null;
	}

	public static Map getEnvData() {

		if (Envexcel instanceof IDataManager) {
			return EnvMap;
		} else {
			Envexcel = new ExcelCore();
			unmodifiableMap = Envexcel.establishConnection().loadData().getEnvData();

			EnvMap = Collections.unmodifiableMap(unmodifiableMap);
			return EnvMap;

		}
		// return EnvMap;

	}

	public static Map getTDS1Data() {
		if (TDexcel instanceof IDataManager) {
			return TDMap;
		} else {
			TDexcel = new ExcelCore();
			return TDMap = TDexcel.establishConnection().loadData().getTDS1Data();

		}
	}

	public static List<Map> getTDS1DataAsList() {

		getTDS1Data();

		List<Map> list = new ArrayList<Map>(getTDS1Data().values());
		return list;

	}

	public static Map getConfigData() {
		if (ConfigExcel instanceof IDataManager) {
			return ConfigMap;
		} else {
			ConfigExcel = new ExcelCore();
			return ConfigMap = ConfigExcel.establishConnection().loadData().getConfigMap();

		}

		// ALMURL ALMUSERNAME ALMPASSWORD UPDATERESULTSINALM VOICEASSISTANT
		// POPUPMSGS VIDEOCAPTURE

	}

	public static List<Map> getConfigDataAsList() {

		getConfigData();

		List<Map> list = new ArrayList<Map>(getConfigData().values());
		return list;

	}

	public static List<Map> getEnvDataAsList() {

		getTDS1Data();

		List<Map> list = new ArrayList<Map>(getTDS1Data().values());
		return list;

	}

	public static void printMap(Map mp) {
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
			// printNestedMap((Map)pair.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}
	}

	public static void printNestedMap(Map mp) {
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}
	}

}
