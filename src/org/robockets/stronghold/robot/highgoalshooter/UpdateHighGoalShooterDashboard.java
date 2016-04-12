package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.flipper.FireShooter;
import org.robockets.stronghold.robot.shootingwheel.RPMAlign;
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
	
    public UpdateHighGoalShooterDashboard() {
    }

    protected void initialize() {
    	visionTable = NetworkTable.getTable("vision");
    	
    	/*SmartDashboard.putData("Move Servo", new FireShooter());
    	SmartDashboard.putNumber("pid error", 0);*/
    	SmartDashboard.putData("Reset Turn Table", new MoveTurnTable(0));
    	SmartDashboard.putData("Turn table right", new MoveTurnTable(20, 0));
    	SmartDashboard.putData("Turn table left", new MoveTurnTable(-20, 0));
    	SmartDashboard.putData("Move turn table to 30 degrees", new MoveTurnTable(30));
    	SmartDashboard.putData("Horizontal align", new HorizontalAlign(true));
    	SmartDashboard.putData("RPM align", new RPMAlign(true));
    	SmartDashboard.putData("Vertical align", new VerticalAlign(true));
    	SmartDashboard.putData("Shoot", new FireShooter());
    	SmartDashboard.putData("Free fire", new FreeFire());
    	SmartDashboard.putNumber("Extra", 200);
    	SmartDashboard.putNumber("Bonus Angle", 1);
    	
    	SmartDashboard.putNumber("Hood P", 0);
    	SmartDashboard.putNumber("Hood I", 0);
    	SmartDashboard.putNumber("Hood D", 0);
    	SmartDashboard.putNumber("New Hood Angle", 0);
    	SmartDashboard.putData("Set Hood PID", new SetPID("hood", Robot.hood.pidController));
    	
    	SmartDashboard.putNumber("RPM P", 0);
    	SmartDashboard.putNumber("RPM I", 0);
    	SmartDashboard.putNumber("RPM D", 0);
    	SmartDashboard.putNumber("New RPM", 0);
    	SmartDashboard.putData("Set RPM PID", new SetPID("rpm", Robot.shootingWheel.shootingWheelPIDController));
    	
    	SmartDashboard.putNumber("Turntable P", 0);
    	SmartDashboard.putNumber("Turntable I", 0);
    	SmartDashboard.putNumber("Turntable D", 0);
    	SmartDashboard.putNumber("New Turntable", 0);
    	SmartDashboard.putData("Set Turntable PID", new SetPID("turntable", Robot.turntable.pidController));
    }

    protected void execute() {    	
    	SmartDashboard.putNumber("Drive Encoder Left", Robot.driveTrain.getLeftDistanceInInches());
    	SmartDashboard.putNumber("Drive Encoder Right", Robot.driveTrain.getRightDistanceInInches());
    	
    	SmartDashboard.putNumber("Hood angle", Robot.hood.getAngle());
    	SmartDashboard.putNumber("Turn table angle", Robot.turntable.getAngle());
    	SmartDashboard.putNumber("Spin RPM", Robot.shootingWheel.getSpeed());
    	
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
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
