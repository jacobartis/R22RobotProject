import lejos.hardware.Battery;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class BatteryLevel implements Behavior {
	
	private boolean suppressBehaviour = false;

	//takes control if the battery is low
	public boolean takeControl() {
		if (Battery.getVoltage() < 0.5) {
			return true;
		}
		return false;
	}
	
	//suppresses behaviour
	public void suppress() {
		suppressBehaviour = true;
	}
	
	//actions for closing when battery is too low
	public void Goodbye() {
		LCD.clear();
		Sound.twoBeeps();
		LCD.drawString("Battery Too Low!", 1, 1);
		delay.msDelay(1000);
		LCD.drawString("Shutting Down", 1, 2);
		LCD.clear();
		Sound.twoBeeps();
		LCD.drawString("Goodbye!", 1, 1);
		Sound.buzz();
		MovePilot.stop();
	}

	//displays on screen that the battery is low
	//stops pilot when battery is too low
	public void action() {
		while (takeControl()) {
			LCD.drawString("Battery Low!", 1, 1);
			Sound.beep();
			while (suppressBehaviour) {
				Thread.yield();
			}
			LCD.clear();
			if (Battery.getVoltage() < 0.2) {
				Goodbye();
			}
		}
	}

	
}
