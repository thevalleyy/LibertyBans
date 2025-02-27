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

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandPackageTest {

	@Test
	public void noArguments() {
		CommandPackage cmd = ArrayCommandPackage.create();

		assertFalse(cmd.hasNext());
		assertEquals("", cmd.allRemaining());
		assertFalse(cmd.findHiddenArgument("s"));
		assertThrows(NoSuchElementException.class, cmd::next);
	}

	@Test
	public void oneNormalArg() {
		CommandPackage cmd = ArrayCommandPackage.create("arg");

		assertTrue(cmd.hasNext());
		assertFalse(cmd.findHiddenArgument("g"));
		assertEquals("arg", cmd.next());
		assertThrows(NoSuchElementException.class, cmd::next);
	}

	@Test
	public void oneNormalArgAllRemaining() {
		CommandPackage cmd = ArrayCommandPackage.create("arg");

		assertEquals("arg", cmd.allRemaining());
		assertThrows(NoSuchElementException.class, cmd::next);
	}

	@Test
	public void hiddenArgSurroundedByNormalArgsConsume() {
		CommandPackage cmd = ArrayCommandPackage.create("arg1", "-s", "arg2");

		assertFalse(cmd.findHiddenArgument("s"), "-s is not yet visible");
		assertFalse(cmd.findHiddenArgument("g"), "No such -g specified");

		assertEquals("arg1", cmd.next());

		assertTrue(cmd.findHiddenArgument("s"), "-s is now visible, having been passed");
		assertFalse(cmd.findHiddenArgument("g"), "No such -g specified");
	}

	@Test
	public void hiddenArgSurroundedByNormalArgsAllRemaining() {
		CommandPackage cmd = ArrayCommandPackage.create("arg1", "-s", "arg2");

		assertFalse(cmd.findHiddenArgument("s"), "-s is not yet visible");
		assertFalse(cmd.findHiddenArgument("g"), "No such -g specified");

		assertEquals("arg1 -s arg2", cmd.allRemaining());
	}

	@Test
	public void hiddenArgOnlyCanBeFound() {
		CommandPackage cmd = ArrayCommandPackage.create("-gav");

		assertTrue(cmd.findHiddenArgument("gav"), "Hidden argument can be found even if no normal arguments exist");
	}

	@Test
	public void hiddenArgOnlyHasNoNormalArguments() {
		CommandPackage cmd = ArrayCommandPackage.create("-gav");

		assertFalse(cmd.hasNext(), "Hidden arguments are not visible through iteration");
		assertEquals("", cmd.allRemaining());
	}

	@Test
	public void multipleNormalAndHiddenArgs() {
		CommandPackage cmd = ArrayCommandPackage.create("user1 -s 30d teaming -green in kitpvp".split(" "));

		assertTrue(cmd.hasNext());
		assertEquals("user1", cmd.next());

		assertTrue(cmd.hasNext());
		assertEquals("30d", cmd.next());

		assertTrue(cmd.findHiddenArgument("s"));
		assertFalse(cmd.findHiddenArgument("green"));

		assertEquals("teaming -green in kitpvp", cmd.allRemaining());
		assertFalse(cmd.hasNext());
	}
	
}
