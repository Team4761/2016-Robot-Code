package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Spin the ball out until there is no ball anymore
 */
public class SpitOutBallUntilBreakBeamLost extends Command {

	Intake intake;
	DigitalInput breakBeam;
	
	boolean ballIn;
	
    public SpitOutBallUntilBreakBeamLost(IntakeSide intakeSide) {
    	if (intakeSide == IntakeSide.FRONT) {
			requires(Robot.intakeFront);
			intake = Robot.intakeFront;
			breakBeam = RobotMap.frontBB;
		} else {
			requires(Robot.intakeBack);
			intake = Robot.intakeBack;
			breakBeam = RobotMap.backBB;
		}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (breakBeam.get()) {
    		ballIn = true;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (ballIn) {
    		intake.spinIn();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (!breakBeam.get()) {
    		return true;
    	}
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	intake.stopIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
