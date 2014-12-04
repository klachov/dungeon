package org.dungeon.core.items;

public class ItemFactory {
	public static Item createItem(ItemPreset itemPreset){
		
		if (itemPreset.weapon){
			return Weapon.createItem(itemPreset);
		}
		else if (itemPreset.foodComponent != null){
			return Food.createItem(itemPreset);
		}
		else if (itemPreset.clockComponent != null){
			return Clock.createItem(itemPreset);
		}
		else{
			return Armor.createItem(itemPreset);
		}
	}
}
