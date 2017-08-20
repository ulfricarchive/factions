package com.ulfric.factions;

import com.ulfric.factions.mutate.EntityMutation;
import com.ulfric.factions.query.EntityQuery;

import java.util.UUID;

public interface Entity {

	UUID getUniqueId();

	<T> T query(EntityQuery<T> query);

	void mutate(EntityMutation mutation);

}
