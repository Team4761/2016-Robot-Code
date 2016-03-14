package org.robockets.stronghold.robot.highgoalshooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class UpdateHighGoalShooterDashboard extends Command {

	NetworkTable table;
	
    public UpdateHighGoalShooterDashboard() {
    }

    protected void initialize() {
    	/*SmartDashboard.putData("Horizontal Align", new HorizontalAlign(true));
    	SmartDashboard.putData("Vertical Align", new VerticalAlign(true));
    	*///SmartDashboard.putData("Spin Turn Table Left", new MoveTurnTable(-30, 0));
    	//SmartDashboard.putData("Spin Turn Table Right", new MoveTurnTable(30, 0));
    	/*SmartDashboard.putData("Free fire", new FreeFire());
    	SmartDashboard.putData("Zero hood", new MoveHood(0));
    	SmartDashboard.putData("Reset Intake Back", new ResetPID(RobotMap.intakeEncoderBack, Robot.intakeBack.encoderPID));
    	SmartDashboard.putData("Reset Turntable", new ResetPID(RobotMap.turnTableEncoder, Robot.shooter.turnTablePidController));
    	SmartDashboard.putData("Reset Hood", new ResetPID(RobotMap.hoodEncoder, Robot.shooter.hoodPidController));
    	SmartDashboard.putData("Shovel some fries", new ChevalDeFrise(IntakeSide.FRONT));
    	SmartDashboard.putData("Set Encoders Angle 90", new TurnRelative(90));
    	SmartDashboard.putData("Set Encoders Angle 0", new TurnRelative(0));
    	SmartDashboard.putData("Intake 'Boulder'", new IntakeBall(IntakeSide.FRONT));
    	SmartDashboard.putData("Give Ball To Shooter", new GiveBallToShooter(IntakeSide.FRONT));
    	SmartDashboard.putData("Limbo", new Limbo());
    	SmartDashboard.putData("Drive Position", new DrivePosition());
    	SmartDashboard.putData("Fire Shooter", new FireShooter());
    	SmartDashboard.putData("Move Hood 0", new MoveHood(0));
    	SmartDashboard.putData("Stop Wheels", new MoveShootingWheel(0));*/
    	//SmartDashboard.putData("Track Button", new Track());
    	//SmartDashboard.putData("Flip flipper", new FireShooter());
    	SmartDashboard.putData("Move Servo", new FireShooter());
    	SmartDashboard.putNumber("pid error", 0);
    }

    protected void execute() {
    	/*SmartDashboard.putNumber("Encoders Setpoint", Robot.driveTrain.encodersPID.getSetpoint());
    	SmartDashboard.putNumber("Encoders Offset", Robot.driveTrain.getEncodersOffset());
    	SmartDashboard.putBoolean("Front Breakbeam", RobotMap.frontBB.get());
    	SmartDashboard.putNumber("Turn table angle", new EncoderPIDSource(RobotMap.turnTableEncoder, 0.16096579, PIDSourceType.kDisplacement).pidGet());
    	SmartDashboard.putNumber("Turn table encoder", RobotMap.turnTableEncoder.get());
    	SmartDashboard.putNumber("Hood angle", Robot.shooter.getHoodAngle());
    	SmartDashboard.putNumber("Spin RPM", Robot.shooter.getShootingWheelSpeed());
    	SmartDashboard.putNumber("Encoders Offset", Robot.driveTrain.getEncodersOffset());
    	SmartDashboard.putNumber("Drive encoder 1", RobotMap.driveEncoder.get());
    	SmartDashboard.putNumber("Drive encoder 2", RobotMap.driveEncoder2.get());
    	SmartDashboard.putNumber("Front Intake angle", RobotMap.intakeEncoderFront.get() / Robot.intakeFront.COUNTS_PER_DEGREE);
    	SmartDashboard.putNumber("Yaw", RobotMap.navX.getYaw());
    	
    	table = NetworkTable.getTable("vision");
    	
    	SmartDashboard.putNumber("Lock", table.getNumber("can_see_target", 0));*/
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
