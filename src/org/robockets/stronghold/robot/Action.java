enum Action
{
	
	SHOOT_HIGH ("AutoShootHighCommand"),
	SHOOT_FROM_CLEAT ("AutoShootFromCleatCommand"),
	SHOOT_LOW ("AutoShootLowCommand"),
	DO_NOTHING (null);
	
	final Command command;
	
	Action (String commandClassName)
	{
		if (commandClassName != null)
			command = getCommandInstanceByName(str);
	}
	
	Command getCommandInstanceByName(String str)
	{
		return (Command)Class.forName("org.robockets.stronghold.robot.commands." + str).getConstructor(null).newInstance(new Object[0]);
	}
	
}
