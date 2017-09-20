package hex.framework.RunTime;

import java.util.Map;

import hex.framework.RunTime.TestCase;
import hex.framework.utils.GenericUtils;

public abstract class TDHelper {

	static MapHandler getConfigMap() {

		return new MapHandler(Runtime.getConfigMap());

	}

	static MapHandler getCurrentDataMap() {

		return new MapHandler(Runtime.getCurrentDataMap());

	}

	static MapHandler getCurrentEnvMap() {

		return new MapHandler(Runtime.getCurrentEnvMap());

	}
}

class MapHandler {
	Map map;

	public MapHandler(Map m) {
		map = null;
		map = m;
	}

	public String get(String s) {
		String sTemp;
		try {
			sTemp = map.get(s).toString();
		} catch (Exception e) {
			System.out.println("Info: no value found for" + s);
			sTemp = "NA";
		}

		return sTemp;

	}
}
