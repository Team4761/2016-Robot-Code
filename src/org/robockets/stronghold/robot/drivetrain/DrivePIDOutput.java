package org.robockets.stronghold.robot.drivetrain;

import edu.wpi.first.wpilibj.PIDOutput;

public class DrivePIDOutput implements PIDOutput {
	private double value = 0;
	
	@Override
	public void pidWrite(double output) {
		this.value = output;
	}
	
	public double getValue() {
		return this.value;
	}
}
