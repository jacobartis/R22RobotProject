import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class StopAtWall implements Behavior {
	
    //private boolean suppressBehaviour = false; // might not need this
    private MovePilot pilot;
        
    float[] distance = new float[1];
    EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S1);
    SampleProvider sp = us.getDistanceMode();
    
    StopAtWall(MovePilot p) {
    	this.pilot = p;
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