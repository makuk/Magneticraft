package com.cout970.magneticraft.item;

import com.cout970.magneticraft.Magneticraft;
import com.cout970.magneticraft.api.tool.IHammer;
import net.darkaqua.blacksmith.api.intermod.IInterfaceIdentifier;
import net.darkaqua.blacksmith.api.intermod.IInterfaceProvider;
import net.darkaqua.blacksmith.api.intermod.InterModUtils;
import net.darkaqua.blacksmith.api.inventory.IItemStack;
import net.darkaqua.blacksmith.api.render.model.RenderPlace;
import net.darkaqua.blacksmith.api.render.model.RenderTransformation;
import net.darkaqua.blacksmith.api.render.model.providers.IItemModelProvider;
import net.darkaqua.blacksmith.api.render.model.providers.defaults.ItemFlatModelProvider;
import net.darkaqua.blacksmith.api.util.Direction;
import net.darkaqua.blacksmith.api.util.ResourceReference;
import net.darkaqua.blacksmith.api.util.Vect3d;
import net.darkaqua.blacksmith.api.util.WorldRef;

/**
 * Created by cout970 on 21/12/2015.
 */
public class ItemIronHammer extends ItemBase implements IHammer, IInterfaceProvider {

    public ItemIronHammer() {
        maxDamage = 250;
        maxStackSize = 1;
    }

    @Override
    public String getItemName() {
        return "iron_hammer";
    }

    public IItemModelProvider getModelProvider() {
        return new ItemFlatModelProvider(new ResourceReference(Magneticraft.ID, "items/" + getItemName().toLowerCase()),
                (id) -> new ItemFlatModelProvider.ItemFlatModel(id,
                        (place) -> {
                            if (place == RenderPlace.THIRD_PERSON) {
                                return new RenderTransformation(new Vect3d(0, 1.25, -3.5).multiply(1 / 16d),
                                        new Vect3d(0, 90, -35), new Vect3d(0.85, 0.85, 0.85));
                            } else if (place == RenderPlace.FIRST_PERSON) {
                                return new RenderTransformation(new Vect3d(0, 4, 2).multiply(1 / 16d),
                                        new Vect3d(0, -135, 25), new Vect3d(1.7, 1.7, 1.7));
                            }
                            return null;
                        }));
    }

    @Override
    public IItemStack tick(IItemStack hammer, WorldRef ref) {
        if (hammer.getDamage() < hammer.getMaxDamage()) {
            hammer.setDamage(hammer.getDamage() + 1);
            return hammer;
        } else {
            return null;
        }
    }

    @Override
    public boolean canHammer(IItemStack hammer, WorldRef ref) {
        return true;
    }

    @Override
    public int getMaxHits(IItemStack hammer, WorldRef ref) {
        return 8;
    }

    @Override
    public boolean hasInterface(IInterfaceIdentifier id, Direction side) {
        return InterModUtils.matches(IHammer.IDENTIFIER, id);
    }

    @Override
    public <T> T getInterface(IInterfaceIdentifier<T> identifier, Direction side) {
        return (T) this;
    }
}
