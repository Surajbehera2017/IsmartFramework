package hex.dl.common.utils;

import java.util.HashMap;
import java.util.Map;

public class ObjectShelf extends ADataHelper {

	public static Map<String, String> HolderMap = new HashMap();

	private static boolean boolPreviousTC = false;

	private static String sContract = null;
	private static String sUser = null;
	private static int iRowCount = 0;

	public static void init() {
		String sContract = null;
		String sUser = null;
		iRowCount = 0;
		boolPreviousTC = false;

	}

	public static void setiRowCount(int iRowCount) {
		ObjectShelf.iRowCount = iRowCount;
	}

	private static String sPassword = null;

	public static boolean isBoolPrevious() {
		return boolPreviousTC;
	}

	public static void setBoolPrevious(boolean boolPrevious) {
		ObjectShelf.boolPreviousTC = boolPrevious;
	}

	public static String getsContract() {
		// if(sContract.isEmpty()){
		// sContract =getData("CONTRACTID");
		// }
		return sContract;
	}

	public static void setsContract(String sContract) {
		ObjectShelf.sContract = sContract;
	}

	public static String getsPassword() {
		return sPassword;
	}

	public static ObjectShelf setsPassword(String sPassword) {
		ObjectShelf.sPassword = sPassword;
		return null;
	}

	public static String getsUser() {
		return sUser;
	}

	public static ObjectShelf setsUser(String sUser) {
		ObjectShelf.sUser = sUser;
		return null;
	}

	public static int getiRowCount() {
		return iRowCount;
	}

	public static void AddToiRowCount(int iRowCount) {
		ObjectShelf.iRowCount = ObjectShelf.iRowCount + iRowCount;
	}

}
