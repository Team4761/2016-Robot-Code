package org.robockets.stronghold.robot;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PIDSource;

public class EncoderPIDSource implements PIDSource {

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// Currently ignoring
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// Currently ignoring
		return null;
	}

	@Override
	public double pidGet() {
		return RobotMap.driveEncoder.get();
	}
}
