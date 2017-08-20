package com.ulfric.factions.query;

import com.ulfric.factions.Entity;

public class DenizenQueries {

	public static EntityQuery<Entity> faction() {
		return FactionQuery.INSTANCE;
	}

	private DenizenQueries() {
	}

}