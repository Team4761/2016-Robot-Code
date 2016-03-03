import edu.wpi.first.wpilibj.command.Command;
enum Defense
{
	
	PORTCULLIS ("AutoPortcullisForward", "AutoPortcullisBackward"),
	CHEVAL_DE_FRISE ("AutoChevalDeFriseForward", "AutoChevalDeFriseBackward"),
	RAMPARTS ("AutoRampartsForward", "AutoRampartsBackward"),
	MOAT ("AutoMoatForward", "AutoMoatBackward"),
	DRAWBRIDGE ("AutoDrawbridgeForward", "AutoDrawbridgeBackward"),
	SALLY_PORT ("AutoSallyPortForward", "AutoSallyPortBackward"),
	ROCK_WALL ("AutoRockWallForward", "AutoRockWallBackward"),
	ROUGH_TERRAIN ("AutoRoughTerrainForward", "AutoRoughTerrainBackward"),
	LOW_BAR	("AutoLowBarForward", "AutoLowBarBackward");
	
	final Command forwardCommand, backwardCommand;
	
	Defense (String forwardCommandClassName, String backwardCommandClassName)
	{
		forwardCommand = getCommandInstanceByName(forwardCommandClassName);
		backwardCommand = getCommandInstanceByName(backwardCommandClassName);
	}
	
	Command getCommandInstanceByName(String str)
	{
		return (Command)Class.forName("org.robockets.stronghold.robot.commands." + str).getConstructor(null).newInstance(new Object[0]);
	}
}

