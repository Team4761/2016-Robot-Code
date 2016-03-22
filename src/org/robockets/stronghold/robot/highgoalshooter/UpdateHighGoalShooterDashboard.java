package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;
import org.robockets.stronghold.robot.flipper.FireShooter;
import org.robockets.stronghold.robot.turntable.MoveTurnTable;
import org.robockets.stronghold.robot.commands.MeasureLatency;
import org.robockets.stronghold.robot.commands.SetPID;
import org.robockets.stronghold.robot.commands.VisionHorizontalTest;

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
    	//visionTable = NetworkTable.getTable("vision");
    	
    	/*SmartDashboard.putData("Move Servo", new FireShooter());
    	SmartDashboard.putNumber("pid error", 0);*/
    	SmartDashboard.putData("Vision test case", new VisionHorizontalTest());
    	SmartDashboard.putData("Reset Turn Table", new MoveTurnTable(0));
    	SmartDashboard.putData("Turn table right", new MoveTurnTable(20, 0));
    	SmartDashboard.putData("Turn table left", new MoveTurnTable(-20, 0));
    	SmartDashboard.putData("Set PID", new SetPID("turntable", Robot.turntable.pidController));
    	SmartDashboard.putData("Measure latency", new MeasureLatency());
    	
    }

    protected void execute() {
    	/*SmartDashboard.putNumber("Encoders Setpoint", Robot.driveTrain.encodersPID.getSetpoint());
    	SmartDashboard.putNumber("Encoders Offset", Robot.driveTrain.getEncodersOffset());
    	SmartDashboard.putBoolean("Front Breakbeam", RobotMap.frontBB.get());
    	SmartDashboard.putNumber("Turn table angle", Robot.turntable.getAngle());
    	SmartDashboard.putNumber("Turn table encoder", RobotMap.turnTableEncoder.get());
    	SmartDashboard.putNumber("Hood angle", Robot.hood.getAngle());
    	SmartDashboard.putNumber("Spin RPM", Robot.shootingWheel.getSpeed());
    	SmartDashboard.putNumber("Encoders Offset", Robot.driveTrain.getEncodersOffset());
    	SmartDashboard.putNumber("Drive encoder 1", RobotMap.driveEncoder.get());
    	SmartDashboard.putNumber("Drive encoder 2", RobotMap.driveEncoder2.get());
    	SmartDashboard.putNumber("Front Intake angle", RobotMap.intakeEncoderFront.get() / Robot.intakeFront.COUNTS_PER_DEGREE);
    	SmartDashboard.putNumber("Yaw", RobotMap.navX.getYaw());
    	
    	SmartDashboard.putNumber("Lock", visionTable.getNumber("can_see_target", 0));*/
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
