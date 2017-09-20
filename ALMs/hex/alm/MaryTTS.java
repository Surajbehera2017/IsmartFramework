package hex.alm;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class MaryTTS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Dispatch Speech = new Dispatch("Speech.Robot");
		Dispatch.call(Speech, "SpeakLoudChild", "RunningIteration");
		
		
	}

}
