import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class StopAtWall implements Behavior {
	
    //private boolean suppressBehaviour = false; // might not need this
    private MovePilot pilot;
    private SampleProvider sp;
    float[] distance = new float[1];
    
    StopAtWall(MovePilot p, SampleProvider us) {
    	this.pilot = p;
    	this.sp = us;
    }
    
    public boolean takeControl() {
		sp.fetchSample(distance, 0);
		return (distance[0] < 0.05); // true if <5cm from wall
    }
    
    public void suppress() {
    	//suppressBehaviour = true;
    }
    
    public void action() { 
    	pilot.stop();
    }
}
