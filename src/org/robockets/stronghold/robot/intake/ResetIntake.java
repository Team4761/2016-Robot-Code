package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Set one intake to zero
 */
public class ResetIntake extends Command {

	Intake intake;
	Encoder intakeEncoder;
	
	/**
	 * Initialize the intake
	 * @param intakeSide Front or Back intake
	 */
    public ResetIntake(IntakeSide intakeSide) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	if (intakeSide == IntakeSide.FRONT) {
    		requires(Robot.intakeFront);
    		intake = Robot.intakeFront;
    		intakeEncoder = RobotMap.intakeEncoderFront;
    	} else {
    		requires(Robot.intakeBack);
    		intake = Robot.intakeBack;
    		intakeEncoder = RobotMap.intakeEncoderBack;
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	intakeEncoder.reset();
    	intake.encoderPID.reset();
    	intake.encoderPID.setSetpoint(0);
    	intake.encoderPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
