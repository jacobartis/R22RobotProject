import lejos.hardware.lcd.LCD;
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
		LCD.clear();
		LCD.drawString("FWL made",0,2);
	}
	
	public boolean takeControl() {
		sp.fetchSample(distance, 0);
		return (distance[0] < 0.1);
	}
	
	public void suppress() {}
	
	public void action() {
		if (!pilot.isMoving()) {
			LCD.clear();
			LCD.drawString("FWL action",0,2);
			pilot.rotate(90);
		}
	}

}
