import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class FollowWallLeft implements Behavior {
	
	private MovePilot pilot;
	private SampleProvider sp;
	float[] distance = new float[1];
	
	FollowWallLeft(MovePilot p, SampleProvider s) {
		this.pilot = p;
		this.sp = s;
	}
	
	public boolean takeControl() {
		sp.fetchSample(distance, 0);
		return (distance[0] < 0.03);
	}
	
	public void suppress() {}
	
	public void action() {
		pilot.rotateLeft();
	}

}
