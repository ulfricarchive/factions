package com.ulfric.factions.mutate;

public class SetNameContext implements MutationContext {

	private final String name;

	public SetNameContext(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}