package org.robockets.stronghold.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class MeasureLatency extends Command {

	NetworkTable table;
	double starttime;
	int i = 0;
	
    public MeasureLatency() {
    }

    protected void initialize() {
    	table = NetworkTable.getTable("vision");
    	starttime = System.nanoTime();
    }

    protected void execute() {
    	if(table.getNumber("heartbeat", 0)==1){
    		System.out.println("i," + (System.nanoTime() - starttime));
    		starttime = System.nanoTime();
    		table.putNumber("heartbeat", 0);
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
