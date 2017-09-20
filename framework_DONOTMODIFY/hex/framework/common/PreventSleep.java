package hex.framework.common;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.util.Random;

public class PreventSleep {
	static Thread t = null;

	public static void Prevent() {
		
		
		
		
		Runnable r = ()->{

		Robot hal;
		try {
			hal = new Robot();

			Random random = new Random();
			while (true) {

				hal.delay(1000 * 30);
				Point pObj = MouseInfo.getPointerInfo().getLocation();
				//System.out.println(pObj.toString() + "x>>" + pObj.x + "  y>>" + pObj.y);
				hal.mouseMove(pObj.x + 1, pObj.y + 1);
				hal.mouseMove(pObj.x - 1, pObj.y - 1);
				pObj = MouseInfo.getPointerInfo().getLocation();
				//System.out.println(pObj.toString() + "x>>" + pObj.x + "  y>>" + pObj.y);
			}

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	
	if(!(t instanceof Thread)){
		
		System.out.println("Prevent Started");
	
	t = new Thread(r);
	t.start();
	
	}
	
	}
}
