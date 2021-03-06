package org.robockets.stronghold.robot;

import org.robockets.buttonmanager.ButtonManager;
import org.robockets.buttonmanager.buttons.ActionButton;
import org.robockets.stronghold.robot.commands.DrivePosition;
import org.robockets.stronghold.robot.commands.Kill;
import org.robockets.stronghold.robot.commands.Limbo;
import org.robockets.stronghold.robot.flipper.FireShooter;
import org.robockets.stronghold.robot.highgoalshooter.AimCleatShot;
import org.robockets.stronghold.robot.highgoalshooter.AutoFire;
import org.robockets.stronghold.robot.highgoalshooter.FireCleatShot;
import org.robockets.stronghold.robot.highgoalshooter.FireSpyShot;
import org.robockets.stronghold.robot.highgoalshooter.FreeFire;
import org.robockets.stronghold.robot.highgoalshooter.PowerLowGoal;
import org.robockets.stronghold.robot.highgoalshooter.Track;
import org.robockets.stronghold.robot.highgoalshooter.UnstickBall;
import org.robockets.stronghold.robot.hood.MoveHood;
import org.robockets.stronghold.robot.hood.ResetHood;
import org.robockets.stronghold.robot.intake.ClampIntake;
import org.robockets.stronghold.robot.intake.GiveBallToShooter;
import org.robockets.stronghold.robot.intake.IntakeBall;
import org.robockets.stronghold.robot.intake.IntakeBallMinimal;
import org.robockets.stronghold.robot.intake.IntakeSide;
import org.robockets.stronghold.robot.intake.SetVerticalIntake;
import org.robockets.stronghold.robot.intake.SpinIntake;
import org.robockets.stronghold.robot.intake.SuperIntakeReset;
import org.robockets.stronghold.robot.shootingwheel.MoveShootingWheel;
import org.robockets.stronghold.robot.turntable.MoveTurnTable;

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
		
		//ButtonManager.addButton(new ActionButton(0, 1, new SetVerticalIntake(Direction.UP, 0, IntakeSide.FRONT), true)); // "A" Button on Joystick
		
		ButtonManager.addButton(new ActionButton(1, 6, new FreeFire(true), true));
		//ButtonManager.addButton(new ActionButton(1, 6, new AutoFire(0), true));
		ButtonManager.addButton(new ActionButton(1, 8, new AutoFire(-5), true));
		ButtonManager.addButton(new ActionButton(1, 11, new AutoFire(5), true));
		
		ButtonManager.addButton(new ActionButton(1, 3, new IntakeBall(IntakeSide.FRONT, Robot.intakeVerticalFront.INTAKE_POSITION), true));
		ButtonManager.addButton(new ActionButton(1, 5, new IntakeBall(IntakeSide.FRONT, Robot.intakeVerticalFront.INTAKE_POSITION + 10), true));
		ButtonManager.addButton(new ActionButton(1, 7, new IntakeBall(IntakeSide.FRONT, Robot.intakeVerticalFront.INTAKE_POSITION - 10), true));
		ButtonManager.addButton(new ActionButton(1, 4, new GiveBallToShooter(IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(1, 2, new Track(), true));
		ButtonManager.addButton(new ActionButton(2, 16, new IntakeBallMinimal(IntakeSide.FRONT), true));
		
		ButtonManager.addButton(new ActionButton(1, 9, new FireShooter(), false));
		//ButtonManager.addButton(new ActionButton(1, 8, new ShootOnAligned(), true));
		ButtonManager.addButton(new ActionButton(1, 12, new UnstickBall(), true));
		
		ButtonManager.addButton(new ActionButton(1, 13, new PowerLowGoal(), true));
		ButtonManager.addButton(new ActionButton(1, 14, new Kill(), true));
		ButtonManager.addButton(new ActionButton(1, 17, new ResetPID(RobotMap.intakeEncoderFront, Robot.intakeVerticalFront.encoderPID), true));
		//ButtonManager.addButton(new ActionButton(1, 17, new SuperIntakeReset(), true));
		ButtonManager.addButton(new ActionButton(1, 15, new ResetHood(), true));
		ButtonManager.addButton(new ActionButton(1, 16, new ResetPID(RobotMap.turnTableEncoder, Robot.turntable.pidController), true));
		
		//ButtonManager.addButton(new ActionButton(2, 19, new LowGoal(IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(2, 19, new ClampIntake(), true));		
		ButtonManager.addButton(new ActionButton(2, 18, new AimCleatShot(Direction.LEFT), true));
		ButtonManager.addButton(new ActionButton(2, 17, new AimCleatShot(Direction.CENTER), true));
		ButtonManager.addButton(new ActionButton(2, 16, new IntakeBallMinimal(IntakeSide.FRONT), true));
		//ButtonManager.addButton(new ActionButton(2, 3, new FireSpyShot(), true));
		ButtonManager.addButton(new ActionButton(1, 10, new MoveShootingWheel(1600), true));
		ButtonManager.addButton(new ActionButton(2, 4, new FireCleatShot(), true));
		ButtonManager.addButton(new ActionButton(2, 7, new DrivePosition(true), true));
		ButtonManager.addButton(new ActionButton(2, 6, new Limbo(), true));
		ButtonManager.addButton(new ActionButton(2, 15, new MoveHood(50, 0), true));
		ButtonManager.addButton(new ActionButton(2, 14, new MoveHood(-50, 0), true));
		ButtonManager.addButton(new ActionButton(2, 12, new MoveTurnTable(-40, 0), true));
		ButtonManager.addButton(new ActionButton(2, 13, new MoveTurnTable(40, 0), true));
		ButtonManager.addButton(new ActionButton(2, 10, new SetVerticalIntake(Direction.DOWN, 0, IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(2, 11, new SetVerticalIntake(Direction.UP, 0, IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(2, 9, new SpinIntake(0.6, 0, IntakeSide.FRONT), true));
		ButtonManager.addButton(new ActionButton(2, 8, new SpinIntake(-0.6, 0, IntakeSide.FRONT), true));
	}
}

