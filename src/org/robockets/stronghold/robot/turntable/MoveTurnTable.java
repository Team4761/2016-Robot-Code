package org.robockets.stronghold.robot.turntable;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Set the exact position or speed of the turn table part of the high goal shooter subsystem.
 */
public class MoveTurnTable extends Command {

	Double angle = null;
	Double time = null;
	Double speed = null;
	
	/**
	 * Set the angle of the turn table part of the highgoal shooter.
	 * @param angle		The angle to set the hood at.
	 */
    public MoveTurnTable(double angle) {
    	requires(Robot.turntable);
    	this.angle = angle;
//    	/this.angle = angle + ;
    }
    
    /**
     * Set the speed of the turn table part of the highgoal shooter for a specified time.
     * @param rate		The speed to set the turn table at (degrees/second).
     * @param time		The time to spin the motor for. Set at 0 for continuous.
     */
    public MoveTurnTable(double rate, double time) {
    	requires(Robot.turntable);
    	speed = rate * 0.02; // Speed is applied every 20 milliseconds and therefore should be divided by 50.
    	if (time != 0) { this.time = time; }
    }
    
    protected void initialize() {
    	if (angle != null) { Robot.turntable.setAngle(angle); }
    	if (time != null) { setTimeout(time); }
    }

    protected void execute() {
    	if (speed != null) {
    		Robot.turntable.setAngle(Robot.turntable.getSetpoint() + speed);
    	}
    	
    	//System.out.println(Robot.turntable.pidController.get());
    }

    protected boolean isFinished() {
        if (angle != null) { return Robot.turntable.onTarget(); }
        if (time != null) { return isTimedOut(); }
        return false;
        
    }
    protected void end() {
    	Robot.turntable.setAngle(Robot.turntable.getSetpoint());
    	//if(speed != null) { Robot.shooter.enableTurnTablePID(); }
    }

    protected void interrupted() {
    	end();
    }
}
