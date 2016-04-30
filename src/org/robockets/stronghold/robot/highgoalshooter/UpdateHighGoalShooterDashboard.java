package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.flipper.FireShooter;
import org.robockets.stronghold.robot.hood.MoveHood;
import org.robockets.stronghold.robot.shootingwheel.MoveShootingWheel;
import org.robockets.stronghold.robot.shootingwheel.RPMAlign;
import org.robockets.stronghold.robot.turntable.CalibrateVisionAngle;
import org.robockets.stronghold.robot.turntable.HorizontalAlign;
import org.robockets.stronghold.robot.turntable.MoveTurnTable;
import org.robockets.stronghold.robot.commands.SetPID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateHighGoalShooterDashboard extends Command {

	NetworkTable visionTable;
	NetworkTable controlTable;
	
    public UpdateHighGoalShooterDashboard() {
    }

    protected void initialize() {    	
    	visionTable = NetworkTable.getTable("vision");
    	controlTable = NetworkTable.getTable("control_daemon");
    	
    	SmartDashboard.putData("Horizontal align", new HorizontalAlign(true));
    	SmartDashboard.putData("Vertical align", new VerticalAlign(true));
    	SmartDashboard.putData("Free fire (normal)", new FreeFire(false));
    	SmartDashboard.putData("Free fire (Menzie)", new FreeFire(true));
    	SmartDashboard.putNumber("Bonus Angle", 0);
    	
    	SmartDashboard.putData("Unstick Ball", new UnstickBall());
    	SmartDashboard.putData("Calibrate vision", new CalibrateVisionAngle());
    }

    protected void execute() {
		Robot.liveCounter += 0.001;
    	SmartDashboard.putNumber("Live Counter", Robot.liveCounter);
    	SmartDashboard.putNumber("Vision Last Updated", controlTable.getNumber("last_updated", 0));
    	
    	SmartDashboard.putNumber("Drive Encoder Left", Robot.driveTrain.getLeftDistanceInInches());
    	SmartDashboard.putNumber("Drive Encoder Right", Robot.driveTrain.getRightDistanceInInches());
    	
    	SmartDashboard.putNumber("Hood angle", Robot.hood.getAngle());
    	SmartDashboard.putNumber("Turn table angle", Robot.turntable.getAngle());
    	SmartDashboard.putNumber("Spin RPM", Robot.shootingWheel.getSpeed());
    	
    	SmartDashboard.putBoolean("Intake Limit Switch", RobotMap.intakeFrontUp.get());

    	/*SmartDashboard.putBoolean("Front Breakbeam", RobotMap.frontBB.get());
    	SmartDashboard.putNumber("Turn table encoder", RobotMap.turnTableEncoder.get());
    	SmartDashboard.putNumber("Left Setpoint", Robot.driveTrain.getLeftDistanceSetpointInInches());
    	SmartDashboard.putNumber("Right Side Setpoint", Robot.driveTrain.getRightDistanceSetpointInInches());
    	SmartDashboard.putNumber("Front Intake angle", RobotMap.intakeEncoderFront.get() / Robot.intakeVerticalFront.COUNTS_PER_DEGREE);
    	SmartDashboard.putNumber("Yaw", RobotMap.navX.getYaw());
    	SmartDashboard.putNumber("Lock", visionTable.getNumber("can_see_target", 0));*/
    	SmartDashboard.putNumber("pid error", Robot.turntable.getSetpoint() - Robot.turntable.getAngle());
		//SmartDashboard.putNumber("intake angle", Robot.intakeVerticalFront.getIntakeAngle());
    	SmartDashboard.putNumber("distance", visionTable.getNumber("distance_guess", 0));
    	SmartDashboard.putBoolean("Can see target", visionTable.getNumber("can_see_target", 0) == 1);
    	SmartDashboard.putBoolean("Vision Conneected", visionTable.isConnected());
		SmartDashboard.putNumber("Simon's Angle", visionTable.getNumber("horiz_offset", 0));
    	
    	SmartDashboard.putBoolean("Not at limit", !(Robot.hood.atLimit || Robot.turntable.atLimit));    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
