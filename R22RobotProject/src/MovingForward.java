import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class MovingForward implements Behavior {
	
    private boolean suppressBehaviour = false;
    private MovePilot pilot;
    
    MovingForward(MovePilot p) {
    	this.pilot = p;
		LCD.clear();
		LCD.drawString("MF made",0,2);
    }
    
    public boolean takeControl() {
    	return true;
    }
    
    public void suppress() {
    	pilot.stop();
    }
    
    public void action() {
//    	while (!suppressBehaviour) {
    		if (!pilot.isMoving()) {
    			pilot.forward();
    		}
//    	}
//		if (suppressBehaviour) {
//			pilot.stop();
//		}
    }
}