package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class HorizontalAlign extends Command {

	NetworkTable table;
	boolean stop;
	
    public HorizontalAlign(boolean stop) {
    	requires(Robot.shooter);
    	this.stop = stop;
    	if (stop){
    		requires(Robot.driveTrain); // Make the drive train stop for a bit here.
    	}
    }

    protected void initialize() {
    	table = NetworkTable.getTable("vision");
    	if (stop) {
    		double pixelError = table.getNumber("horiz_offset", 3);
    		double distance = table.getNumber("distance", 10);
    		double width = table.getNumber("width", 100);
    		double actualWidthin = 20;
    		Robot.shooter.setTurnTableAngle(Robot.shooter.turnTablePidController.getSetpoint() * Math.asin(pixelError * (actualWidthin / width) / distance));
    	} else {
    		Robot.shooter.disableTurnTablePID();
    	}
    }

    protected void execute() {
    	double pixelError = table.getNumber("horiz_offset", 3);
		
    	if(!stop) {
    		double factor = 0.5;
    		if (pixelError > 0) { factor *= -1; }
    		if (Math.abs(pixelError) >= 40) { factor *= 0.5; } 
    		Robot.shooter.spinTurnTable(factor);
    	}
    }

    protected boolean isFinished() {
    	if (!stop) { return Math.abs(table.getNumber("horiz_offset", 21)) < 20; }
    	return Robot.shooter.turnTablePidController.onTarget();
    }

    protected void end() {
    	Robot.shooter.spinTurnTable(0);
    	Robot.shooter.enableTurnTablePID();
    }

    protected void interrupted() {
    	end();
    }
}
