package com.ulfric.factions;

import com.ulfric.commons.value.Bean;
import com.ulfric.palpatine.expiry.BooleanExpiries;
import com.ulfric.palpatine.expiry.Expiry;

import java.util.Objects;

public final class Invitation extends Bean {

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder implements org.apache.commons.lang3.builder.Builder<Invitation> {
		private Entity invited;
		private Expiry expiry;

		Builder() {
		}

		@Override
		public Invitation build() {
			Objects.requireNonNull(invited, "invited");

			return new Invitation(invited, expiry());
		}

		private Expiry expiry() {
			return expiry == null ? BooleanExpiries.NEVER_EXPIRES : expiry; // TODO make this last 1 day by default
		}
	}

	private final Entity invited;
	private final Expiry expiry;

	private Invitation(Entity invited, Expiry expiry) {
		this.invited = invited;
		this.expiry = expiry;
	}

	public Entity getInvited() {
		return invited;
	}

	public Expiry getExpiry() {
		return expiry;
	}

}