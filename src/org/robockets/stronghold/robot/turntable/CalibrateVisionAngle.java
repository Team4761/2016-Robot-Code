package org.robockets.stronghold.robot.turntable;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CalibrateVisionAngle extends CommandGroup {
	
    public  CalibrateVisionAngle() {
    	addSequential(new MoveTurnTable(Robot.turntable.CALIBRATION_START));
    	
        for (double currentAngle = Robot.turntable.CALIBRATION_START; currentAngle < -Robot.turntable.CALIBRATION_START; currentAngle += Robot.turntable.CALIBRATION_INCREMENT) {
        	addSequential(new MoveTurnTable(currentAngle));
        	addSequential(new WaitCommand(0.5));
        	addSequential(new CompareVisionAngle());
        }
    }
}
