/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.enums;

/**
 * 
 * @author Arun Shanmugam
 *
 */
public enum Logstatus {
	PASS("PASS"), FAIL("FAIL"), FATAL("FATAL"), ERROR("ERROR"), WARNING("WARNING"), INFO("INFO"), SKIP("SKIP"), UNKNOWN(
			"UNKNOWN");

	private final String text;

	/**
	 * @param text
	 */
	private Logstatus(final String text) {
		this.text = text.toString();
	}

	@Override
	public String toString() {
		return text.toString();
	}
}
