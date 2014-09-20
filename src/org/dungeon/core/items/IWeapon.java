package org.dungeon.core.items;

/**
 * Interface that specifies what a weapon must do.
 * <p/>
 * Created by Bernardo Sulzbach on 09/20/2014.
 */
public interface IWeapon {

    String getName();

    int getDamage();

    boolean isMiss();

    void repair();
}
