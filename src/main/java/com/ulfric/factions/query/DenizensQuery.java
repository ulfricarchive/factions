package com.ulfric.factions.query;

import com.ulfric.factions.Entity;
import com.ulfric.factions.mixin.DenizensMixin;

import java.util.List;

enum DenizensQuery implements EntityQuery<List<Entity>> {

	INSTANCE;

	@Override
	public List<Entity> queryFrom(Entity entity) {
		if (entity instanceof DenizensMixin) {
			DenizensMixin denizens = (DenizensMixin) entity;
			return denizens.getDenizens();
		}

		throw new InvalidQueryException("Entity must have a DenizensMixin (was " + entity + ')');
	}

}