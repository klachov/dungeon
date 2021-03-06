/* 
 * Copyright (C) 2014 Bernardo Sulzbach
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dungeon.utils;

import org.dungeon.core.game.Game;
import org.dungeon.core.game.Selectable;
import org.dungeon.io.IO;
import org.dungeon.io.WriteStyle;

import java.util.List;

/**
 * General utility class.
 *
 * @author Bernardo Sulzbach
 */
public class Utils {

    /**
     * Prints the startup heading.
     */
    public static void printHeading() {
        IO.writeString(Constants.HEADING, WriteStyle.MARGIN);
    }

    /**
     * Prints the game's name followed by the version and its codename.
     */
    public static void printVersion() {
        IO.writeString(Constants.TITLE + " " + Constants.VERSION + " - " + Constants.CODENAME, WriteStyle.MARGIN);
    }

    /**
     * Checks if a string is a valid name in the game.
     */
    public static boolean isValidName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Names must be at least one character long.");
        } else if (!StringUtils.isAlphabetic(name)) {
            throw new IllegalArgumentException("Names must contain only letters.");
        } else {
            return true;
        }
    }

    /**
     * Method that let the player select a Selectable object from a List.
     */
    public static <T extends Selectable> T selectFromList(List<T> list) {
        int size = list.size();
        String indexFormat;
        if (list.isEmpty()) {
            IO.writeString("No options available.", WriteStyle.MARGIN);
            return null;
        } else if (size < 10) {
            indexFormat = "%1d";
        } else {
            indexFormat = "%2d";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(indexFormat, 0)).append(". ").append("Abort").append('\n');
        int index = 1;
        for (Selectable aSelectable : list) {
            builder.append(String.format(indexFormat, index)).append(". ").append(aSelectable.toSelectionEntry()).append('\n');
            index++;
        }
        IO.writeString(builder.toString(), WriteStyle.MARGIN);
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(IO.readString());
            } catch (NumberFormatException exception) {
                IO.writeString(Constants.INVALID_INPUT, WriteStyle.MARGIN);
                continue;
            }
            if (choice < 0 || choice > list.size()) {
                IO.writeString(Constants.INVALID_INPUT, WriteStyle.MARGIN);
            } else {
                break;
            }
        }
        if (choice == 0) {
            return null;
        }
        return list.get(choice - 1);
    }

    /**
     * Prints a message reporting the usage of an invalid command.
     */
    public static void printInvalidCommandMessage(String command) {
        IO.writeString(command + " is not a valid command.\nSee 'commands' for a list of valid commands.", WriteStyle.MARGIN);
    }

    public static void printCredits() {
        IO.writeString("This game was made by Bernardo Sulzbach and Gabriel Bohmer.\n", WriteStyle.MARGIN);
    }

    /**
     * Simulates a random roll.
     * @param chance the probability of a true result. Must be nonnegative and smaller than 1.
     */
    public static boolean roll(double chance) {
        if (chance < 0 || chance > 1) {
            throw new IllegalArgumentException("chance must be nonnegative and smaller than 1.");
        }
        return chance > Game.RANDOM.nextDouble();
    }

}
