package se.llbit.chunky.block;

import se.llbit.chunky.model.CropsModel;
import se.llbit.chunky.renderer.scene.Scene;
import se.llbit.chunky.resources.Texture;
import se.llbit.math.Ray;

public class NetherWart extends MinecraftBlock {
  private static final Texture[] texture = {
      Texture.netherWart0, Texture.netherWart1, Texture.netherWart1, Texture.netherWart2
  };

  private final int age;

  public NetherWart(int age) {
    super("nether_wart", Texture.netherWart2);
    localIntersect = true;
    opaque = false;
    this.age = age & 3;
  }

  @Override public boolean intersect(Ray ray, Scene scene) {
    return CropsModel.intersect(ray, texture[age]);
  }

  @Override public String description() {
    return "age=" + age;
  }
}
