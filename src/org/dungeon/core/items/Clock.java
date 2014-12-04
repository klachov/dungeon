package org.dungeon.core.items;

import org.dungeon.core.creatures.Creature;
import org.dungeon.core.game.Selectable;
import org.dungeon.utils.Constants;

public class Clock extends Item implements Selectable {

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

    // Clocks.
    private ClockComponent clock;

    private Clock() {

    }

    public static Clock createItem(ItemPreset preset) {
        Clock result = new Clock();

        result.id = preset.id;
        result.type = preset.type;
        result.name = preset.name;

        result.repairable = preset.repairable;
        result.maxIntegrity = preset.maxIntegrity;
        result.curIntegrity = preset.curIntegrity;

        result.clock = new ClockComponent(preset.clockComponent);
        result.clock.setMaster(result);

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
            if (isClock()) {
                // A clock just broke! Update its last time record.
                clock.setLastTime(getOwner().getLocation().getWorld().getWorldDate());
            }
        }
    }

    public boolean isRepairable() {
        return repairable;
    }

    // Clock methods
    public boolean isClock() {
        return clock != null;
    }

    public ClockComponent getClock() {
        return clock;
    }

    // Durability methods
    public boolean isBroken() {
        return getCurIntegrity() == 0;
    }

    // Selectable implementation
    @Override
    public String toSelectionEntry() {
        String typeString = String.format("[%s]", getType());
        
        return String.format(Constants.SELECTION_ENTRY_FORMAT, typeString, getName());
    }

    // Printing methods
    public String getStatusString() {
        StringBuilder builder = new StringBuilder();
        String nameString = getName();
        builder.append(String.format("%-20s%20s\n", "Name", nameString));
        // Uses three lines to build the integrity line to improve code readability.
        return builder.toString();
    }


}
