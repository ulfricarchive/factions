package com.ulfric.factions.command;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.ulfric.andrew.Sender;
import com.ulfric.andrew.argument.ResolutionRequest;
import com.ulfric.andrew.argument.Resolver;
import com.ulfric.commons.spigot.player.PlayerHelper;
import com.ulfric.commons.value.UniqueIdHelper;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.factions.Entity;
import com.ulfric.factions.Universe;

import java.util.UUID;

public class EntityResolver extends Resolver<Entity> {

	@Inject
	private Universe universe;

	public EntityResolver() {
		super(Entity.class);
	}

	@Override
	public Entity apply(ResolutionRequest request) {
		String argument = request.getArgument().toLowerCase();
		UUID uniqueId = UniqueIdHelper.parseUniqueId(argument);

		Entity entity;
		if (uniqueId != null) {
			entity = universe.getFactionByUniqueId(uniqueId);

			if (entity == null) {
				OfflinePlayer target = Bukkit.getOfflinePlayer(uniqueId);
				entity = getDenizenForOfflinePlayer(target);
			}
		} else {
			entity = universe.getFactionByName(argument);

			if (entity == null) {
				OfflinePlayer target = PlayerHelper.getOfflinePlayerByName(argument);
				entity = getDenizenForOfflinePlayer(target);
			}
		}

		if (entity == null) {
			if (argument.equals("?")) {
				return getRandomDenizenOtherThan(request.getContext().getSender());
			}

			else if (PlayerHelper.isAskingForSelf(argument)) {
				return universe.getDenizen(request.getContext().getSender().getUniqueId());
			}
		}

		return entity;
	}

	private Entity getRandomDenizenOtherThan(Sender sender) {
		UUID senderId = sender.getUniqueId();
		for (int x = 0; x < 4; x++) { // TODO make '4' configurable
			Player randomPlayer = PlayerHelper.getRandomPlayer();

			if (randomPlayer == null || randomPlayer.getUniqueId().equals(senderId)) {
				continue;
			}

			Entity entity = getDenizenForOfflinePlayer(randomPlayer);

			if (entity != null) {
				return entity;
			}
		}

		return null;
	}

	private Entity getDenizenForOfflinePlayer(OfflinePlayer player) {
		return PlayerHelper.hasPlayedOnServer(player) ? universe.getDenizen(player.getUniqueId()) : null; // TODO
	}

}