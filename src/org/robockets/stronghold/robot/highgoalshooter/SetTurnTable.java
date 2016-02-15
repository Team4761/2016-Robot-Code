package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Set the exact position or speed of the turn table part of the high goal shooter subsystem.
 */
public class SetTurnTable extends Command {

	Double angle = null;
	Double time = null;
	Double speed = null;
	
	/**
	 * Set the angle of the turn table part of the highgoal shooter.
	 * @param angle		The angle to set the hood at.
	 */
    public SetTurnTable(double angle) {
    	requires(Robot.shooter);
    }
    
    /**
     * Set the speed of the turn table part of the highgoal shooter continuously.
     * @param rate		The speed to set the turn table at.
     */
    
    /**
     * Set the speed of the turn table part of the highgoal shooter for a specified time.
     * @param rate		The speed to set the turn table at.
     * @param time		The time to spin the motor for.
     */
    protected void initialize() {
    	if(angle!=null) { Robot.shooter.setTurnTableAngle(angle); }
    	if(time!=null) { setTimeout(time); }
    	if(speed!=null) {
    		Robot.shooter.spinTurnTable(speed);
    		Robot.shooter.disableTurnTablePID();
    	}
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        if(angle!=null) { return Robot.shooter.turnTablePidController.onTarget(); }
        if(time!=null) { return isTimedOut(); }
        return false;
    }

    protected void end() {
    	Robot.shooter.spinTurnTable(0);
    	if(speed!=null) { Robot.shooter.enableTurnTablePID(); }
    }

    protected void interrupted() {
    	end();
    }
}
