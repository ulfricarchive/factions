package com.ulfric.factions.command;

import org.bukkit.command.CommandSender;

import com.ulfric.andrew.Alias;
import com.ulfric.andrew.Context;
import com.ulfric.andrew.argument.Argument;
import com.ulfric.commons.naming.Name;
import com.ulfric.commons.spigot.command.CommandSenderHelper;
import com.ulfric.commons.text.StringHelper;
import com.ulfric.factions.Entity;
import com.ulfric.factions.Universe;
import com.ulfric.factions.query.DenizenQueries;
import com.ulfric.i18n.content.Details;
import com.ulfric.servix.services.locale.TellService;

import java.util.List;

@Name("show")
@Alias({"this", "who", "me", "information", "info"}) // TODO should 'this' show the faction where the player is standing instead?
public class FactionsShowCommand extends FactionsCommand {

	@Argument(optional = true)
	protected Entity lookup;

	@Override
	public void run(Context context) {
		CommandSender sender = context.getSender();

		if (lookup == null) {
			List<String> arguments = context.getArguments().get(FactionsShowCommand.class);
			if (arguments.isEmpty()) {
				lookup = Universe.get().getDenizen(CommandSenderHelper.getUniqueId(sender));

				if (lookup == null) {
					sender.sendMessage("factions-show-not-in-faction-and-no-faction-specified");
					return;
				}

				Entity lookupDenizen = lookup;
				lookup = lookup.query(DenizenQueries.faction());
				if (lookup == null) {
					lookup = lookupDenizen;
					showDenizen(sender);
					return;
				}
			} else {
				String given = StringHelper.joinWithOxfordComma(arguments, "or"); // TODO localize 'or'
				Details details = Details.of("given", given);
				TellService.sendMessage(sender, "factions-show-not-in-faction-and-given-factions-not-found", details);
				return;
			}
		}

		showFaction(sender);
	}

	private void showDenizen(CommandSender sender) {
		TellService.sendMessage(sender, "factions-show-faction", details("denizen"));
	}

	private void showFaction(CommandSender sender) {
		TellService.sendMessage(sender, "factions-show-faction", details("faction"));
	}

	private Details details(String name) {
		return Details.of(name, lookup);
	}

}