package org.robockets.stronghold.robot.intake;

import org.robockets.stronghold.robot.Direction;
import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VerticalIntake extends Command {

	IntakeSide intakeSide;
	Direction direction;
	Intake intake;
	
	int setpoint;
	double speed;
	int time;
	
    public VerticalIntake(IntakeSide intakeSide, int setpoint) {
    	this.intakeSide = intakeSide;
    	this.setpoint = setpoint;
    	direction = Direction.AUTO;
    }
    
    public VerticalIntake(IntakeSide intakeSide, double speed, int time) {
    	this.intakeSide = intakeSide;
    	this.speed = speed;
    	this.time = time;
    	direction = Direction.MANUAL;
    }

    protected void initialize() {
    	if (direction == Direction.MANUAL) {
    		setTimeout(time);
    	}
    }

    protected void execute() {
    	if (intakeSide == IntakeSide.FRONT) {
    		if (direction == Direction.AUTO) {
    			Robot.intakeFront.move(setpoint);
    		} else {
    			Robot.intakeFront.move(speed);
    		}
    	} else {
    		if (direction == Direction.AUTO) {
    			Robot.intakeBack.move(setpoint);
    		} else {
    			Robot.intakeBack.move(speed);
    		}
    	}
    }

    protected boolean isFinished() {
        if (direction == Direction.AUTO) {
        	return true;
        } else {
        	return isTimedOut();
        }
    }

    protected void end() {
    	if (intakeSide == IntakeSide.FRONT) {
    		Robot.intakeFront.stopVertical();
    	} else {
    		Robot.intakeBack.stopVertical();
    	}
    }

    protected void interrupted() {
    	end();
    }
}
