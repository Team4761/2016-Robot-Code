package org.robockets.stronghold.robot;

import org.robockets.buttonmanager.ButtonManager;
import org.robockets.buttonmanager.buttons.ActionButton;
import org.robockets.buttonmanager.joysticks.XboxOne;
import org.robockets.stronghold.robot.intake.ManualVerticalIntake;
import org.robockets.stronghold.robot.intake.ManualSpinIntake;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick joystick = new Joystick(0);
	
	public OI () {
		ButtonManager.addJoystick(joystick);
		ButtonManager.addButton(new ActionButton(0, XboxOne.A.getButtonNumber(), new ManualVerticalIntake(Direction.DOWN, 0), true));
		ButtonManager.addButton(new ActionButton(0, XboxOne.Y.getButtonNumber(), new ManualVerticalIntake(Direction.UP, 0), true));
		ButtonManager.addButton(new ActionButton(0, 1, new ManualSpinIntake(), true));
		ButtonManager.addButton(new ActionButton(0, 1, new ManualSpinIntake(-0.5, 0), true));
	}
}

