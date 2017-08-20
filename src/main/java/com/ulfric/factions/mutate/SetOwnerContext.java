package com.ulfric.factions.mutate;

import com.ulfric.factions.Entity;

public class SetOwnerContext implements MutationContext {

	private final Entity owner;

	public SetOwnerContext(Entity owner) {
		this.owner = owner;
	}

	public Entity getOwner() {
		return owner;
	}

}