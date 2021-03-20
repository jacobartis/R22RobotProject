import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class MovingForward implements Behavior {
	
    private boolean suppressBehaviour = false;
    private MovePilot pilot;
    
    MovingForward(MovePilot p) {
    	this.pilot = p;
    }
    
    public boolean takeControl() {
    	return true;
    }
    
    public void suppress() {
    	suppressBehaviour = true;
    }
    
    public void action() {
    	if (!pilot.isMoving()) {
    		pilot.forward();
    		if (suppressBehaviour) {
    			pilot.stop();
    		}
    	}
    }
}