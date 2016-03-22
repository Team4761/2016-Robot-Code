package org.robockets.stronghold.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetPID extends Command {

	PIDController controller;
	String name;
	
	double p;
	double i;
	double d;
	
    public SetPID(String name, PIDController controller) {
    	this.controller = controller;
    	this.name = name;
    }

    public SetPID(String name, PIDController controller, double P, double I, double D) {
    	this.controller = controller;
    	this.name = name;
    }
    
    protected void initialize() {
    	controller.disable();
    	
    	SmartDashboard.putNumber(name + " p", SmartDashboard.getNumber(name + " p", controller.getP()));
    	SmartDashboard.putNumber(name + " i", SmartDashboard.getNumber(name + " i", controller.getI()));
    	SmartDashboard.putNumber(name + " d", SmartDashboard.getNumber(name + " d", controller.getD()));
    }

    protected void execute() {
    	p = SmartDashboard.getNumber(name + " d", controller.getP());
    	i = SmartDashboard.getNumber(name + " d", controller.getI());
    	d = SmartDashboard.getNumber(name + " d", controller.getD());
    	controller.setPID(p, i, d);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	controller.enable();
    }

    protected void interrupted() {
    	end();
    }
}
