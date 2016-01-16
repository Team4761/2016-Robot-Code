package org.robockets.stronghold.robot;


import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class GyroPIDSource implements PIDSource {

	@Override
	public void setPIDSourceType(PIDSourceType pidSource){
		RobotMap.navX.setPIDSourceType(pidSource);
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return RobotMap.navX.getPIDSourceType();
	}

	@Override
	public double pidGet() {
		return RobotMap.navX.getYaw();
	}

}
