package org.robockets.stronghold.robot.pidsources;

import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class DualEncoderPIDSource implements PIDSource {

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		//return (-RobotMap.driveEncoder.get() - RobotMap.driveEncoder2.get());
    	return -RobotMap.driveEncoder.get() - ((RobotMap.driveEncoder2.get() / 360.0) * 250.0);
	}

	@Override
	public void setPIDSourceType(PIDSourceType arg0) {
		
	}
	
}
