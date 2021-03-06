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

package org.dungeon.core.counters;

import org.dungeon.core.creatures.Creature;
import org.dungeon.core.creatures.CreatureID;
import org.dungeon.core.creatures.CreatureType;

import java.io.Serializable;

class BattleLogEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    protected final CreatureID attackerID;
    protected final CreatureID defenderID;
    protected final String attackerWeapon;
    protected final String defenderWeapon;
    protected final CreatureType attackerType;
    protected final CreatureType defenderType;
    protected final boolean attackerWon;
    protected final int turns;

    public BattleLogEntry(Creature attacker, Creature defender, boolean attackerWon, int turns) {
        this.attackerID = attacker.getId();
        this.defenderID = defender.getId();
        if (attacker.hasWeapon()) {
            this.attackerWeapon = attacker.getWeapon().getId();
        } else {
            // If the creature was not equipping a weapon, consider an empty string as the weaponID.
            this.attackerWeapon = "";
        }
        if (defender.hasWeapon()) {
            this.defenderWeapon = defender.getWeapon().getId();
        } else {
            this.defenderWeapon = "";
        }
        this.attackerType = attacker.getType();
        this.defenderType = defender.getType();
        this.attackerWon = attackerWon;
        this.turns = turns;
    }
}
