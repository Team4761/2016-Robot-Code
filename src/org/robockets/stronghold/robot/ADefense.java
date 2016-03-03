package org.robockets.stronghold.robot;
import edu.wpi.first.wpilibj.command.Command;
public enum ADefense
{
	
	public PORTCULLIS ("AutoPortcullisForward", "AutoPortcullisBackward"),
	public CHEVAL_DE_FRISE ("AutoChevalDeFriseForward", "AutoChevalDeFriseBackward"),
	public RAMPARTS ("AutoRampartsForward", "AutoRampartsBackward"),
	public MOAT ("AutoMoatForward", "AutoMoatBackward"),
	public DRAWBRIDGE ("AutoDrawbridgeForward", "AutoDrawbridgeBackward"),
	public SALLY_PORT ("AutoSallyPortForward", "AutoSallyPortBackward"),
	public ROCK_WALL ("AutoRockWallForward", "AutoRockWallBackward"),
	public ROUGH_TERRAIN ("AutoRoughTerrainForward", "AutoRoughTerrainBackward"),
	public LOW_BAR	("AutoLowBarForward", "AutoLowBarBackward");
	
	final Command forwardCommand, backwardCommand;
	
	ADefense (String forwardCommandClassName, String backwardCommandClassName)
	{
		forwardCommand = getCommandInstanceByName(forwardCommandClassName);
		backwardCommand = getCommandInstanceByName(backwardCommandClassName);
	}
	
	Command getCommandInstanceByName(String str)
	{
		return (Command)Class.forName("org.robockets.stronghold.robot.commands." + str).getConstructor(null).newInstance(new Object[0]);
	}
}

