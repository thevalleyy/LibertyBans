/*
 * ArimBans, a punishment plugin for minecraft servers
 * Copyright © 2019 Anand Beh <https://www.arim.space>
 * 
 * ArimBans is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * ArimBans is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with ArimBans. If not, see <https://www.gnu.org/licenses/>
 * and navigate to version 3 of the GNU General Public License.
 */
package space.arim.bans.env;

import space.arim.bans.api.Punishment;
import space.arim.bans.internal.Configurable;

public interface Enforcer extends Configurable {
	void enforce(Punishment punishment);
	
	boolean callPunishEvent(Punishment punishment, boolean retro);
	
	boolean callUnpunishEvent(Punishment punishment, boolean automatic);
	
	void callPostPunishEvent(Punishment punishment, boolean retro);
	
	void callPostUnpunishEvent(Punishment punishment, boolean automatic);
}
