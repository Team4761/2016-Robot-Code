package org.robockets.stronghold.robot.highgoalshooter;

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
    	requires(Robot.shooter);
    	this.angle = angle;
    }
    
    /**
     * Set the speed of the turn table part of the highgoal shooter for a specified time.
     * @param rate		The speed to set the turn table at.
     * @param time		The time to spin the motor for. Set at 0 for continuos.
     */
    public MoveTurnTable(double rate, double time) {
    	requires(Robot.shooter);
    	speed = rate;
    	if (time != 0) { this.time = time; }
    }
    
    protected void initialize() {
    	if (angle != null) { Robot.shooter.setTurnTableAngle(angle); }
    	if (time != null) { setTimeout(time); }
    	if (speed != null) {
    		Robot.shooter.disableTurnTablePID();
    		Robot.shooter.spinTurnTable(speed);
    	}
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        if (angle != null) { return Robot.shooter.turnTablePidController.onTarget(); }
        if (time != null) { return isTimedOut(); }
        return false;
        
    }
    protected void end() {
    	Robot.shooter.spinTurnTable(0);
    	if(speed!=null) { System.out.println("Test"); Robot.shooter.enableTurnTablePID(); }
    }

    protected void interrupted() {
    	end();
    }
}
