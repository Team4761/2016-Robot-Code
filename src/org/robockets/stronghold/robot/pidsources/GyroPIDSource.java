package org.robockets.stronghold.robot.pidsources;


import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class GyroPIDSource implements PIDSource {

	@Override
	public void setPIDSourceType(PIDSourceType pidSource){
		RobotMap.navX.setPIDSourceType(pidSource);
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return RobotMap.navX.getAccumulatedYaw();
		
	}
}
