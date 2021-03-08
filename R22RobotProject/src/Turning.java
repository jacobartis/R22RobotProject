import java.util.Random;

import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Turning implements Behavior { 
	
	private MovePilot pilot;
	private SampleProvider distance;
	float[] level = new float[1];
	
	public Turning(MovePilot pilot, SampleProvider distance) {
		this.pilot = pilot;
		this.distance = distance;
	}
	
	public boolean takeControl() {
		distance.fetchSample(level, 0);
		return (level[0] <= 0.1);
	}
	
	public void suppress() {
	}
	
	public void action() { //; random for now
		Random rand = new Random();
		if (rand.nextInt(2) == 0) {
			pilot.rotateLeft();
		} else {
			pilot.rotateRight();
		}
	}
	
}
