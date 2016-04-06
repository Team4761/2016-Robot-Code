package org.robockets.stronghold.robot.pidsources;

import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class TalonPIDSource implements PIDSource {

	double factor;
	
	public TalonPIDSource(double factor) {
		this.factor = factor;
	}
	
	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kRate;
	}

	@Override
	public double pidGet() { 
		return RobotMap.shootingWheelMotor.getEncVelocity() * factor;
	}

	@Override
	public void setPIDSourceType(PIDSourceType arg0) {

	}

}

