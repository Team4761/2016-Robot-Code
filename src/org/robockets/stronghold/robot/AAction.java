package org.robockets.stronghold.robot;
import edu.wpi.first.wpilibj.command.Command;
enum AAction
{
	
	SHOOT_HIGH ("AutoShootHighCommand"),
	SHOOT_FROM_CLEAT ("AutoShootFromCleatCommand"),
	SHOOT_LOW ("AutoShootLowCommand"),
	DO_NOTHING (null);
	
	final Command command;
	
	AAction (String commandClassName)
	{
		if (commandClassName != null)
			command = getCommandInstanceByName(commandClassName);
	}
	
	Command getCommandInstanceByName(String str)
	{
		return (Command)Class.forName("org.robockets.stronghold.robot.commands." + str).getConstructor(null).newInstance(new Object[0]);
	}
	
}

