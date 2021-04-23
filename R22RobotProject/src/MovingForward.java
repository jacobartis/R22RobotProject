import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class MovingForward implements Behavior {
	
    private MovePilot pilot;
    
    MovingForward(MovePilot p) {
    	this.pilot = p;
    }
    
    public boolean takeControl() {
    	return true;
    }
    
    public void suppress() {
    	pilot.stop();
    }
    
    public void action() { // only moves the robot if it isn't moving, stops the robot from jittering
    	if (!pilot.isMoving()) {
    			pilot.forward();
    	}
    }
}