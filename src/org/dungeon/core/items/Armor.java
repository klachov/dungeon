
package org.dungeon.core.items;

import org.dungeon.core.creatures.Creature;
import org.dungeon.core.game.Game;
import org.dungeon.core.game.Selectable;
import org.dungeon.utils.Constants;

public class Armor extends Item implements Selectable {

    private static final long serialVersionUID = 1L;

    // Ownership.
    private Creature owner;

    // Identification fields.
    private String id;
    private String type;
    private String name;

    // Durability fields.
    private int maxIntegrity;
    private int curIntegrity;
    private boolean repairable;

    // Weapon fields.
    private boolean weapon;
    private int damage;
    private double hitRate;
    private int integrityDecrementOnHit;

    private Armor() {

    }

    public static Armor createItem(ItemPreset preset) {
        Armor result = new Armor();

        result.id = preset.id;
        result.type = preset.type;
        result.name = preset.name;

        result.repairable = preset.repairable;
        result.maxIntegrity = preset.maxIntegrity;
        result.curIntegrity = preset.curIntegrity;

        result.weapon = preset.weapon;
        result.damage = preset.damage;
        result.hitRate = preset.hitRate;
        result.integrityDecrementOnHit = preset.integrityDecrementOnHit;

        return result;
    }

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

    public int getMaxIntegrity() {
        return maxIntegrity;
    }

    public int getCurIntegrity() {
        return curIntegrity;
    }

    public void setCurIntegrity(int curIntegrity) {
        if (curIntegrity > 0) {
            this.curIntegrity = curIntegrity;
        } else {
            this.curIntegrity = 0;
            // TODO: maybe we should extract the "breaking routine" to another method.
        }
    }

    public boolean isRepairable() {
        return repairable;
    }

    public boolean isWeapon() {
        return weapon;
    }

    public void setWeapon(boolean weapon) {
        this.weapon = weapon;
    }

    public int getDamage() {
        return damage;
    }

    public double getHitRate() {
        return hitRate;
    }

    public int getIntegrityDecrementOnHit() {
        return integrityDecrementOnHit;
    }

    // Durability methods
    public boolean isBroken() {
        return getCurIntegrity() == 0;
    }

    public void decrementIntegrityByHit() {
        setCurIntegrity(getCurIntegrity() - getIntegrityDecrementOnHit());
    }

    public void decrementIntegrity(int integrityDecrement) {
        setCurIntegrity(getCurIntegrity() - integrityDecrement);
    }

    // Weapon methods
    public boolean rollForHit() {
        return getHitRate() > Game.RANDOM.nextDouble();
    }

    public String getWeaponIntegrity() {
        String weaponIntegrity;
        if (getCurIntegrity() == getMaxIntegrity()) {
            weaponIntegrity = "Not damaged";
        } else if (getCurIntegrity() >= getMaxIntegrity() * 0.65) {
            weaponIntegrity = "Slightly damaged";
        } else if (getCurIntegrity() >= getMaxIntegrity() * 0.3) {
            weaponIntegrity = "Damaged";
        } else if (getCurIntegrity() > 0) {
            weaponIntegrity = "Severely damaged";
        } else {
            weaponIntegrity = "Broken";
        }
        weaponIntegrity = String.format("(%s)", weaponIntegrity);
        return weaponIntegrity;
    }

    // Selectable implementation
    @Override
    public String toSelectionEntry() {
        String typeString = String.format("[%s]", getType());
        String extraString;
        extraString = String.format("Damage: %7d", getDamage());
        return String.format(Constants.SELECTION_ENTRY_FORMAT, typeString, getName(), extraString);
    }

    // Printing methods
    public String getStatusString() {
        StringBuilder builder = new StringBuilder();
        String nameString = getName();
        builder.append(String.format("%-20s%20s\n", "Name", nameString));
        builder.append(String.format("%-20s%20s\n", "Damage", getDamage()));
        // Uses three lines to build the integrity line to improve code readability.
        String weaponIntegrity = getWeaponIntegrity();
        builder.append(String.format("%-20s%20s\n", "Integrity", weaponIntegrity));
        return builder.toString();
    }

}
