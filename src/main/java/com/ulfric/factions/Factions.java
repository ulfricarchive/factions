package com.ulfric.factions;

import com.ulfric.factions.command.EntityResolver;
import com.ulfric.factions.command.FactionsCommand;
import com.ulfric.factions.command.FactionsCreateCommand;
import com.ulfric.plugin.Plugin;

public class Factions extends Plugin {

	public Factions() {
		install(EntityResolver.class);

		install(FactionsCommand.class);
		install(FactionsCreateCommand.class);
	}

}