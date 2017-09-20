/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.enums;

public enum Sprint {

	SPRINT2("SPRINT2"), SPRINT3("SPRINT3"), SPRINT4("SPRINT4"), SPRINT5("SPRINT5"), SPRINT6("SPRINT6"), SPRINT7(
			"SPRINT7"), POC("POC");

	private final String text;

	/**
	 * @param text
	 */
	private Sprint(final String text) {
		this.text = text.toString();
	}

	@Override
	public String toString() {
		return text.toString();
	}
}
