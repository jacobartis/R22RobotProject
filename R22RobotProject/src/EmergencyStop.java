import lejos.hardware.Button;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class EmergencyStop implements Behavior {
	
	private MovePilot pilot;
	
	public EmergencyStop(MovePilot p) {
		this.pilot = p;
	}
	
    //take control when button is pressed
	public boolean takeControl() {
		if (Button.ESCAPE.isDown()) {
			return true;
		}
		return false;
	}
	
    //suppresses behaviour
	public void suppress() {}
	
    //takeControl = true when button is pressed
    //therefore stops when takeControl is true
	public void action() {
		pilot.stop();
	}
}