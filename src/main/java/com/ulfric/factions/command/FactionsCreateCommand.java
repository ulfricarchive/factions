package com.ulfric.factions.command;

import org.bukkit.command.CommandSender;

import org.apache.commons.lang3.StringUtils;

import com.ulfric.andrew.Alias;
import com.ulfric.andrew.Context;
import com.ulfric.andrew.argument.Argument;
import com.ulfric.commons.naming.Name;
import com.ulfric.commons.spigot.command.CommandSenderHelper;
import com.ulfric.factions.Entity;
import com.ulfric.factions.Universe;
import com.ulfric.factions.mutate.FactionMutations;
import com.ulfric.factions.query.DenizenQueries;
import com.ulfric.factions.text.RandomNameGenerator;
import com.ulfric.i18n.content.Details;
import com.ulfric.servix.services.locale.TellService;

import java.util.Optional;

@Name("create")
@Alias({"new", "make"})
public class FactionsCreateCommand extends FactionsCommand { // TODO Adam Edwards 8/20/17: prevent bad words, attempt name normalization before failing

	@Argument
	protected String name; // TODO Adam Edwards 8/20/17: dedicated 'Name' type?

	@Override
	public void run(Context context) {
		generateRandomNameIfNeeded();

		CommandSender sender = context.getSender();
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

	private void createFromSource(CommandSender sender) {
		Universe factions = Universe.get();

		Entity creator = factions.getDenizen(CommandSenderHelper.getUniqueId(sender));
		Entity currentFaction = creator.query(DenizenQueries.faction());
		if (currentFaction != null) {
			TellService.sendMessage(sender, "factions-create-already-in-faction", details(currentFaction));
			return;
		}

		Optional<Entity> creation = factions.createFaction(name);
		if (creation.isPresent()) {
			Entity created = creation.get();
			created.mutate(FactionMutations.owner(creator));
			TellService.sendMessage(sender, "factions-create", details(created));
		} else {
			Entity existing = factions.getFactionByName(name);
			 if (existing != null) { // TODO Adam Edwards 8/20/17: better 'faction exists' handling to prevent (harmless, next to impossibly rare) missed failure cause
				 TellService.sendMessage(sender, "factions-create-name-taken", details(existing));
			} else {
				TellService.sendMessage(sender, "factions-create-generic-failure", Details.of("attemptedName", name));
			}
		}
	}

	private Details details(Entity faction) {
		return Details.of("faction", faction);
	}

	private void invalidName(CommandSender sender) {
		if (StringUtils.isAsciiPrintable(name)) {
			sender.sendMessage("factions-create-must-be-alphabetical");
		} else {
			sender.sendMessage("factions-create-must-be-alphabetical-other-country");
		}
	}

}