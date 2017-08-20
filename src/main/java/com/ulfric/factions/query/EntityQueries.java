package com.ulfric.factions.query;

public class EntityQueries {

	public static EntityQuery<String> name() {
		return NameQuery.INSTANCE;
	}

	private EntityQueries() {
	}

}