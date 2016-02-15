package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A Command to spin the intake motor forward or backward
 */
public class ManualVerticalIntake extends Command {
	
	public Direction direction; //Object for the Direction enum
	
	int time; //Used to set timeout
	
	double speed; //Used to set speed manually
	
	boolean encoderPID = false;
	
	/**
	 * Initalizes some variables
	 * 
	 * @param direction  Used to initalize Direction enum
	 * @param time  Takes input for time
	 * */
    public ManualVerticalIntake(Direction direction, int time) {
    	requires(Robot.intake);
    	this.direction = direction;
    	this.time = time;
    }
    
    public ManualVerticalIntake(double speed, int time) {
    	requires(Robot.intake);
    	this.speed = speed;
    	this.direction = Direction.MANUAL;
    	this.time = time;
    }
    
    public ManualVerticalIntake() {
    	requires(Robot.intake);
    	this.speed = 0.5;
    	this.direction = Direction.MANUAL;
    	this.time = 0;
    	SmartDashboard.putNumber("Intake P", Robot.intake.encoderPID.getP());
    	SmartDashboard.putNumber("Intake I", Robot.intake.encoderPID.getI());
    	SmartDashboard.putNumber("Intake D", Robot.intake.encoderPID.getD());
    	SmartDashboard.putNumber("Intake Start", 0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.intake.encoderPID.setPID(SmartDashboard.getNumber("Intake P"), SmartDashboard.getNumber("Intake I"), SmartDashboard.getNumber("Intake D"));
    	
    	if (SmartDashboard.getNumber("Intake Start") == 1) {
    		Robot.intake.encoderPID.enable();
    		encoderPID = true;
    	} else {
    		if (encoderPID) {
    			encoderPID = false;
    			Robot.intake.encoderPID.disable();
    		}
    		
    	}
    	
    	if (direction == Direction.UP) {
    		Robot.intake.moveUp();
    	} else if (direction == Direction.DOWN){
    		Robot.intake.moveDown();
    	} else if (direction == Direction.MANUAL){
    		Robot.intake.move(speed);
    	} else {
    		Robot.intake.stopVertical();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (time == 0) {
    		return false;
    	} else {
    		return isTimedOut();
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.stopVertical();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
