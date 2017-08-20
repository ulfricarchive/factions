package com.ulfric.factions.mutate;

import com.ulfric.factions.Entity;
import com.ulfric.factions.mixin.NamedMixin;

import java.util.Objects;

class SetNameMutation implements EntityMutation {

	private final SetNameContext context;

	public SetNameMutation(SetNameContext context) {
		Objects.requireNonNull(context, "context");

		this.context = context;
	}

	@Override
	public void mutateIn(Entity entity) {
		if (entity instanceof NamedMixin) {
			NamedMixin named = (NamedMixin) entity;
			named.setName(context.getName());
			return;
		}

		throw new InvalidMutationException("Entity must have a NamedMixin (was " + entity + ')');
	}

}