package com.ulfric.factions.query;

import com.ulfric.factions.Entity;
import com.ulfric.factions.mixin.ParticipantMixin;

enum FactionQuery implements EntityQuery<Entity> {

	INSTANCE;

	@Override
	public Entity queryFrom(Entity entity) {
		if (entity instanceof ParticipantMixin) {
			ParticipantMixin participant = (ParticipantMixin) entity;
			return participant.getFaction();
		}

		throw new InvalidQueryException("Entity must have a ParticipantMixin (was " + entity + ')');
	}

}