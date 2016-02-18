package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class HorizontalAlign extends Command {

	NetworkTable table;
	
	
    public HorizontalAlign() {
    	requires(Robot.shooter);
    }

    protected void initialize() {
    	table = NetworkTable.getTable("vision");
    }

    protected void execute() {
    	double pixelError = table.getNumber("horiz_offset", 3);
    	
    	SmartDashboard.putNumber("Horizontal error", pixelError);
    	
    	if (pixelError > 0) {
    		Robot.shooter.spinTurnTable(-0.1);
    	}else if (pixelError < 0) {
    		Robot.shooter.spinTurnTable(0.1);
    	}else{
    		Robot.shooter.spinTurnTable(0);
    	}
    }

    protected boolean isFinished() {
        return Math.abs(table.getNumber("horiz_offset", 3)) < 1; //< 20;
    }

    protected void end() {
    	Robot.shooter.spinTurnTable(0);
    }

    protected void interrupted() {
    }
}
