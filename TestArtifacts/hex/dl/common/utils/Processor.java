package hex.dl.common.utils;

import java.net.SocketException;

import org.openqa.selenium.remote.UnreachableBrowserException;

import hex.framework.RunTime.TestCase;
import hex.framework.browser.BrowserFactory;
import hex.framework.enums.Logstatus;
import hex.framework.logger.Log;
import hex.framework.utils.AutoBotFactory;
import hex.framework.utils.CredentialManager;

public class Processor extends ADataHelper {
	static int i = 0;
	static TCCallable tccopy = null;

	public static void Caller(TCCallable tc) {

		tccopy = tc;
		try {

			Log.startTest();
			initialize();
			tc.TestScript();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			Log.endtest();
			BrowserFactory.destroyDriver();

		}
	}
	
	static void initialize() {
		//ObjectShelf.setsUser(getData("USERNAME")).setsPassword(getData("USERPASSWORD"));

		// Destroy Driver before starting the TC
		BrowserFactory.destroyDriver();

	//	CredentialManager.addCredentials(TestCase.getApplicationDomain(), ObjectShelf.getsUser(),
			//	ObjectShelf.getsPassword());
		
		BrowserFactory.navigateTo(TestCase.sURL());

		
	}
}
