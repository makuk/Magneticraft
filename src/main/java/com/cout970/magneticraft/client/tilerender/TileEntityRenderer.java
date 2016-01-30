package com.cout970.magneticraft.client.tilerender;

import net.darkaqua.blacksmith.api.render.tileentity.ITileEntityRenderer;
import net.darkaqua.blacksmith.api.tileentity.ITileEntityDefinition;

/**
 * Created by cout970 on 29/01/2016.
 */
public abstract class TileEntityRenderer<T extends ITileEntityDefinition> implements ITileEntityRenderer<T> {

    public abstract void initModels();
}
