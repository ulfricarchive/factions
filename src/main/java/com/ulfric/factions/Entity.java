package com.ulfric.factions;

import com.ulfric.commons.value.Result;
import com.ulfric.factions.mutate.EntityMutation;
import com.ulfric.factions.query.EntityQuery;

import java.util.UUID;

public interface Entity {

	UUID getUniqueId();

	<T> T query(EntityQuery<T> query);

	Result mutate(EntityMutation mutation);

}
