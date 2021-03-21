import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class FollowWallLeft implements Behavior {
	
	private MovePilot pilot;
	private NXTUltrasonicSensor us = new NXTUltrasonicSensor(SensorPort.S2);
	private SampleProvider sp = us.getDistanceMode();
	float[] distance = new float[1];
	
	FollowWallLeft(MovePilot p) {
		this.pilot = p;
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
