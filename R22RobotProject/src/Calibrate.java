import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.subsumption.Behavior;

public class Calibrate{
	
	final int GOAL = 1;
	final int FLOOR = 0;
	
	private boolean runCalibrate = true;
	private boolean suppressCalibrate = false;
	private float[] level = new float[3];
	private NXTLightSensor ls;
	
	public void run() {
		
		ls = new NXTLightSensor(SensorPort.S1);
		LCD.drawString("Calibrating sensor, follow instructions and press enter",0,0);
		LCD.drawString("Place on end goal",0,1);
		Button.ENTER.waitForPressAndRelease();
		ls.fetchSample(level, GOAL);
			
		LCD.drawString("Place average floor",0,1);
		Button.ENTER.waitForPressAndRelease();
		ls.fetchSample(level, FLOOR);
			
		LCD.drawString("The light value of the end goal is: "+level[GOAL],0,1);
		LCD.drawString("The light value of the floor is: "+level[FLOOR],0,2);
			
		LCD.drawString("Calabration complete",0,3);
		LCD.clear();
	}
	public float getGoal() {
		return level[GOAL];
	}
}
