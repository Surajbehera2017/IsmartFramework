/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.enums;

public enum Element {

	Mandatory("MANDATORY"), Optional("OPTIONAL"), GetFirstVisible("GetFirstVisible"), ReturnAsCollection(
			"ReturnAsCollection");

	private final String text;

	/**
	 * @param text
	 */
	private Element(final String text) {
		this.text = text.toString();
	}

	@Override
	public String toString() {
		return text.toString();
	}
}
