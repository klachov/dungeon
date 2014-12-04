package org.dungeon.core.items;

import org.dungeon.core.creatures.Creature;
import org.dungeon.core.game.Selectable;
import org.dungeon.utils.Constants;

public class Food extends Item implements Selectable {

    private static final long serialVersionUID = 1L;

    // Ownership.
    private Creature owner;

    // Identification fields.
    private String id;
    private String type;
    private String name;

    private int curIntegrity;
    private boolean repairable;
    
    // Food fields.
    private FoodComponent food;

    private Food() {

    }

    public static Food createItem(ItemPreset preset) {
        Food result = new Food();

        result.id = preset.id;
        result.type = preset.type;
        result.name = preset.name;
        
        result.repairable = preset.repairable;
        result.curIntegrity = preset.curIntegrity;

        result.food = new FoodComponent(preset.foodComponent);
        
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

    // Food methods
    public boolean isFood() {
        return food != null;
    }

    public FoodComponent getFood() {
        return food;
    }
    
    public boolean isBroken() {
        return getCurIntegrity() == 0;
    }
    
    public void decrementIntegrity(int integrityDecrement) {
        setCurIntegrity(getCurIntegrity() - integrityDecrement);
    }

    // Selectable implementation
    @Override
    public String toSelectionEntry() {
        String typeString = String.format("[%s]", getType());
        String extraString;
        extraString = String.format("Nutrition: %4d", getFood().getNutrition());
        
        return String.format(Constants.SELECTION_ENTRY_FORMAT, typeString, getName(), extraString);
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
