package hex.alm;

import java.util.List;
import java.util.Map;

public class OTAWrapper {
	
	@SuppressWarnings("static-access")
	public static String login(String User, String sPassword, String sURL){
		
		OTACollection.CreateALMObject();
		OTACollection.CreateALMConnection(sURL);
		OTACollection.LogintoALMas(User, sPassword);
		
		
		
		return OTACollection.sStatus.toString();
		
	}
	
	public static Map getDomainsprojects(){
		return OTACollection.GetAvailableDomains();
		
	}

	
	

}
