/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.automation;

import hex.framework.enums.Element;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebTable extends AWebElement implements IWebElement {
	private List<WebElement> eleRowsList = null;
	private List<WebElement> eleColsList = null;

	public WebTable(WebElement ele) {

		super(ele);
		// TODO Auto-generated constructor stub
	}

	public WebTable(By by) {

		super(by);
		// TODO Auto-generated constructor stub
	}

	public WebTable(By by, Element... E) {

		super(by);
		// TODO Auto-generated constructor stub
	}

	public int getRowCount() {
		int count = 0;
		if (this.isDisplayed()) {

			count = getRowList().size();
			System.out.println("------------------>>>>>>>" + count);
		}
		return count;

	}

	public WebElement getElementAt(int row, int col) {
		WebElement tmpele = null;
		try {
			tmpele = getColsList(row).get(col);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

		return tmpele;

	}

	public int getColsCount(int row) {

		int count = 0;
		if (this.isDisplayed()) {

			count = getColsList(row).size();
		}
		return count;

	}

	public List<WebElement> getColsList(int row) {
		try {
			eleColsList = getRowList().get(row).findElements(By.tagName("td"));
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return eleColsList;

	}

	public List<WebElement> getRowList() {
		this.defaultAjaxSync();
		eleRowsList = this.getElement().findElements(By.tagName("tr"));
		return eleRowsList;

	}

}
