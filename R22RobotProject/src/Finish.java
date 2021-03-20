import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Finish implements Behavior{
	
	private MovePilot pilot;
	private NXTLightSensor ls = new NXTLightSensor(SensorPort.S1);
	private SampleProvider light = ls.getRedMode();
	private float goal;
	private float neutral;
	private float[] level = new float[1];
	
	public Finish(MovePilot p) {
		this.pilot = p;
		calibrate();
	}
	
	public boolean takeControl() {
		light.fetchSample(level, 0);
		return(level[0] == goal);
	}
	
	public void suppress() {}
	
	public void action() {
		pilot.stop();
	}
	
	public void calibrate() {
		LCD.drawString("Calibrating sensor, follow instructions and press enter",0,0);
		LCD.drawString("Place on end goal",0,1);
		Button.ENTER.waitForPressAndRelease();
		light.fetchSample(level, 0);
		goal = level[0];
			
		LCD.drawString("Place average floor",0,1);
		Button.ENTER.waitForPressAndRelease();
		light.fetchSample(level, 0);
		neutral = level[0];
			
		LCD.drawString("The light value of the end goal is: "+goal,0,1);
		LCD.drawString("The light value of the room is: "+neutral,0,2);
			
		LCD.drawString("Calibration complete",0,3);
		LCD.drawString("Place at start of maze",0,5);
		LCD.drawString("press enter when ready",0,6);
		Button.ENTER.waitForPressAndRelease();
		LCD.clear();
	}

}
