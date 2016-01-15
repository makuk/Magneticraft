// Date: 29/12/2015 19:10:13
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX


package com.cout970.magneticraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelWoodenShaft extends ModelBase {
    //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;

    public ModelWoodenShaft() {
        textureWidth = 64;
        textureHeight = 64;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(-2F, 0F, -2F, 4, 8, 4);
        Shape1.setRotationPoint(0F, 16F, 0F);
        Shape1.setTextureSize(64, 64);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 12);
        Shape2.addBox(-2F, -2F, 0F, 4, 4, 8);
        Shape2.setRotationPoint(0F, 16F, 0F);
        Shape2.setTextureSize(64, 64);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 16, 0);
        Shape3.addBox(-2F, -8F, -2F, 4, 8, 4);
        Shape3.setRotationPoint(0F, 16F, 0F);
        Shape3.setTextureSize(64, 64);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 28);
        Shape4.addBox(-2F, -2F, -8F, 4, 4, 8);
        Shape4.setRotationPoint(0F, 16F, 0F);
        Shape4.setTextureSize(64, 64);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 24, 28);
        Shape5.addBox(0F, -2F, -2F, 8, 4, 4);
        Shape5.setRotationPoint(0F, 16F, 0F);
        Shape5.setTextureSize(64, 64);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 32, 0);
        Shape6.addBox(-8F, -2F, -2F, 8, 4, 4);
        Shape6.setRotationPoint(0F, 16F, 0F);
        Shape6.setTextureSize(64, 64);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 24, 12);
        Shape7.addBox(-4F, -4F, -4F, 8, 8, 8);
        Shape7.setRotationPoint(0F, 16F, 0F);
        Shape7.setTextureSize(64, 64);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
    }

//  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
//  {
//    super.render(entity, f, f1, f2, f3, f4, f5);
//    setRotationAngles(f, f1, f2, f3, f4, f5);
//    Shape1.render(f5);
//    Shape2.render(f5);
//    Shape3.render(f5);
//    Shape4.render(f5);
//    Shape5.render(f5);
//    Shape6.render(f5);
//    Shape7.render(f5);
//  }

    public void render(float f5, int sides, float rot) {
        if ((sides & 0x1) > 0) {
            Shape1.rotateAngleY = rot;
            Shape1.render(f5); // down
        }
        if ((sides & 0x2) > 0) {
            Shape3.rotateAngleY = rot;
            Shape3.render(f5); // up
        }
        if ((sides & 0x4) > 0) {
            Shape2.rotateAngleZ = rot;
            Shape2.render(f5); // north
        }
        if ((sides & 0x8) > 0) {
            Shape4.rotateAngleZ = rot;
            Shape4.render(f5); // south
        }
        if ((sides & 0x10) > 0) {
            Shape6.rotateAngleX = rot;
            Shape6.render(f5); // west
        }
        if ((sides & 0x20) > 0) {
            Shape5.rotateAngleX = rot;
            Shape5.render(f5); // east
        }
        if ((sides & 0x40) > 0) Shape7.render(f5); // center
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
