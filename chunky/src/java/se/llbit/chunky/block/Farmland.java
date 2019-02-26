package se.llbit.chunky.block;

import se.llbit.chunky.model.TexturedBlockModel;
import se.llbit.chunky.renderer.scene.Scene;
import se.llbit.chunky.resources.Texture;
import se.llbit.math.Ray;

public class Farmland extends MinecraftBlock {
  private static final Texture[] texture = {
      Texture.dirt, Texture.dirt, Texture.dirt, Texture.dirt,
      Texture.farmlandDry, Texture.dirt,
  };
  private final int moisture;

  public Farmland(int moisture) {
    super("farmland", Texture.farmlandWet);
    this.moisture = moisture;
    localIntersect = true;

    if (moisture >= 7) {
      texture[4] = Texture.farmlandWet;
    }
  }

  @Override public boolean intersect(Ray ray, Scene scene) {
    return TexturedBlockModel.intersect(ray, texture);
  }

  @Override public String description() {
    return "moisture=" + moisture;
  }
}
