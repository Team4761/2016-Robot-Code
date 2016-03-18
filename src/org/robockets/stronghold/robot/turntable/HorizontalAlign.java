package org.robockets.stronghold.robot.turntable;

import org.robockets.stronghold.robot.Robot;
import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Align the robot horizontally with the target.
 */
public class HorizontalAlign extends Command {

	NetworkTable table;
	boolean continuous;
	boolean onTargetForReal = false;
	
	/**
	 * * @param continuous		If it should stop when on target.
	 */
    public HorizontalAlign(boolean continuous) {
    	requires(Robot.turntable);
    	this.continuous = continuous;
    }

    protected void initialize() {
    	table = NetworkTable.getTable("vision");
    	table.putNumber("heartbeat", 1);
    	onTargetForReal = false;
    }

    protected void execute() {
    	double pixelError = table.getNumber("horiz_offset", 0);
    	 SmartDashboard.putNumber("factorz", SmartDashboard.getNumber("factorz", 0.0305));
    	double factor = SmartDashboard.getNumber("factorz", 0.0305);
    	
    	double output = Robot.turntable.getAngle() + (factor * pixelError);
    	Robot.turntable.setAngle(output);
    	SmartDashboard.putNumber("output for turntable", output);
    	SmartDashboard.putNumber("pid error", Robot.turntable.getSetpoint() - RobotMap.turntablePIDSource.pidGet());
    	Robot.turntable.setAngle(output);
    			
    	if (!continuous && Robot.turntable.onTarget()) {
    		if (!continuous && Math.abs(pixelError) < 20) {
    			if (!onTargetForReal) {
    				setTimeout(1);
    			}
    		
    			onTargetForReal = true;
    		} else {
    			onTargetForReal = false;
    		}
    	}
    }
    	
    protected boolean isFinished() {    	
    	if (continuous == false) {
    		return Robot.turntable.onTarget() && isTimedOut() && onTargetForReal;
    	}
    	return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
