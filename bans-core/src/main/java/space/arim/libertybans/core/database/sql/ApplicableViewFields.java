/*
 * LibertyBans
 * Copyright © 2021 Anand Beh
 *
 * LibertyBans is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * LibertyBans is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with LibertyBans. If not, see <https://www.gnu.org/licenses/>
 * and navigate to version 3 of the GNU Affero General Public License.
 */

package space.arim.libertybans.core.database.sql;

import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record12;
import org.jooq.Table;
import space.arim.libertybans.api.NetworkAddress;
import space.arim.libertybans.api.Operator;
import space.arim.libertybans.api.PunishmentType;
import space.arim.libertybans.api.Victim;
import space.arim.libertybans.api.scope.ServerScope;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class ApplicableViewFields<R extends Record12<
		Long, PunishmentType,
		Victim.VictimType, UUID, NetworkAddress,
		Operator, String, ServerScope, Instant, Instant,
		UUID, NetworkAddress>> implements PunishmentFields {

	private final Table<R> applicableView;
	private final R fieldSupplier;

	public ApplicableViewFields(Table<R> applicableView) {
		this.applicableView = applicableView;
		this.fieldSupplier = applicableView.newRecord();
	}

	@Override
	public Table<? extends Record> table() {
		return applicableView;
	}

	@Override
	public Field<Long> id() {
		return fieldSupplier.field1();
	}

	@Override
	public Field<PunishmentType> type() {
		return fieldSupplier.field2();
	}

	@Override
	public Field<Victim.VictimType> victimType() {
		return fieldSupplier.field3();
	}

	@Override
	public Field<UUID> victimUuid() {
		return fieldSupplier.field4();
	}

	@Override
	public Field<NetworkAddress> victimAddress() {
		return fieldSupplier.field5();
	}

	@Override
	public Field<Operator> operator() {
		return fieldSupplier.field6();
	}

	@Override
	public Field<String> reason() {
		return fieldSupplier.field7();
	}

	@Override
	public Field<ServerScope> scope() {
		return fieldSupplier.field8();
	}

	@Override
	public Field<Instant> start() {
		return fieldSupplier.field9();
	}

	@Override
	public Field<Instant> end() {
		return fieldSupplier.field10();
	}

	public Field<UUID> uuid() {
		return Objects.requireNonNull(fieldSupplier.field11(), "uuid field does not exist");
	}

	@Override
	public String toString() {
		return "ApplicableViewFields{" +
				"applicableView=" + applicableView +
				'}';
	}
}
