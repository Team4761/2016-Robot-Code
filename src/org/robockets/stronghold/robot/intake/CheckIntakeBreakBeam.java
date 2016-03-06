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
	
	boolean spinIn = false;
	boolean haveBall = false;
	
	/**
	 * 
	 * @param intakeSide The intake that you wish to manipulate.
	 * @param intaking Do you want to use the break beam to aid in intaking(true) or spitting out(false)?
	 * @param lowGoal Are you shooting for lowgoal?
	 */
    public CheckIntakeBreakBeam(IntakeSide intakeSide, boolean spinIn, boolean haveBall) {
    	if (intakeSide == IntakeSide.FRONT) {
			requires(Robot.intakeFront);
			intake = Robot.intakeFront;
			breakBeam = RobotMap.frontBB;
		} else {
			requires(Robot.intakeBack);
			intake = Robot.intakeBack;
			breakBeam = RobotMap.backBB;
		}
    	
    	this.spinIn = spinIn;
    	this.haveBall = haveBall;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (spinIn) {
    		intake.spinIn();
    	} else {
    		intake.spinOut();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (haveBall) {
    		return !breakBeam.get();
    	} else {
    		return breakBeam.get();
    	}
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
