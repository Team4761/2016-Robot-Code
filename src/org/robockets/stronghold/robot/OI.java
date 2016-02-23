package org.robockets.stronghold.robot;

import org.robockets.buttonmanager.ButtonManager;
import org.robockets.buttonmanager.buttons.ActionButton;
import org.robockets.buttonmanager.joysticks.XboxOne;
import org.robockets.stronghold.robot.highgoalshooter.FireShooter;
import org.robockets.stronghold.robot.drivetrain.TurnRelative;
import org.robockets.stronghold.robot.highgoalshooter.MoveHood;
import org.robockets.stronghold.robot.highgoalshooter.MoveShootingWheel;
import org.robockets.stronghold.robot.highgoalshooter.MoveTurnTable;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.IntakesUp;
import org.robockets.stronghold.robot.intake.SetVerticalIntake;
import org.robockets.stronghold.robot.intake.SpinIntake;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick joystick = new Joystick(0);
	public static Joystick towerJoystick1 = new Joystick(1);
	public static Joystick towerJoystick2 = new Joystick(2);
	
	public OI () {
		ButtonManager.addJoystick(joystick);
		ButtonManager.addJoystick(towerJoystick1);
		ButtonManager.addJoystick(towerJoystick2);
	
		ButtonManager.addButton(new ActionButton(1, 1, new FireShooter(), false));
		ButtonManager.addButton(new ActionButton(1, 2, new MoveHood(-0.5, 0), true));
		ButtonManager.addButton(new ActionButton(1, 3, new MoveHood(0.5, 0), true));
		ButtonManager.addButton(new ActionButton(1, 4, new MoveTurnTable(-30, 0), true));
		ButtonManager.addButton(new ActionButton(1, 5, new MoveTurnTable(30, 0), true));
		ButtonManager.addButton(new ActionButton(1, 11, new MoveShootingWheel(1200), true));
		ButtonManager.addButton(new ActionButton(1, 10, new MoveShootingWheel(0), true));
		
		ButtonManager.addButton(new ActionButton(2, 2, new SetVerticalIntake(Direction.DOWN, 0, IntakeSide.BACK), true));
		ButtonManager.addButton(new ActionButton(2, 3, new SetVerticalIntake(Direction.UP, 0, IntakeSide.BACK), true));
		ButtonManager.addButton(new ActionButton(2, 4, new SpinIntake(Direction.FORWARD, 0, IntakeSide.BACK), true));
		ButtonManager.addButton(new ActionButton(2, 5, new SpinIntake(Direction.BACKWARD, 0, IntakeSide.BACK), true));
		
		/*ButtonManager.addButton(new ActionButton(0, XboxOne.LEFT_BUMPER.getButtonNumber(), new MoveHood(0.5, 0), true));
		ButtonManager.addButton(new ActionButton(0, XboxOne.RIGHT_BUMPER.getButtonNumber(), new MoveHood(-0.5, 0), true));
		ButtonManager.addButton(new ActionButton(1, XboxOne.Y.getButtonNumber(), new MoveTurnTable(0.5), true));
		ButtonManager.addButton(new ActionButton(1, 0, new MoveTurnTable(-0.5), true));
		ButtonManager.addButton(new ActionButton(0, XboxOne.A.getButtonNumber(), new FireShooter(), true));
		ButtonManager.addButton(new ActionButton(0, XboxOne.LEFT_BUMPER.getButtonNumber(), new ManualVerticalIntake(Direction.UP, 0, IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(0, XboxOne.RIGHT_BUMPER.getButtonNumber(), new ManualVerticalIntake(Direction.DOWN, 0, IntakeSide.BACK), true));
		ButtonManager.addButton(new ActionButton(0, XboxOne.A.getButtonNumber(), new ManualVerticalIntake(Direction.DOWN, 0, IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(0, XboxOne.B.getButtonNumber(), new ManualVerticalIntake(Direction.UP, 0, IntakeSide.BACK), true));
		ButtonManager.addButton(new ActionButton(1, XboxOne.LEFT_BUMPER.getButtonNumber(), new ManualSpinIntake(Direction.FORWARD, 0, IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(1, XboxOne.RIGHT_BUMPER.getButtonNumber(), new ManualSpinIntake(Direction.BACKWARD, 0, IntakeSide.BACK), true));
		ButtonManager.addButton(new ActionButton(1, XboxOne.A.getButtonNumber(), new ManualSpinIntake(Direction.BACKWARD, 0, IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(1, XboxOne.B.getButtonNumber(), new ManualSpinIntake(Direction.FORWARD, 0, IntakeSide.BACK), true));
		ButtonManager.addButton(new ActionButton(1, XboxOne.X.getButtonNumber(), new IntakesUp(), false));
		ButtonManager.addButton(new ActionButton(0, XboxOne.A.getButtonNumber(), new ManualVerticalIntake(Direction.DOWN, 0, IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(0, XboxOne.Y.getButtonNumber(), new ManualVerticalIntake(Direction.UP, 0, IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(0, 1, new ManualSpinIntake(0.5, 0, IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(0, 1, new ManualSpinIntake(-0.5, 0, IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(1, XboxOne.A.getButtonNumber(), new TurnRelative(90), false));
		ButtonManager.addButton(new ActionButton(1, XboxOne.X.getButtonNumber(), new TurnRelative(180), false));
		ButtonManager.addButton(new ActionButton(1, XboxOne.B.getButtonNumber(), new TurnRelative(270), false));*/
	}
}

