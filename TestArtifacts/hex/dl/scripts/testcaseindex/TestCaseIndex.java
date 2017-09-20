package hex.dl.scripts.testcaseindex;

import hex.dl.common.utils.Processor;
import hex.dl.common.utils.TCCallable;
import hex.dl.scripts.testcase.DLTC001;
import hex.dl.scripts.testcase.Stockland;
import hex.dl.scripts.testcase.pdfContent;

public class TestCaseIndex {
	
	public static void DLTC001() {	
	TCCallable t = () ->
	{
		//Function call
	DLTC001.TestScript();

	};
	
	
	Processor.Caller(t)	;

	}
	

	public static void STOCKLAND() {	
		TCCallable t = () ->
		{
	
		Stockland.TestScript();
		
		};
		
		Processor.Caller(t)	;

		}
	
	public static void PDF() {	
		TCCallable t = () ->
		{
	
		pdfContent.TestScript();
		
		};
		
		Processor.Caller(t)	;

		}
	
	
}
