package org.robockets.stronghold.robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class HoodPIDSource implements PIDSource {
	public final double COUNTS_PER_DEGREE_HOOD = 7.3111;
	
	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return RobotMap.hoodEncoder.get() * COUNTS_PER_DEGREE_HOOD;
	}

	@Override
	public void setPIDSourceType(PIDSourceType arg0) {
		
	}

}
