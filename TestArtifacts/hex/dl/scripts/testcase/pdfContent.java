package hex.dl.scripts.testcase;

import hex.dl.common.utils.ADataHelper;
import hex.framework.pdf.extractString;

public class pdfContent extends ADataHelper
{
	public static void  TestScript() throws Exception 
	{
		extractString extract = new extractString();
		extract.verifyPDFContent(getData("URL"),getData("STRING"));
		
	}
}
