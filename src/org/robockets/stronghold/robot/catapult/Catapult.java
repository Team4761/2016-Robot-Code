package org.robockets.stronghold.robot.catapult;

import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Catapult extends Subsystem {
	public final boolean FIRE_BOOL = true;
	public final boolean CLOSE_BOOL = false;
	public final double FIRE_WAIT = 0.5;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void open() {
    	RobotMap.catapultSolenoid.set(FIRE_BOOL);
    }
    
    public void close() {
    	RobotMap.catapultSolenoid.set(CLOSE_BOOL);
    }
    
    public void set(boolean on) {
    	RobotMap.catapultSolenoid.set(on);
    }
}

