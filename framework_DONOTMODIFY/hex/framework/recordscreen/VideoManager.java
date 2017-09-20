/**Copyright (C) Hexaware Technologies Ltd - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Arun Shanmugam <aruns3@hexaware.com>, September 2015
 **/
package hex.framework.recordscreen;

import hex.framework.RunTime.Configuration;
import hex.framework.RunTime.RunTimeManager;
import hex.framework.RunTime.Runtime;
import hex.framework.enums.Config;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import static org.monte.media.AudioFormatKeys.*;

import org.monte.media.Format;
import org.monte.media.FormatKeys;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;

import org.monte.media.FormatKeys.MediaType;
import org.monte.media.VideoFormatKeys;

import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.*;

import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class VideoManager {

	private ScreenRecorder screenRecorder;
	static VideoManager videoReord;
	static VideoManager videoReordLineA;
	// public static String sFileName;

	// public static synchronized String getsFileName() {
	// return sFileName;
	// }

	public static synchronized void setsFileName(String sFileName) {
		sFileName = sFileName;
	}

	public static void StartRecording(String sFileName) throws Exception {
		if(Configuration.IsVideoCapture()){
		videoReord = new VideoManager();
		videoReord.startRecording(sFileName);
		}

	}

	public static void StartRecordingLineA(String sFileName) throws Exception {
		if(Configuration.IsVideoCapture()){
		videoReordLineA = new VideoManager();
		videoReordLineA.startRecording(sFileName);
		}

	}

	public static void StopRecording() throws Exception {
		if(Configuration.IsVideoCapture()){
		videoReord.stopRecording();
		}
	}

	public static void StopRecordingLineA() throws Exception {
		if(Configuration.IsVideoCapture()){
		videoReordLineA.stopRecording();
		}
	}

	private void startRecording(String sFileName) throws Exception {
		File file = new File(Config.LOCALRRSUITE_REPORT_RESOURCES_PATH.toString());

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;

		Rectangle captureSize = new Rectangle(0, 0, width, height);

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();

		this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
				new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, FormatKeys.MIME_AVI),

				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
						CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
						Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
				null, file, sFileName);
		this.screenRecorder.start();

	}

	private void stopRecording() throws Exception {
		this.screenRecorder.stop();
	}
}
