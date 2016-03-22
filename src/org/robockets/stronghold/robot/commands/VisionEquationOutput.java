package org.robockets.stronghold.robot.commands;

import org.robockets.stronghold.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 */
public class VisionEquationOutput extends Command {

	double constant;
	
    public VisionEquationOutput(double constant) {
    	requires(Robot.turntable);
    	this.constant = constant;
    }

    protected void initialize() {
    }

    protected void execute() {
    	System.out.println(Robot.turntable.getAngle() * constant);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
