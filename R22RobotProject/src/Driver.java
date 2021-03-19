import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Driver {

	final static float WHEEL_DIAMETER = 51;
	final static float AXLE_LENGTH = 44;

	public static void main(String[] args) {
		
		MovePilot pilot = getPilot(MotorPort.A, MotorPort.B, WHEEL_DIAMETER, AXLE_LENGTH);
		
		welcome();
		
		pilot.setLinearSpeed(70);
		NXTUltrasonicSensor us = new NXTUltrasonicSensor(SensorPort.S2);
		NXTUltrasonicSensor ul = new NXTUltrasonicSensor(SensorPort.S3);
		SampleProvider distanceForward = us.getDistanceMode();
		SampleProvider distanceSide = ul.getDistanceMode();
		
		//Behaviors
		Behavior emergencyStop = new EmergencyStop(pilot);
		Behavior batteryLevel = new BatteryLevel(pilot);
		Behavior stopAtWall = new StopAtWall(pilot,distanceForward);
		Behavior movingForward = new MovingForward(pilot);
		Behavior followLeft = new FollowWallLeft(pilot,distanceForward);
		Behavior followRight = new FollowWallRight(pilot,distanceSide);
		
		Arbitrator ab = new Arbitrator(new Behavior[] {movingForward,stopAtWall,followLeft,followRight,emergencyStop,batteryLevel});
		ab.go();
		us.close();
		ul.close();
	}
	
	public static MovePilot getPilot(Port left, Port right, float diam, float offset) {
		BaseRegulatedMotor mL = new EV3LargeRegulatedMotor(left);
		Wheel wLeft = WheeledChassis.modelWheel(mL, diam).offset(-AXLE_LENGTH / 2);
		BaseRegulatedMotor mR = new EV3LargeRegulatedMotor(right);
		Wheel wRight = WheeledChassis.modelWheel(mR, diam).offset(AXLE_LENGTH / 2);
		Chassis chassis = new WheeledChassis((new Wheel[] {wRight, wLeft}),WheeledChassis.TYPE_DIFFERENTIAL);
		return new MovePilot(chassis);
	}
	
	public static void welcome() {
		LCD.drawString("Maze Solver", 0, 0);
		LCD.drawString("Authors:", 0, 1);
		LCD.drawString("Rayan Miah", 0, 2);
		LCD.drawString("Jacob Artis", 0, 3);
		LCD.drawString("Mandeep Sehdev", 0, 4);
		LCD.drawString("Dylan Cheema", 0, 5);
		LCD.drawString("Version: 1.0", 0, 6);
		Button.ENTER.waitForPressAndRelease();
	}
}
