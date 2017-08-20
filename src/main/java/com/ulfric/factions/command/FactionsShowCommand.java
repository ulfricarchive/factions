package com.ulfric.factions.command;

import com.ulfric.andrew.Alias;
import com.ulfric.andrew.Context;
import com.ulfric.andrew.Sender;
import com.ulfric.andrew.argument.Argument;
import com.ulfric.commons.naming.Name;
import com.ulfric.commons.text.StringHelper;
import com.ulfric.factions.Entity;
import com.ulfric.factions.Universe;
import com.ulfric.i18n.content.Details;

import java.util.List;

@Name("show")
@Alias({"this", "who", "me", "information", "info"}) // TODO should 'this' show the faction where the player is standing instead?
public class FactionsShowCommand extends FactionsCommand {

	@Argument(optional = true)
	protected Entity lookup;

	@Override
	public void run(Context context) {
		Sender sender = context.getSender();

		if (lookup == null) {
			List<String> arguments = context.getArguments().get(FactionsShowCommand.class);
			if (arguments.isEmpty()) {
				lookup = Universe.get().getDenizen(sender.getUniqueId());

				if (lookup == null) {
					sender.sendMessage("factions-show-not-in-faction-and-no-faction-specified");
					return;
				}
			} else {
				String given = StringHelper.joinWithOxfordComma(arguments, "or"); // TODO localize 'or'
				Details details = Details.of("given", given);
				sender.sendMessage("factions-show-not-in-faction-and-given-factions-not-found", details);
				return;
			}
		}

		

		// TODO
	}

}