package org.robockets.stronghold.robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class EncoderPIDSource implements PIDSource {
	
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
		return RobotMap.navX.getAccumulatedYaw();//TEMP^^^
		
	}
	
}
