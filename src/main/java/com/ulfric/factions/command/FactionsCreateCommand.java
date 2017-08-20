package com.ulfric.factions.command;

import org.apache.commons.lang3.StringUtils;

import com.ulfric.andrew.Alias;
import com.ulfric.andrew.Context;
import com.ulfric.andrew.Sender;
import com.ulfric.andrew.argument.Argument;
import com.ulfric.commons.naming.Name;
import com.ulfric.factions.Entity;
import com.ulfric.factions.Universe;
import com.ulfric.factions.query.EntityQueries;
import com.ulfric.factions.text.RandomNameGenerator;
import com.ulfric.i18n.content.Details;

import java.util.Optional;

@Name("create")
@Alias({"new", "make"})
public class FactionsCreateCommand extends FactionsCommand { // TODO prevent bad words, attempt name normalization before failing

	@Argument
	protected String name;

	@Override
	public void run(Context context) {
		Sender sender = context.getSender();

		if (name.equals("?")) {
			name = RandomNameGenerator.createName();
		}

		if (StringUtils.isAlpha(name)) {
			Universe factions = Universe.get();
			Optional<Entity> creation = factions.createFaction(name);
			if (creation.isPresent()) {
				Entity created = creation.get();
				String name = created.query(EntityQueries.name());
				sender.sendMessage("factions-created", Details.of("factionName", name));
			} else if (factions.getFactionByName(name) != null) {
				sender.sendMessage("factions-create-failure-taken", Details.of("factionName", name));
			} else {
				sender.sendMessage("factions-create-failure", Details.of("factionName", name));
			}
			return;
		}

		if (StringUtils.isAsciiPrintable(name)) {
			sender.sendMessage("factions-create-must-be-alphabetical");
		} else {
			sender.sendMessage("factions-create-must-be-alphabetical-other-country");
		}
	}

}