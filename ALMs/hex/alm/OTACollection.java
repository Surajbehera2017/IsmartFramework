package hex.alm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComFailException;
import com.jacob.com.Dispatch;
import com.jacob.com.SafeArray;
import com.jacob.com.Variant;

public class OTACollection {


	// Variable to hold OTA connection
	public static ActiveXComponent QCConnection;
	static Map<String,String> mDomain;
	//Holds system log
	public static StringBuilder sStatus = new StringBuilder("System Status: ");
	
	// Create ALM connection object
	public static  void   CreateALMObject() {
		QCConnection = new ActiveXComponent("TDApiOle80.TDConnection");
		sStatus.append("/n Creating connection --> Success");
		
	}
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	
	
	// Establish ALM connection
	public static boolean CreateALMConnection(String sURL) {
		
		
		try {	
			
			Dispatch.call(QCConnection, "InitConnectionEx", sURL);
			sStatus.append("/n Connecting ALM URL: "+sURL);
		} catch (ComFailException e) {
			sStatus.append("/n Connecting ALM URL: "+sURL);
			sStatus.append("/n Connection Failed, Please relaunch the application/ check ALM URL / ");
			return false;
		}
		sStatus.append("Connection Status: Success");
		return true;
		
		

	}
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	// Login as user XXXXX
	public static boolean  LogintoALMas(String sUser, String sPassword) {
		
		try{
			
		
		// Login -User and Password
		Dispatch.call(QCConnection, "Login", sUser, sPassword);

		//Check the connection status
		if (Dispatch.call(QCConnection, "LoggedIn").toBoolean()) {

			System.out.print("Connected Successfully");

			// Dispatch.call(component, "Logout");
			// Dispatch.call(component, "ReleaseConnection");


		} else {
			
			return false;
			
		}
		
		} catch (ComFailException e) {
			return false;
		}
	
		
	
		return true;
		
	}
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
	// Get Available projects 
	
	public static List GetAvailableprojects(String sDomain){
		
		//Dispatch oProjects = Dispatch.call(QCConnection, "GetAllVisibleProjectDescriptors" ).toDispatch();
		Dispatch oProjects = Dispatch.call(QCConnection, "VisibleProjects" , sDomain ).toDispatch();
		
		
		
		int iProjectsCount = Integer.parseInt(Dispatch.call(oProjects, "Count").toString());
		List<String> sProjects = new ArrayList();
		for (int i = 1; i <= iProjectsCount; i++) {
			// String oChild = "Child("+i+")";
		//	Dispatch oChildItem = Dispatch.call(oDomains, "Item", i).toDispatch();
		//	sDomain.add(Dispatch.call(oDomains, "Item", i).toString());
					
			sProjects.add(Dispatch.call(oProjects, "Item", i).toString());
			
		}
		return sProjects;

	}
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
	// Get Available Domains 
	
	public static Map GetAvailableDomains(){
		
		Dispatch oDomains = 	Dispatch.call(QCConnection, "VisibleDomains").toDispatch();
		
int iDomainCount = Integer.parseInt(Dispatch.call(oDomains, "Count").toString());

 mDomain = new HashMap();



List<String> lstDomain = new ArrayList();
		
		for (int i = 1; i <= iDomainCount; i++) {
			// String oChild = "Child("+i+")";
		//	Dispatch oChildItem = Dispatch.call(oDomains, "Item", i).toDispatch();
		//	sDomain.add(Dispatch.call(oDomains, "Item", i).toString());
			
			String sDomain = Dispatch.call(oDomains, "Item", i).toString();
			
			List  mapDomainprj = OTACollection.GetAvailableprojects(sDomain);
			
			//lstDomain.add(Dispatch.call(oDomains, "Item", i).toString());
			
			for (Iterator iterator = mapDomainprj.iterator(); iterator
					.hasNext();) {
				String  object =  iterator.next().toString();
				mDomain.put(object, sDomain);
				
				
			}
		}
		
		
		return mDomain;
		
		
		
		
	}
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		
	// Connect to project
	
	public static void ConnectToProject(String sDomain, String sProject){
		
		//Try block for Project Connection 
		try {

			Dispatch.call(QCConnection, "Connect", sDomain, sProject);
			
			//Check the connection status
			if (Dispatch.call(QCConnection, "Connected").toBoolean()) {
				System.out.print("Project Connected Successfully");
			}

		} catch (ComFailException e) {
			System.out.print("Error while connecting project");
		}
	}
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public static void main(String[] arg) {
		

		Dispatch testplanTreeManager = Dispatch.get(QCConnection, "TreeManager").toDispatch();

		Dispatch testplanroot = Dispatch.call(testplanTreeManager, "NodeByPath", "Subject").toDispatch();
		
		int iTCfolderCount = Integer.parseInt(Dispatch.call(testplanroot, "Count").toString());
		
		
		for (int i = 1; i <= iTCfolderCount; i++) {
			// String oChild = "Child("+i+")";
			Dispatch oChildItem = Dispatch.call(testplanroot, "Child", i).toDispatch();
			System.out.println(Dispatch.call(oChildItem, "Path"));

		}




	}
	
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	public static void IgnoreHTMLFormat(){
		
		String bIgnore = "TRUE";
		//
		QCConnection.setProperty("IgnoreHTMLFormat", new Variant(bIgnore));

		
	}
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	//Release OTA connection

	public static void ReleaseConnection() {
		
		//Get ALM Status and 
		String sLoggedIn = Dispatch.call(QCConnection, "LoggedIn").toString();
		
		boolean bLoggedIn = Boolean.parseBoolean(sLoggedIn);
		
		if (bLoggedIn) {

			Dispatch.call(QCConnection, "Logout");

		} else{
			
			System.out.print("No Login instance found");
		}
		
		
		
		if (Dispatch.call(QCConnection, "Connected").toBoolean()) {
		Dispatch.call(QCConnection, "ReleaseConnection");
		}else{
			System.out.print("Currently ALM is not connected");
		}
	}




}