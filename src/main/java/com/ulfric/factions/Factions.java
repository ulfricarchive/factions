package com.ulfric.factions;

import com.ulfric.factions.command.FactionsCommand;
import com.ulfric.platform.Plugin;

public class Factions extends Plugin {

	public Factions() {
		install(FactionsCommand.class);
	}

}