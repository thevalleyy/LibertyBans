/* 
 * LibertyBans-core
 * Copyright © 2020 Anand Beh <https://www.arim.space>
 * 
 * LibertyBans-core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * LibertyBans-core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with LibertyBans-core. If not, see <https://www.gnu.org/licenses/>
 * and navigate to version 3 of the GNU Affero General Public License.
 */
package space.arim.libertybans.core.commands;

import space.arim.api.chat.SendableMessage;

import space.arim.libertybans.api.Operator;
import space.arim.libertybans.core.LibertyBansCore;
import space.arim.libertybans.core.env.CmdSender;

public class PrefixedCmdSender implements CmdSender {
	
	private final LibertyBansCore core;
	private final CmdSender sender;
	private final SendableMessage prefix;

	public PrefixedCmdSender(LibertyBansCore core, CmdSender sender, SendableMessage prefix) {
		this.core = core;
		this.sender = sender;
		this.prefix = prefix;
	}

	@Override
	public Operator getOperator() {
		return sender.getOperator();
	}

	@Override
	public boolean hasPermission(String permission) {
		return sender.hasPermission(permission);
	}

	@Override
	public void sendMessage(SendableMessage message) {
		sender.sendMessage(prefix.concatenate(message));
	}

	@Override
	public void parseThenSend(String message) {
		SendableMessage parsed = core.getFormatter().parseMessage(message);
		sender.sendMessage(prefix.concatenate(parsed));
	}

	@Override
	public Object getRawSender() {
		return sender.getRawSender();
	}
}
