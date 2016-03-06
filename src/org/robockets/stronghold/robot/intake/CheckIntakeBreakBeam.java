package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CheckIntakeBreakBeam extends Command {

	Intake intake;
	DigitalInput breakBeam;
	
	boolean ballIn;
	boolean intaking = false;
	boolean lowGoal = false;
	
	/**
	 * 
	 * @param intakeSide The intake that you wish to manipulate.
	 * @param intaking Do you want to use the break beam to aid in intaking(true) or spitting out(false)?
	 * @param lowGoal Are you shooting for lowgoal?
	 */
    public CheckIntakeBreakBeam(IntakeSide intakeSide, boolean intaking, boolean lowGoal) {
    	if (intakeSide == IntakeSide.FRONT) {
			requires(Robot.intakeFront);
			intake = Robot.intakeFront;
			breakBeam = RobotMap.frontBB;
		} else {
			requires(Robot.intakeBack);
			intake = Robot.intakeBack;
			breakBeam = RobotMap.backBB;
		}
    	
    	if (intaking) { this.intaking = true; }
    	if (lowGoal) { this.lowGoal = true; }
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (breakBeam.get()) {
    		ballIn = true;
    	} else {
    		ballIn = false;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (intaking && !ballIn) { //Don't have the ball and want to pick it up
    		intake.spinIn();
    	} else {
    		ballIn = true;
    		setTimeout(0.75);
    	}
    	
    	if (!intaking && ballIn && !lowGoal) { // Have the ball and want to give to shooter
    		intake.spinIn();
    	} else {
    		intake.spinOut();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (intaking && !ballIn) { return ballIn && isTimedOut(); }
    	if (!intaking && ballIn && !lowGoal) { return !breakBeam.get(); }
    	
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
