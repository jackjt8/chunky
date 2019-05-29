package se.llbit.chunky.chunk;

import se.llbit.chunky.block.Block;
import se.llbit.chunky.block.RedstoneLamp;
import se.llbit.nbt.CompoundTag;
import se.llbit.nbt.StringTag;
import se.llbit.nbt.Tag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BlockPalette {
  private static final int BLOCK_PALETTE_VERSION = 2;
  public final int airId, stoneId, waterId;

  private static final Map<String, Consumer<Block>> materialProperties = new HashMap<>();

  /** Stone blocks are used for filling invisible regions in the Octree. */
  public final Block stone, water;

  private final Map<BlockSpec, Integer> blockMap = new HashMap<>();
  private final List<Block> palette = new ArrayList<>();

  public BlockPalette() {
    CompoundTag airTag = new CompoundTag();
    airTag.add("Name", new StringTag("minecraft:air"));
    CompoundTag stoneTag = new CompoundTag();
    stoneTag.add("Name", new StringTag("minecraft:stone"));
    CompoundTag waterTag = new CompoundTag();
    waterTag.add("Name", new StringTag("minecraft:water"));
    airId = put(airTag);
    stoneId = put(stoneTag);
    waterId = put(waterTag);
    stone = get(stoneId);
    water = get(waterId);
  }

  /**
   * Adds a new block to the palette and returns the palette index.
   * @param tag NBT tag for the block.
   * @return the palette index of the block in this palette.
   */
  public int put(Tag tag) {
    return put(new TagBlockSpec(tag));
  }

  public int put(BlockSpec spec) {
    Integer id = blockMap.get(spec);
    if (id != null) {
      return id;
    }
    id = palette.size();
    blockMap.put(spec, id);
    Block block = spec.toBlock();
    applyMaterial(block);
    palette.add(block);
    return id;
  }

  private void applyMaterial(Block block) {
    Consumer<Block> properties = materialProperties.get(block.name);
    if (properties != null) {
      properties.accept(block);
    }
  }

  public Block get(int id) {
    return palette.get(id);
  }

  static {
    materialProperties.put("minecraft:water", block -> {
      block.specular = 0.12f;
      block.ior = 1.333f;
      block.refractive = true;
    });
    materialProperties.put("minecraft:lava", block -> {
      block.emittance = 1.0f;
    });
    materialProperties.put("minecraft:glass", block -> {
      block.emittance = 1.52f;
    });
    materialProperties.put("minecraft:gold_block", block -> {
      block.emittance = 0.04f;
    });
    materialProperties.put("minecraft:diamond_block", block -> {
      block.emittance = 0.04f;
    });
    materialProperties.put("minecraft:iron_block", block -> {
      block.emittance = 0.04f;
    });
    materialProperties.put("minecraft:redstone_torch", block -> {
      block.emittance = 1.0f;
    });
    materialProperties.put("minecraft:torch", block -> {
      block.emittance = 50.0f;
    });
    materialProperties.put("minecraft:wall_torch", block -> {
      block.emittance = 50.0f;
    });
    materialProperties.put("minecraft:fire", block -> {
      block.emittance = 1.0f;
    });
    materialProperties.put("minecraft:ice", block -> {
      block.ior = 1.31f;
      block.refractive = true;
    });
    materialProperties.put("minecraft:frosted_ice", block -> {
      block.ior = 1.31f;
      block.refractive = true;
    });
    materialProperties.put("minecraft:glowstone", block -> {
      block.emittance = 1.0f;
    });
    materialProperties.put("minecraft:portal", block -> {
      block.emittance = 0.4f;
    });
    materialProperties.put("minecraft:jackolantern", block -> {
      block.emittance = 1.0f;
    });
    materialProperties.put("minecraft:beacon", block -> {
      block.emittance = 1.0f;
      block.ior = 1.52f;
    });
    materialProperties.put("minecraft:redstone_lamp", block -> {
      if (block instanceof RedstoneLamp && ((RedstoneLamp) block).isLit) {
        block.emittance = 1.0f;
      }
    });
    materialProperties.put("minecraft:emerald_block", block -> {
      block.specular = 0.04f;
    });
    materialProperties.put("minecraft:sea_lantern", block -> {
      block.emittance = 0.5f;
    });
    materialProperties.put("minecraft:magma", block -> {
      block.emittance = 0.6f;
    });
    materialProperties.put("minecraft:end_rod", block -> {
      block.emittance = 1.0f;
    });
    materialProperties.put("minecraft:kelp", block -> {
      block.waterlogged = true;
    });
    materialProperties.put("minecraft:kelp_plant", block -> {
      block.waterlogged = true;
    });
    materialProperties.put("minecraft:seagrass", block -> {
      block.waterlogged = true;
    });
    materialProperties.put("minecraft:tall_seagrass", block -> {
      block.waterlogged = true;
    });
    // TODO: handle glass panes (multiple different block names).
    /*STAINED_GLASS.ior = 1.52f;
    GLASSPANE.ior = 1.52f;
    STAINED_GLASSPANE.ior = 1.52f;*/
  }

  /**
   * Writes the block specifications to file.
   */
  public void write(DataOutputStream out) throws IOException {
    out.writeInt(BLOCK_PALETTE_VERSION);
    BlockSpec[] specs = new BlockSpec[blockMap.size()];
    for (Map.Entry<BlockSpec, Integer> entry : blockMap.entrySet()) {
      specs[entry.getValue()] = entry.getKey();
    }
    out.writeInt(specs.length);
    for (BlockSpec spec : specs) {
      spec.serialize(out);
    }
  }

  public static BlockPalette read(DataInputStream in) throws IOException {
    int version = in.readInt();
    if (version != BLOCK_PALETTE_VERSION) {
      throw new IOException("Incompatible block palette format.");
    }
    int numBlocks = in.readInt();
    BlockPalette palette = new BlockPalette();
    for (int i = 0; i < numBlocks; ++i) {
      BlockSpec spec = BlockSpec.deserialize(in);
      palette.blockMap.put(spec, i);
      palette.palette.add(spec.toBlock());
    }
    return palette;
  }
}
