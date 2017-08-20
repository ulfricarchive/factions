package com.ulfric.factions.query;

import com.ulfric.factions.Entity;
import com.ulfric.factions.mixin.NamedMixin;

enum NameQuery implements EntityQuery<String> {

	INSTANCE;

	@Override
	public String queryFrom(Entity entity) {
		if (entity instanceof NamedMixin) {
			NamedMixin denizens = (NamedMixin) entity;
			return denizens.getName();
		}

		throw new InvalidQueryException("Entity must have a NamedMixin (was " + entity + ')');
	}

}