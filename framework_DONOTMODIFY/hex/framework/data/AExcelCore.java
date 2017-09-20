/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.data;

import hex.framework.enums.Config;
import hex.framework.utils.GenericUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class AExcelCore implements IDataManager<AExcelCore> {
	private Map<Integer, HashMap<String, String>> objExcelMap = new HashMap<Integer, HashMap<String, String>>();
	private int iCounter = 0;
	private int RowCounter = 0;
	private int ColCounter = 0;
	private XSSFWorkbook Excel;
	private FileInputStream FIExcel;
	private File file;

	@Override
	public AExcelCore establishConnection() {

		setFile(new File(GenericUtils.getProjectpath() + Config.DATASHEET_NAME));

		try {
			setFIExcel(new FileInputStream(getFile()));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return this;

	}

	@Override
	public AExcelCore establishConnection(String excelpath) {
		// TODO Auto-generated method stub
		setFile(new File(excelpath));

		try {
			setFIExcel(new FileInputStream(getFile()));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return this;
	}

	@Override
	public AExcelCore loadData() {

		try {
			setExcel(new XSSFWorkbook(getFIExcel()));
		} catch (IOException e) {

			e.printStackTrace();
		}
		return this;
	}

	@Override
	public void closeConnection() {

		try {

			if (Excel instanceof XSSFWorkbook)
				Excel.close();
			Excel = null;

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public String cellToString(Cell cell) {

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:

			return Boolean.toString(cell.getBooleanCellValue());
		case Cell.CELL_TYPE_NUMERIC:

			return String.valueOf(cell.getNumericCellValue());
		case Cell.CELL_TYPE_STRING:

			return cell.getStringCellValue();
		case Cell.CELL_TYPE_BLANK:
			return "";

		}
		return null;
	}

	// public IExcelCore clone() { return null; }
	public XSSFWorkbook getExcel() {
		return Excel;
	}

	public void setExcel(XSSFWorkbook excel) {
		Excel = excel;
	}

	public FileInputStream getFIExcel() {
		return FIExcel;
	}

	public void setFIExcel(FileInputStream fIExcel) {
		FIExcel = fIExcel;
	}

	public int getColCounter() {

		return ColCounter;
	}

	public void setColCounter(int colCounter) {
		ColCounter = colCounter;
	}

	public int getRowCounter() {
		return RowCounter;
	}

	public void setRowCounter(int rowCounter) {
		RowCounter = rowCounter;
	}

	public int getiCounter() {
		return iCounter;
	}

	public void setiCounter(int iCounter) {
		this.iCounter = iCounter;
	}

	public Map<Integer, HashMap<String, String>> getobjExcelMap() {
		return objExcelMap;
	}

	public void setobjExcelMap(Map<Integer, HashMap<String, String>> excelMap) {
		objExcelMap = excelMap;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
