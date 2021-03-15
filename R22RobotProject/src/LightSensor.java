
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.*;


public class LightSensor implements Behavior {
	
	private float[] level = new float[1];
	private SampleProvider light;
	private Calibrate cal = new Calibrate();
	private float goal;
	
	LightSensor(){
		cal.run();
	}

	public boolean takeControl() {
		light.fetchSample(level, 0);
		cal.getGoal();
		return (level[0] == goal);
	}
	
	public float getSample() {
		light.fetchSample(level, 0);
		return level[0];
	}

	
	public void suppress() {}
	
	public void action() {
		if (getSample()!=goal) {
		}
	}
	
}
