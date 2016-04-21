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
    	//controller.disable();
    	
    	SmartDashboard.putNumber(name + " p", SmartDashboard.getNumber(name + " p", controller.getP() * 1000.0));
    	SmartDashboard.putNumber(name + " i", SmartDashboard.getNumber(name + " i", controller.getI() * 1000.0));
    	SmartDashboard.putNumber(name + " d", SmartDashboard.getNumber(name + " d", controller.getD() * 1000.0));
    }

    protected void execute() {
    	p = SmartDashboard.getNumber(name + " p", controller.getP()) / 1000.0;
    	i = SmartDashboard.getNumber(name + " i", controller.getI()) / 1000.0;
    	d = SmartDashboard.getNumber(name + " d", controller.getD()) / 1000.0;
    	controller.setPID(p, i, d);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	//controller.enable();
    }

    protected void interrupted() {
    	end();
    }
}
