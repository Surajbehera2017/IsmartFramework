package hex.dl.common.utils;

import hex.framework.RunTime.TestCase;
import hex.framework.utils.GenericUtils;

public abstract class ADataHelper {

	public static String getData(String Header) {
		String sData = "NODATANODATA";
		try {

			sData = TestCase.getTestData().get(Header).toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sData;
	}

	public static String getDataPlusRND(String Header) {
		String sData = "NODATANODATA";
		try {

			sData = TestCase.getTestData().get(Header).toString() + GenericUtils.getDateTimeStamp();
			;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sData;
	}

}
