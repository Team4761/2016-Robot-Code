package org.robockets.stronghold.robot.flipper;

import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The part of the shooter pushing balls into the shooting wheels.
 */
public class Flipper extends Subsystem {
    
	public void setAngle(double speed) {
    	//RobotMap.shootingFlipper.set(speed);
		RobotMap.shootingFlipper.set(SmartDashboard.getNumber("New flipper Angle"));
	}
    	
    public void initDefaultCommand() {
    }
}

