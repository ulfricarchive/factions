package com.ulfric.factions.mutate;

import com.ulfric.factions.Entity;

public class FactionMutations {

	public static EntityMutation name(String name) {
		return new SetNameMutation(new SetNameContext(name));
	}

	public static EntityMutation owner(Entity owner) {
		return new SetOwnerMutation(new SetOwnerContext(owner));
	}

	private FactionMutations() {
	}

}
