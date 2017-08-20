package com.ulfric.factions.query;

import com.ulfric.factions.Entity;

public interface EntityQuery<T> {

	T queryFrom(Entity faction);

}