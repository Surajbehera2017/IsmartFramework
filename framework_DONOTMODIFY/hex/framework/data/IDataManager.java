/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;

public interface IDataManager<T extends IDataManager<T>> {

	public T establishConnection();

	public T establishConnection(String excelpath);

	public T loadData();

	<X extends Map> X getConfigMap();

	Map<Integer, HashMap<String, String>> getEnvData();

	Map<Integer, HashMap<String, String>> getTDS1Data();

	void closeConnection();

	public String cellToString(Cell cell);

	Map<Integer, HashMap<String, String>> getDataMap(int iSheetat);

}
