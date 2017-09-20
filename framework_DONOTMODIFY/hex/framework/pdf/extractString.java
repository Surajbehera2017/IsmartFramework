package hex.framework.pdf;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import hex.framework.enums.Logstatus;
import hex.framework.logger.Log;

public class extractString extends Log {
	
	public boolean verifyPDFContent(String strURL, String str) throws IOException {
		System.setProperty("http.proxyHost", "hexproxy.hexaware.local");
		System.setProperty("http.proxyPort", "3128");

		boolean flag = false;

		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		String parsedText = str;
		try {
			URL url = new URL(strURL);
			System.out.println(url);
			BufferedInputStream file = new BufferedInputStream(url.openStream());
			PDFParser parser = new PDFParser(file);
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(1);
			pdDoc = new PDDocument(cosDoc);
			parsedText = pdfStripper.getText(pdDoc);
		} catch (MalformedURLException e2) {
			System.err.println("URL string could not be parsed "
					+ e2.getMessage());
			implicitLog(false, "URL String has to be parsed", "URL string could not be parsed "+ e2.getMessage(), true);
			
		} catch (IOException e) {
			System.err.println("Unable to open PDF Parser. " + e.getMessage());
			implicitLog(false, "PDF Parser should be opened", "Unable to open PDF Parser. " + e.getMessage(), true);
			
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e1) {
				e.printStackTrace();
				
			}
		}
		System.out.println("+++++++++++++++++");
		try{
			int startIndex = parsedText.indexOf(str, 1);
			int totalLength = str.length();
			System.out.println(parsedText.substring(startIndex, totalLength + startIndex));
			
			logStep(Logstatus.INFO, "Extract text from PDF", "The extracted text from PDF is :"+parsedText.substring(startIndex, totalLength + startIndex), true);
			
			System.out.println("+++++++++++++++++");
			
		}
		catch(Exception e3)
		{
			logStep(Logstatus.FAIL, "Extract text from PDF", "The expected string is not present in PDF", true);
			
		}
		
		if (parsedText.equals(str)) {
			flag = true;
		}
		return flag;
	}
}
