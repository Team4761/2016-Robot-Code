package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CheckIntakeBreakBeam extends Command {

	IntakeSpinners intake;
	DigitalInput breakBeam;
	
	boolean spinIn = false;
	boolean haveBall = false;
	boolean timeout = false;
	double time;
	
	/**
	 * 
	 * @param intakeSide The intake that you wish to manipulate.
	 * @param intaking Do you want to use the break beam to aid in intaking(true) or spitting out(false)?
	 * @param lowGoal Are you shooting for lowgoal?
	 */
    public CheckIntakeBreakBeam(IntakeSide intakeSide, boolean spinIn, boolean haveBall, double time) {
    	if (intakeSide == IntakeSide.FRONT) {
			requires(Robot.intakeSpinnersFront);
			intake = Robot.intakeSpinnersFront;
			breakBeam = RobotMap.frontBB;
		} else {
			requires(Robot.intakeSpinnersBack);
			intake = Robot.intakeSpinnersBack;
			breakBeam = RobotMap.backBB;
		}
    	
    	this.spinIn = spinIn;
    	this.haveBall = haveBall;
    	this.time = time;
    	this.timeout = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Check Intake Breakbeam init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (spinIn) {
    		intake.spinIn();
    	} else {
    		intake.spinOut();
    	}
    	
    	if (!timeout) {
	    	if (haveBall) {
		    	if (!breakBeam.get()) {
		        	setTimeout(time);
		        	timeout = true;
		    	}
	    	} else {
	    		if (breakBeam.get()) {
		        	setTimeout(time);
		        	timeout = true;
		    	}
	    	}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println(breakBeam.get());
    	System.out.println("isTimedOut: " + isTimedOut());
    	System.out.println("timeout: " + timeout);
    	if (haveBall) {
    		return !breakBeam.get() && isTimedOut() && timeout;
    	} else {
    		return breakBeam.get() && isTimedOut() && timeout;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	intake.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
