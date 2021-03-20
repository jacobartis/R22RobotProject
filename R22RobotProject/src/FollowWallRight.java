import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class FollowWallRight implements Behavior {
	
	private MovePilot pilot;
	private SampleProvider sp;
	float[] distance = new float[1];
	
	FollowWallRight(MovePilot p, SampleProvider s) {
		this.pilot = p;
		this.sp = s;
	}
	
	public boolean takeControl() {
		sp.fetchSample(distance, 0);
		return (distance[0] > 0.04);
	}
	
	public void suppress() {}
	
	public void action() {
		pilot.rotateRight();
		pilot.travel(0.2);
	}
	
	
}
