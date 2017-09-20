/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.SuffixFileFilter;

public class WinFso {

	public static String getProjectPath() {
		File fProject = new File(".");
		String sPath = "";
		try {
			sPath = fProject.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sPath;

	}



	public static boolean createFolderNoOverWrite(String sFolderPath) {

		File theDir = new File(sFolderPath);
		if (!theDir.exists()) {
			theDir.mkdir();
			return true;
		} else {
			return false;
		}
	}

}
