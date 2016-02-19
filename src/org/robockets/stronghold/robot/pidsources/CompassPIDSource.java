package org.robockets.stronghold.robot.pidsources;


import org.robockets.stronghold.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class CompassPIDSource implements PIDSource {

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
		return RobotMap.navX.getCompassHeading();
	}

}
