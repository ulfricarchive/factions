package com.ulfric.factions.command;

import org.apache.commons.lang3.StringUtils;

import com.ulfric.andrew.Alias;
import com.ulfric.andrew.Context;
import com.ulfric.andrew.Sender;
import com.ulfric.andrew.argument.Argument;
import com.ulfric.commons.naming.Name;
import com.ulfric.factions.Entity;
import com.ulfric.factions.Universe;
import com.ulfric.factions.mutate.FactionMutations;
import com.ulfric.factions.query.DenizenQueries;
import com.ulfric.factions.text.RandomNameGenerator;
import com.ulfric.i18n.content.Details;

import java.util.Optional;

@Name("create")
@Alias({"new", "make"})
public class FactionsCreateCommand extends FactionsCommand { // TODO Adam Edwards 8/20/17: prevent bad words, attempt name normalization before failing

	@Argument
	protected String name; // TODO Adam Edwards 8/20/17: dedicated 'Name' type?

	@Override
	public void run(Context context) {
		generateRandomNameIfNeeded();

		Sender sender = context.getSender();
		if (nameIsValid()) {
			createFromSource(sender);
		} else {
			invalidName(sender);
		}
	}

	private void generateRandomNameIfNeeded() {
		if (name.equals("?")) {
			name = RandomNameGenerator.createName(); // TODO Adam Edwards 8/20/17: (currently harmless) ensure name is available
		}
	}

	private boolean nameIsValid() {
		return StringUtils.isAlpha(name);
	}

	private void createFromSource(Sender sender) {
		Universe factions = Universe.get();

		Entity creator = factions.getDenizen(sender.getUniqueId());
		Entity currentFaction = creator.query(DenizenQueries.faction());
		if (currentFaction != null) {
			sender.sendMessage("factions-create-already-in-faction", details(currentFaction));
			return;
		}

		Optional<Entity> creation = factions.createFaction(name);
		if (creation.isPresent()) {
			Entity created = creation.get();
			created.mutate(FactionMutations.owner(creator));
			sender.sendMessage("factions-created", details(created));
		} else {
			Entity existing = factions.getFactionByName(name);
			 if (existing != null) { // TODO Adam Edwards 8/20/17: better 'faction exists' handling to prevent (harmless, next to impossibly rare) missed failure cause
				sender.sendMessage("factions-create-name-taken", details(existing));
			} else {
				sender.sendMessage("factions-create-generic-failure", Details.of("attemptedName", name));
			}
		}
	}

	private Details details(Entity faction) {
		return Details.of("faction", faction);
	}

	private void invalidName(Sender sender) {
		if (StringUtils.isAsciiPrintable(name)) {
			sender.sendMessage("factions-create-must-be-alphabetical");
		} else {
			sender.sendMessage("factions-create-must-be-alphabetical-other-country");
		}
	}

}