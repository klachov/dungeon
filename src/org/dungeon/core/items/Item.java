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

package org.dungeon.core.items;

import org.dungeon.core.creatures.Creature;
import org.dungeon.core.game.Selectable;

import java.io.Serializable;

public abstract class Item implements Selectable, Serializable {

    private static final long serialVersionUID = 1L;

    // Ownership.
    private Creature owner;

    // Identification fields.
    private String id;
    private String type;
    private String name;
   
    // Getters and setters
    public Creature getOwner() {
        return owner;
    }

    public void setOwner(Creature owner) {
        this.owner = owner;
    }
    
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public abstract void setCurIntegrity(int newIntegrity);
    public abstract boolean isRepairable();
    public abstract boolean isBroken();
    
    public abstract String toSelectionEntry();

}
