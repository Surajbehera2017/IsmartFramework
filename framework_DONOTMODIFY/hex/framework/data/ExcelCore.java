/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelCore extends AExcelCore {

	@Override
	public Map<Integer, HashMap<String, String>> getConfigMap() {
		// TODO Auto-generated method stub
		return getDataMap(1);
	}

	@Override
	public Map<Integer, HashMap<String, String>> getEnvData() {

		return getDataMap(2);

	}

	@Override
	public Map<Integer, HashMap<String, String>> getTDS1Data() {
		return getDataMap(3);

	}

	@Override
	public Map<Integer, HashMap<String, String>> getDataMap(int iSheetat) {
		// setiCounter(0);

		// getExcel().getSheet("");

		Iterator<Row> IRow = getExcel().getSheetAt(iSheetat).iterator();
		Row Header = null;
		while (IRow.hasNext()) {
			if (getiCounter() == 0) {

				setiCounter(getiCounter() + 1);
				Header = IRow.next();

			}

			getobjExcelMap().put(getiCounter(), new HashMap<String, String>());
			// System.out.println(getiCounter());
			Row row = IRow.next();
			Iterator<Cell> HeaderCellIterator = Header.cellIterator();
			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();

				Cell HeaderCell = HeaderCellIterator.next();

				String sHeader = cellToString(HeaderCell);
				String sValue = cellToString(cell);
				// System.out.println(sHeader+"-->"+sValue);
				getobjExcelMap().get(getiCounter()).put(sHeader, sValue);

			}
			setiCounter(getiCounter() + 1);
			// System.out.println("");
		}

		return getobjExcelMap();

	}

}
