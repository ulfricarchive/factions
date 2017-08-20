package com.ulfric.factions;

import com.ulfric.servix.Service;

import java.util.Optional;
import java.util.UUID;

public interface Universe extends Service<Universe> {

	public static Universe get() {
		return Service.get(Universe.class);
	}

	Entity getFactionByName(String name);

	Entity getFactionByUniqueId(UUID uniqueId);

	Entity getDenizen(UUID uniqueId);

	Optional<Entity> createFaction(String name);

}