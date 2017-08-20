package com.ulfric.factions.mutate;

import com.ulfric.factions.Entity;
import com.ulfric.factions.mixin.RankedDenizensMixin;

import java.util.Objects;

class SetOwnerMutation implements EntityMutation {

	private final SetOwnerContext context;

	public SetOwnerMutation(SetOwnerContext context) {
		Objects.requireNonNull(context, "context");

		this.context = context;
	}

	@Override
	public void mutateIn(Entity entity) {
		if (entity instanceof RankedDenizensMixin) {
			RankedDenizensMixin grouped = (RankedDenizensMixin) entity;
			grouped.setOwner(context.getOwner());
			return;
		}

		throw new InvalidMutationException("Entity must have a RankedDenizensMixin (was " + entity + ')');
	}

}