package org.robockets.stronghold.robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class DualEncoderPIDSource implements PIDSource {

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return (-RobotMap.driveEncoder.get() - RobotMap.driveEncoder2.get());
	}

	@Override
	public void setPIDSourceType(PIDSourceType arg0) {
		
	}
	
}
