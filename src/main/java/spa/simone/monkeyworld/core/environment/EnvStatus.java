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
package spa.simone.monkeyworld.core.environment;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is the internal state of the environment.
 *
 * @author Deep Blue Team
 */
public class EnvStatus {

    private Lock bananasLock;
    private int bananasBunch;
    private int box;
    private int home;
    private int monkey;
    private int length;
    private boolean onTheBox;
    private boolean grabbed;
    private boolean atHome;
    private int penalty;

    /**
     * Create a new status with 10 locations.
     */
    public EnvStatus() {
        this(10);
    }

    /**
     * Creates a new environment with <code>length</code> locations.
     *
     * @param length
     *            the number of locations of this environment.
     */
    public EnvStatus(int length) {
        checkLength(length);
        this.length = length;
        home = 0;
        monkey = 0;
        bananasBunch = 0;
        box = 0;
        atHome = true;
        onTheBox = false;
        grabbed = false;
        bananasLock = new ReentrantLock(true);
        penalty = 0;
    }

    /**
     * @return the score
     */
    public int getPenalty() {
        return penalty;
    }

    /**
     * @param penalty the score to sum to the total
     */
    public void penalizeWith(int penalty) {
        this.penalty += penalty;
    }

    private void checkLength(int length) {
        if (length < 1) {
            throw new IllegalArgumentException(
                    "The lenght of the environment has to be at least 1");
        }
    }

    private void checkPosition(int position) {
        if (position < 0 || position >= length) {
            StringBuilder message = new StringBuilder();
            message.append("\"" + position + "\" is not a valid position.");
            message.append(" Please, insert a number between 0 and " + length
                    + ".");
            throw new IllegalArgumentException(message.toString());
        }
    }

    /**
     * Gets the position of the bananas bunch.
     *
     * @return the bananas bunch position
     */
    public int getBananasBunch() {
        bananasLock.lock();
        int position = bananasBunch;
        bananasLock.unlock();
        return position;

    }

    /**
     * Sets the position of the bananas bunch.
     *
     * @param position
     *            the bananas bunch position to set
     */
    public void setBananasBunch(int position) {
        bananasLock.lock();
        if (!isGrabbed()) {
            checkPosition(position);
            bananasBunch = position;
        }
        bananasLock.unlock();
    }

    /**
     * Tells if the monkey has grabbed the bunch of bananas.
     *
     * @return true if the bunch of bananas has been grabbed
     */
    public boolean isGrabbed() {
        bananasLock.lock();
        boolean flag = grabbed;
        bananasLock.unlock();
        return flag;
    }

    public void setGrabbed(boolean grabbed) {
        bananasLock.lock();
        this.grabbed = grabbed;
        bananasLock.unlock();
    }

    /**
     * Tells whether or not the monkey is on the box.
     *
     * @return true if the monkey climbed on the box
     */
    public boolean isOnTheBox() {
        return onTheBox;
    }

    /**
     * @param onTheBox true if the monkey is on the box
     */
    public void setOnTheBox(boolean onTheBox) {
        if (onTheBox == true && monkey == box) {
            this.onTheBox = true;
        } else if (onTheBox == false) {
            this.onTheBox = false;
        }
    }

    /**
     * Gets the position of the box.
     *
     * @return the box position
     */
    public int getBox() {
        return box;
    }

    /**
     * Sets the position of the box.
     *
     * @param position
     *            the box position to set
     */
    public void setBox(int position) {
        checkPosition(position);
        box = position;
    }

    /**
     * Gets the position of the home.
     *
     * @return the home position
     */
    public int getHome() {
        return home;
    }

    /**
     * Sets the position of the home.
     *
     * @param position
     *            the home position to set
     */
    public void setHome(int position) {
        checkPosition(position);
        home = position;
        monkey = home;
        atHome = true;
    }

    public boolean isAtHome() {
        return atHome;
    }

    public void setAtHome(boolean atHome) {
        this.atHome = atHome;
    }

    /**
     * Gets the position of the monkey.
     *
     * @return the monkey position
     */
    public int getMonkey() {
        return monkey;
    }

    /**
     * Sets the position of the monkey.
     *
     * @param position
     *            the monkey position to set
     */
    public void setMonkey(int position) {
        checkPosition(position);
        monkey = position;
        atHome = false;
    }

    /**
     * Gets the environment length.
     *
     * @return the length
     */
    public int getLength() {
        return length;
    }

}
