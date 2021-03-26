import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class EmergencyStop implements Behavior {
	
	private MovePilot pilot;
	
	public EmergencyStop(MovePilot p) {
		this.pilot = p;
		LCD.clear();
		LCD.drawString("Emer Stop made",0,2);
	}
	
    //take control when button is pressed
	public boolean takeControl() {
		if (Button.ESCAPE.isDown()) {
			return true;
		}
		return false;
	}
	
    //suppresses behaviour
	public void suppress() {
	}
	
    //takeControl = true when button is pressed
    //therefore stops when takeControl is true
	public void action() {
		pilot.stop();
		LCD.clear();
		LCD.drawString("Emer Stop action",0,2);
	}
}