package org.robockets.stronghold.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Tune a PID with values from SmartDashboard.
 */
public class SetPID extends Command {

	PIDController controller;
	String name;
	
    public SetPID(PIDController jack, String name) {
    	controller = jack;
    	this.name = name + "PIDController";
    }

    protected void initialize() {
    	SmartDashboard.putNumber(name+" P", SmartDashboard.getNumber(name+" P", 1));
    	SmartDashboard.putNumber(name+" I", SmartDashboard.getNumber(name+" I", 1));
    	SmartDashboard.putNumber(name+" D", SmartDashboard.getNumber(name+" D", 0));
    }

    protected void execute() {
    	controller.setPID(SmartDashboard.getNumber(name+" P", 1), SmartDashboard.getNumber(name+" I", 1), SmartDashboard.getNumber(name+" D", 0));
    	//System.out.println("P " + controller.getP() + "I " + controller.getI() + "D " + controller.getD());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
