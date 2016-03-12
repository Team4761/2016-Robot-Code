package org.robockets.stronghold.robot;

import org.robockets.buttonmanager.ButtonManager;
import org.robockets.buttonmanager.buttons.ActionButton;
import org.robockets.buttonmanager.joysticks.XboxOne;
import org.robockets.stronghold.robot.highgoalshooter.FireShooter;
import org.robockets.stronghold.robot.commands.DrivePosition;
import org.robockets.stronghold.robot.commands.Kill;
import org.robockets.stronghold.robot.commands.Limbo;
import org.robockets.stronghold.robot.commands.LowGoal;
import org.robockets.stronghold.robot.drivetrain.TurnRelative;
import org.robockets.stronghold.robot.highgoalshooter.AimCleatShot;
import org.robockets.stronghold.robot.highgoalshooter.AutoFire;
import org.robockets.stronghold.robot.highgoalshooter.FireCleatShot;
import org.robockets.stronghold.robot.highgoalshooter.FireSpyShot;
import org.robockets.stronghold.robot.highgoalshooter.FreeFire;
import org.robockets.stronghold.robot.highgoalshooter.HorizontalAlign;
import org.robockets.stronghold.robot.highgoalshooter.MoveHood;
import org.robockets.stronghold.robot.highgoalshooter.MoveShootingWheel;
import org.robockets.stronghold.robot.highgoalshooter.MoveTurnTable;
import org.robockets.stronghold.robot.highgoalshooter.RPMAlign;
import org.robockets.stronghold.robot.highgoalshooter.Track;
import org.robockets.stronghold.robot.highgoalshooter.VerticalAlign;
import org.robockets.stronghold.robot.intake.GiveBallToShooter;
import org.robockets.stronghold.robot.intake.IntakeBall;
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
	public static Joystick buttonBoard1 = new Joystick(1);
	public static Joystick buttonBoard2 = new Joystick(2);
	
	public OI () {
		ButtonManager.addJoystick(joystick);
		ButtonManager.addJoystick(buttonBoard1);
		ButtonManager.addJoystick(buttonBoard2);
		
		ButtonManager.addButton(new ActionButton(2, 19, new LowGoal(IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(1, 20, new AutoFire(), true));
		ButtonManager.addButton(new ActionButton(1, 3, new IntakeBall(IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(1, 4, new GiveBallToShooter(IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(1, 2, new Track(), true));
		ButtonManager.addButton(new ActionButton(1, 8, new FireShooter(), false));
		ButtonManager.addButton(new ActionButton(1, 14, new Kill(), true));
		ButtonManager.addButton(new ActionButton(1, 15, new ResetPID(RobotMap.hoodEncoder, Robot.shooter.hoodPidController), true));
		ButtonManager.addButton(new ActionButton(1, 16, new ResetPID(RobotMap.turnTableEncoder, Robot.shooter.turnTablePidController), true));
		ButtonManager.addButton(new ActionButton(1, 17, new ResetPID(RobotMap.intakeEncoderFront, Robot.intakeFront.encoderPID), true));
		
		ButtonManager.addButton(new ActionButton(2, 18, new AimCleatShot(Direction.LEFT), true));
		ButtonManager.addButton(new ActionButton(2, 17, new AimCleatShot(Direction.CENTER), true));
		ButtonManager.addButton(new ActionButton(2, 16, new AimCleatShot(Direction.RIGHT), true));
		ButtonManager.addButton(new ActionButton(2, 3, new FireSpyShot(), true));
		ButtonManager.addButton(new ActionButton(2, 4, new FireCleatShot(), true));
		ButtonManager.addButton(new ActionButton(2, 7, new DrivePosition(), true));
		ButtonManager.addButton(new ActionButton(2, 6, new Limbo(), true));
		ButtonManager.addButton(new ActionButton(2, 15, new MoveHood(25, 0), true));
		ButtonManager.addButton(new ActionButton(2, 14, new MoveHood(-25, 0), true));
		ButtonManager.addButton(new ActionButton(2, 12, new MoveTurnTable(-40, 0), true));
		ButtonManager.addButton(new ActionButton(2, 13, new MoveTurnTable(40, 0), true));
		ButtonManager.addButton(new ActionButton(2, 10, new SetVerticalIntake(Direction.DOWN, 0, IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(2, 11, new SetVerticalIntake(Direction.UP, 0, IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(2, 9, new SpinIntake(Direction.FORWARD, 0, IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(2, 8, new SpinIntake(Direction.BACKWARD, 0, IntakeSide.FRONT), true));
		
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

