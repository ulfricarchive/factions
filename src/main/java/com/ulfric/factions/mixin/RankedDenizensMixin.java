package com.ulfric.factions.mixin;

import com.ulfric.factions.Entity;

public interface RankedDenizensMixin extends DenizensMixin {

	Entity getOwner();

	void setOwner(Entity owner);

}