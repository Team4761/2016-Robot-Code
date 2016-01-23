package org.robockets.stronghold.robot.highgoalshooter;

import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the high goal shooter mechanism including the turn table, the rollers, the shooting rollers, and the hood.
 */
public class HighGoalShooter extends Subsystem {

    public void initDefaultCommand() {
    }
    
    
    /**
     * Roll the Jeff rollers (the motors that guide the ball into the firing mechanism) at a desired speed.
     * @param speed 	The speed to set both Jeff rollers at.
     */
    public void spinJeffRollers(double speed) {
    	RobotMap.jeffRoller1.set(speed);
    	RobotMap.jeffRoller2.set(speed);
    }
    
    /**
     * Roll the shooting wheel to fire the cannonball at a desired speed.
     * @param speed 	The speed to set the shooting mechanism at.
     */
    public void spinShootingWheel(double speed) {
    	RobotMap.shootingWheelMotor.set(speed);
    }
}

