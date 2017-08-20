package com.ulfric.factions.query;

import com.ulfric.factions.Entity;

import java.util.List;

public class FactionQueries {

	public static EntityQuery<List<Entity>> denizens() {
		return DenizensQuery.INSTANCE;
	}

	private FactionQueries() {
	}

}