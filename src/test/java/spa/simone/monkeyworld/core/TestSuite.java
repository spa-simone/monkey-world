/**
 * Monkey World is an environment where a monkey agent can stole a bunch of bananas and go home.
 * Copyright (C) 2011 Deep Blue Team <see the team details file>
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package spa.simone.monkeyworld.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import spa.simone.monkeyworld.core.agent.MonkeyActionTest;
import spa.simone.monkeyworld.core.agent.MonkeyTest;
import spa.simone.monkeyworld.core.environment.EnvStatusModifierTest;
import spa.simone.monkeyworld.core.environment.EnvStatusTest;
import spa.simone.monkeyworld.core.environment.LaboratoryTest;

/**
 * @author Deep Blue Team
 */
@RunWith(Suite.class)
@SuiteClasses({PlannerTest.class, MonkeyActionTest.class, MonkeyTest.class,
        EnvStatusModifierTest.class, EnvStatusTest.class, LaboratoryTest.class})
public class TestSuite {

}
