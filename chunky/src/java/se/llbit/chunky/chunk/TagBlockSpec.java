package se.llbit.chunky.chunk;

import se.llbit.chunky.block.*;
import se.llbit.nbt.Tag;
import se.llbit.util.NotNull;

public class TagBlockSpec implements BlockSpec {
  private static final int MAGIC = 0xE6FFE636;
  public final Tag tag;

  public TagBlockSpec(@NotNull  Tag tag) {
    this.tag = tag;
  }

  @Override public int hashCode() {
    return MAGIC ^ tag.hashCode();
  }

  @Override public boolean equals(Object obj) {
    return (obj instanceof TagBlockSpec)
        && ((TagBlockSpec) obj).tag.equals(tag);
  }

  /**
   * Converts NBT block data to Chunky block object.
   */
  @Override public Block toBlock() {
    // Reference: https://minecraft.gamepedia.com/Java_Edition_data_values#Blocks
    String name = tag.get("Name").stringValue("minecraft:air");
    if (name.startsWith("minecraft:")) {
      name = name.substring(10);
      // TODO: add a new class for each block type...
      // TODO: convert all old block IDs to the new block types.
      switch (name) {
        case "air":
        case "cave_air":
        case "void_air":
          return Air.INSTANCE;
        case "stone":
          return new Stone();
        case "granite":
          return new Granite();
        case "polished_granite":
          return new PolishedGranite();
        case "diorite":
          return new Diorite();
        case "polished_diorite":
          return new PolishedDiorite();
        case "andesite":
          return new Andesite();
        case "polished_andesite":
          return new PolishedAndesite();
        case "grass_block":
          return new GrassBlock();
        case "dirt":
          return new Dirt();
        case "coarse_dirt":
          return new CoarseDirt();
        case "podzol":
          return new Podzol();
        case "cobblestone":
          return new Cobblestone();
        case "oak_planks":
          return new OakPlanks();
        case "spruce_planks":
          return new SprucePlanks();
        case "birch_planks":
          return new BirchPlanks();
        case "jungle_planks":
          return new JunglePlanks();
        case "acacia_planks":
          return new AcaciaPlanks();
        case "dark_oak_planks":
          return new DarkOakPlanks();
        case "oak_sapling":
          return new OakSapling();
        case "spruce_sapling":
          return new SpruceSapling();
        case "birch_sapling":
          return new BirchSapling();
        case "jungle_sapling":
          return new JungleSapling();
        case "acacia_sapling":
          return new AcaciaSapling();
        case "dark_oak_sapling":
          return new DarkOakSapling();
        case "water": {
          int level = 0;
          try {
            level = Integer.parseInt(tag.get("Properties").get("level").stringValue("0"));
          } catch (NumberFormatException ignored) {
          }
          return new Water(level);
        }
        case "bubble_column":
          return new UnknownBlock(name);
        case "lava": {
          int level = 0;
          try {
            level = Integer.parseInt(tag.get("Properties").get("level").stringValue("0"));
          } catch (NumberFormatException ignored) {
          }
          return new Lava(level);
        }
        case "bedrock":
          return new Bedrock();
        case "sand":
          return new Sand();
        case "red_sand":
          return new RedSand();
        case "gravel":
          return new Gravel();
        case "gold_ore":
          return new GoldOre();
        case "iron_ore":
          return new IronOre();
        case "coal_ore":
          return new CoalOre();
        case "oak_log": {
          String axis = tag.get("Properties").get("axis").stringValue("y");
          return new OakLog(axis);
        }
        case "spruce_log": {
          String axis = tag.get("Properties").get("axis").stringValue("y");
          return new SpruceLog(axis);
        }
        case "birch_log": {
          String axis = tag.get("Properties").get("axis").stringValue("y");
          return new BirchLog(axis);
        }
        case "jungle_log": {
          String axis = tag.get("Properties").get("axis").stringValue("y");
          return new JungleLog(axis);
        }
        case "acacia_log": {
          String axis = tag.get("Properties").get("axis").stringValue("y");
          return new AcaciaLog(axis);
        }
        case "dark_oak_log": {
          String axis = tag.get("Properties").get("axis").stringValue("y");
          return new DarkOakLog(axis);
        }
        case "stripped_oak_log":
        case "stripped_spruce_log":
        case "stripped_birch_log":
        case "stripped_jungle_log":
        case "stripped_acacia_log":
        case "stripped_dark_oak_log":
        case "stripped_oak_wood":
        case "stripped_spruce_wood":
        case "stripped_birch_wood":
        case "stripped_jungle_wood":
        case "stripped_acacia_wood":
        case "stripped_dark_oak_wood":
        case "oak_wood":
          return new OakWood();
        case "spruce_wood":
          return new SpruceWood();
        case "birch_wood":
          return new BirchWood();
        case "jungle_wood":
          return new JungleWood();
        case "acacia_wood":
          return new AcaciaWood();
        case "dark_oak_wood":
          return new DarkOakWood();
        case "oak_leaves":
          return new OakLeaves();
        case "spruce_leaves":
          return new SpruceLeaves();
        case "birch_leaves":
          return new BirchLeaves();
        case "jungle_leaves":
          return new JungleLeaves();
        case "acacia_leaves":
          return new AcaciaLeaves();
        case "dark_oak_leaves":
          return new DarkOakLeaves();
        case "sponge":
          return new Sponge();
        case "wet_sponge":
          return new WetSponge();
        case "glass":
          return new Glass();
        case "lapis_ore":
          return new LapisOre();
        case "lapis_block":
          return new LapisBlock();
        case "dispenser":
          return new UnknownBlock(name);
        case "sandstone":
          return new Sandstone();
        case "chiseled_sandstone":
        case "cut_sandstone":
          return new UnknownBlock(name);
        case "note_block":
          return new NoteBlock();
        case "powered_rail":
        case "detector_rail":
        case "sticky_piston":
        case "cobweb":
          return new Cobweb();
        case "grass":
          return new Grass();
        case "fern":
        case "dead_bush":
        case "seagrass":
        case "tall_seagrass":
        case "sea_pickle":
        case "piston":
        case "piston_head":
        case "moving_piston":
        case "white_wool":
        case "orange_wool":
        case "magenta_wool":
        case "light_blue_wool":
        case "yellow_wool":
        case "lime_wool":
        case "pink_wool":
        case "gray_wool":
        case "light_gray_wool":
        case "cyan_wool":
        case "purple_wool":
        case "blue_wool":
        case "brown_wool":
        case "green_wool":
        case "red_wool":
        case "black_wool":
          return new UnknownBlock(name);
        case "dandelion":
          return new Dandelion();
        case "poppy":
          return new Poppy();
        case "blue_orchid":
          return new BlueOrchid();
        case "allium":
          return new Allium();
        case "azure_bluet":
          return new AzureBluet();
        case "red_tulip":
          return new TulipRed();
        case "orange_tulip":
          return new TulipOrange();
        case "white_tulip":
          return new TulipWhite();
        case "pink_tulip":
          return new TulipPink();
        case "oxeye_daisy":
          return new OxeyeDaisy();
        case "cornflower":
          return new UnknownBlock(name);
        case "lily_of_the_valley":
          return new UnknownBlock(name);
        case "wither_rose":
          return new UnknownBlock(name);
        case "brown_mushroom":
        case "red_mushroom":
        case "gold_block":
        case "iron_block":
        case "oak_slab":
        case "spruce_slab":
        case "birch_slab":
        case "jungle_slab":
        case "acacia_slab":
        case "dark_oak_slab":
        case "stone_slab":
        case "smooth_stone_slab":
        case "sandstone_slab":
        case "petrified_oak_slab":
        case "cobblestone_slab":
        case "brick_slab":
        case "stone_brick_slab":
        case "nether_brick_slab":
        case "quartz_slab":
        case "red_sandstone_slab":
        case "purpur_slab":
        case "prismarine_slab":
        case "prismarine_brick_slab":
        case "dark_prismarine_slab":
        case "smooth_quartz":
        case "smooth_red_sandstone":
        case "smooth_sandstone":
        case "smooth_stone":
        case "bricks":
        case "tnt":
        case "bookshelf":
        case "mossy_cobblestone":
        case "obsidian":
        case "torch":
        case "wall_torch":
        case "end_rod":
        case "chorus_plant":
        case "chorus_flower":
        case "purpur_block":
        case "purpur_pillar":
        case "purpur_stairs":
        case "oak_stairs":
        case "spruce_stairs":
        case "birch_stairs":
        case "jungle_stairs":
        case "acacia_stairs":
        case "dark_oak_stairs":
        case "chest":
        case "diamond_ore":
        case "diamond_block":
        case "crafting_table":
        case "farmland":
        case "furnace":
        case "ladder":
        case "rail":
        case "cobblestone_stairs":
        case "lever":
        case "stone_pressure_plate":
        case "oak_pressure_plate":
        case "spruce_pressure_plate":
        case "birch_pressure_plate":
        case "jungle_pressure_plate":
        case "acacia_pressure_plate":
        case "dark_oak_pressure_plate":
        case "redstone_ore":
        case "redstone_torch":
        case "redstone_wall_torch":
        case "stone_button":
        case "snow":
        case "ice":
        case "snow_block":
        case "cactus":
        case "clay":
        case "jukebox":
        case "oak_fence":
        case "spruce_fence":
        case "birch_fence":
        case "jungle_fence":
        case "acacia_fence":
        case "dark_oak_fence":
        case "pumpkin":
        case "carved_pumpkin":
        case "netherrack":
        case "soul_sand":
        case "glowstone":
        case "jack_o_lantern":
        case "oak_trapdoor":
        case "spruce_trapdoor":
        case "birch_trapdoor":
        case "jungle_trapdoor":
        case "acacia_trapdoor":
        case "dark_oak_trapdoor":
        case "infested_stone":
        case "infested_cobblestone":
        case "infested_stone_bricks":
        case "infested_mossy_stone_bricks":
        case "infested_cracked_stone_bricks":
        case "infested_chiseled_stone_bricks":
        case "stone_bricks":
        case "mossy_stone_bricks":
        case "cracked_stone_bricks":
        case "chiseled_stone_bricks":
        case "brown_mushroom_block":
        case "red_mushroom_block":
        case "mushroom_stem":
        case "iron_bars":
        case "glass_pane":
        case "melon":
        case "vine":
        case "oak_fence_gate":
        case "spruce_fence_gate":
        case "birch_fence_gate":
        case "jungle_fence_gate":
        case "acacia_fence_gate":
        case "dark_oak_fence_gate":
        case "brick_stairs":
        case "stone_brick_stairs":
        case "mycelium":
        case "lily_pad":
        case "nether_bricks":
        case "nether_brick_fence":
        case "nether_brick_stairs":
        case "enchanting_table":
        case "end_portal_frame":
        case "end_stone":
        case "end_stone_bricks":
        case "redstone_lamp":
        case "cocoa":
        case "sandstone_stairs":
        case "emerald_ore":
        case "ender_chest":
        case "tripwire_hook":
        case "tripwire":
        case "emerald_block":
        case "beacon":
        case "cobblestone_wall":
        case "mossy_cobblestone_wall":
        case "oak_button":
        case "spruce_button":
        case "birch_button":
        case "jungle_button":
        case "acacia_button":
        case "dark_oak_button":
        case "anvil":
        case "chipped_anvil":
        case "damaged_anvil":
        case "trapped_chest":
        case "light_weighted_pressure_plate":
        case "heavy_weighted_pressure_plate":
        case "daylight_detector":
        case "redstone_block":
        case "nether_quartz_ore":
        case "hopper":
        case "chiseled_quartz_block":
        case "quartz_block":
        case "quartz_pillar":
        case "quartz_stairs":
        case "activator_rail":
        case "dropper":
        case "white_terracotta":
        case "orange_terracotta":
        case "magenta_terracotta":
        case "light_blue_terracotta":
        case "yellow_terracotta":
        case "lime_terracotta":
        case "pink_terracotta":
        case "gray_terracotta":
        case "light_gray_terracotta":
        case "cyan_terracotta":
        case "purple_terracotta":
        case "blue_terracotta":
        case "brown_terracotta":
        case "green_terracotta":
        case "red_terracotta":
        case "black_terracotta":
        case "iron_trapdoor":
        case "hay_block":
        case "white_carpet":
        case "orange_carpet":
        case "magenta_carpet":
        case "light_blue_carpet":
        case "yellow_carpet":
        case "lime_carpet":
        case "pink_carpet":
        case "gray_carpet":
        case "light_gray_carpet":
        case "cyan_carpet":
        case "purple_carpet":
        case "blue_carpet":
        case "brown_carpet":
        case "green_carpet":
        case "red_carpet":
        case "black_carpet":
        case "terracotta":
        case "coal_block":
        case "packed_ice":
        case "slime_block":
        case "grass_path":
        case "sunflower":
        case "lilac":
        case "rose_bush":
        case "peony":
          return new UnknownBlock(name);
        case "tall_grass": {
          String half = tag.get("Properties").get("half").stringValue("lower");
          return half.equals("lower") ? TallGrass.LOWER : TallGrass.UPPER;
        }
        case "large_fern":
          return new UnknownBlock(name);
        case "white_stained_glass":
          return new StainedGlassWhite();
        case "orange_stained_glass":
          return new StainedGlassOrange();
        case "magenta_stained_glass":
          return new StainedGlassMagenta();
        case "light_blue_stained_glass":
          return new StainedGlassLightBlue();
        case "yellow_stained_glass":
          return new StainedGlassYellow();
        case "lime_stained_glass":
          return new StainedGlassLime();
        case "pink_stained_glass":
          return new StainedGlassPink();
        case "gray_stained_glass":
          return new StainedGlassGray();
        case "light_gray_stained_glass":
          return new StainedGlassLightGray();
        case "cyan_stained_glass":
          return new StainedGlassCyan();
        case "purple_stained_glass":
          return new StainedGlassPurple();
        case "blue_stained_glass":
          return new StainedGlassBlue();
        case "brown_stained_glass":
          return new StainedGlassBrown();
        case "green_stained_glass":
          return new StainedGlassGreen();
        case "red_stained_glass":
          return new StainedGlassRed();
        case "black_stained_glass":
          return new StainedGlassBlack();
        case "white_stained_glass_pane":
        case "orange_stained_glass_pane":
        case "magenta_stained_glass_pane":
        case "light_blue_stained_glass_pane":
        case "yellow_stained_glass_pane":
        case "lime_stained_glass_pane":
        case "pink_stained_glass_pane":
        case "gray_stained_glass_pane":
        case "light_gray_stained_glass_pane":
        case "cyan_stained_glass_pane":
        case "purple_stained_glass_pane":
        case "blue_stained_glass_pane":
        case "brown_stained_glass_pane":
        case "green_stained_glass_pane":
        case "red_stained_glass_pane":
        case "black_stained_glass_pane":
        case "prismarine":
        case "prismarine_bricks":
        case "dark_prismarine":
        case "prismarine_stairs":
        case "prismarine_brick_stairs":
        case "dark_prismarine_stairs":
        case "sea_lantern":
        case "red_sandstone":
        case "chiseled_red_sandstone":
        case "cut_red_sandstone":
        case "red_sandstone_stairs":
        case "magma_block":
        case "nether_wart_block":
        case "red_nether_bricks":
        case "bone_block":
        case "observer":
        case "shulker_box":
        case "white_shulker_box":
        case "orange_shulker_box":
        case "magenta_shulker_box":
        case "light_blue_shulker_box":
        case "yellow_shulker_box":
        case "lime_shulker_box":
        case "pink_shulker_box":
        case "gray_shulker_box":
        case "light_gray_shulker_box":
        case "cyan_shulker_box":
        case "purple_shulker_box":
        case "blue_shulker_box":
        case "brown_shulker_box":
        case "green_shulker_box":
        case "red_shulker_box":
        case "black_shulker_box":
        case "white_glazed_terracotta":
        case "orange_glazed_terracotta":
        case "magenta_glazed_terracotta":
        case "light_blue_glazed_terracotta":
        case "yellow_glazed_terracotta":
        case "lime_glazed_terracotta":
        case "pink_glazed_terracotta":
        case "gray_glazed_terracotta":
        case "light_gray_glazed_terracotta":
        case "cyan_glazed_terracotta":
        case "purple_glazed_terracotta":
        case "blue_glazed_terracotta":
        case "brown_glazed_terracotta":
        case "green_glazed_terracotta":
        case "red_glazed_terracotta":
        case "black_glazed_terracotta":
        case "white_concrete":
        case "orange_concrete":
        case "magenta_concrete":
        case "light_blue_concrete":
        case "yellow_concrete":
        case "lime_concrete":
        case "pink_concrete":
        case "gray_concrete":
        case "light_gray_concrete":
        case "cyan_concrete":
        case "purple_concrete":
        case "blue_concrete":
        case "brown_concrete":
        case "green_concrete":
        case "red_concrete":
        case "black_concrete":
        case "white_concrete_powder":
        case "orange_concrete_powder":
        case "magenta_concrete_powder":
        case "light_blue_concrete_powder":
        case "yellow_concrete_powder":
        case "lime_concrete_powder":
        case "pink_concrete_powder":
        case "gray_concrete_powder":
        case "light_gray_concrete_powder":
        case "cyan_concrete_powder":
        case "purple_concrete_powder":
        case "blue_concrete_powder":
        case "brown_concrete_powder":
        case "green_concrete_powder":
        case "red_concrete_powder":
        case "black_concrete_powder":
        case "turtle_egg":
        case "dead_tube_coral_block":
        case "dead_brain_coral_block":
        case "dead_bubble_coral_block":
        case "dead_fire_coral_block":
        case "dead_horn_coral_block":
        case "tube_coral_block":
        case "brain_coral_block":
        case "bubble_coral_block":
        case "fire_coral_block":
        case "horn_coral_block":
        case "tube_coral":
        case "brain_coral":
        case "bubble_coral":
        case "fire_coral":
        case "horn_coral":
        case "dead_tube_coral":
        case "dead_brain_coral":
        case "dead_bubble_coral":
        case "dead_fire_coral":
        case "dead_horn_coral":
        case "tube_coral_fan":
        case "tube_coral_wall_fan":
        case "brain_coral_fan":
        case "brain_coral_wall_fan":
        case "bubble_coral_fan":
        case "bubble_coral_wall_fan":
        case "fire_coral_fan":
        case "fire_coral_wall_fan":
        case "horn_coral_fan":
        case "horn_coral_wall_fan":
        case "dead_tube_coral_fan":
        case "dead_tube_coral_wall_fan":
        case "dead_brain_coral_fan":
        case "dead_brain_coral_wall_fan":
        case "dead_bubble_coral_fan":
        case "dead_bubble_coral_wall_fan":
        case "dead_fire_coral_fan":
        case "dead_fire_coral_wall_fan":
        case "dead_horn_coral_fan":
        case "dead_horn_coral_wall_fan":
        case "blue_ice":
        case "conduit":
        case "polished_granite_stairs":
        case "smooth_red_sandstone_stairs":
        case "mossy_stone_brick_stairs":
        case "polished_diorite_stairs":
        case "mossy_cobblestone_stairs":
        case "end_stone_brick_stairs":
        case "stone_stairs":
        case "smooth_sandstone_stairs":
        case "smooth_quartz_stairs":
        case "granite_stairs":
        case "andesite_stairs":
        case "red_nether_brick_stairs":
        case "polished_andesite_stairs":
        case "diorite_stairs":
        case "polished_granite_slab":
        case "smooth_red_sandstone_slab":
        case "mossy_stone_brick_slab":
        case "polished_diorite_slab":
        case "mossy_cobblestone_slab":
        case "end_stone_brick_slab":
        case "smooth_sandstone_slab":
        case "smooth_quartz_slab":
        case "granite_slab":
        case "andesite_slab":
        case "red_nether_brick_slab":
        case "polished_andesite_slab":
        case "diorite_slab":
        case "brick_wall":
        case "prismarine_wall":
        case "red_sandstone_wall":
        case "mossy_stone_brick_wall":
        case "granite_wall":
        case "stone_brick_wall":
        case "nether_brick_wall":
        case "andesite_wall":
        case "red_nether_brick_wall":
        case "sandstone_wall":
        case "end_stone_brick_wall":
        case "diorite_wall":
        case "scaffolding":
        case "oak_door":
        case "iron_door":
        case "spruce_door":
        case "birch_door":
        case "jungle_door":
        case "acacia_door":
        case "dark_oak_door":
        case "repeater":
        case "comparator":
        case "composter":
        case "fire":
        case "wheat":
        case "sign":
        case "oak_sign":
        case "Sign":
        case "wall_sign":
        case "oak_wall_sign":
        case "spruce_sign":
        case "spruce_wall_sign":
        case "birch_sign":
        case "birch_wall_sign":
        case "jungle_sign":
        case "jungle_wall_sign":
        case "acacia_sign":
        case "acacia_wall_sign":
        case "dark_oak_sign":
        case "dark_oak_wall_sign":
        case "redstone_wire":
          return new UnknownBlock(name);
        case "sugar_cane":
          return new SugarCane();
        case "kelp":
        case "kelp_plant":
        case "dried_kelp_block":
        case "bamboo":
        case "bamboo_sapling":
        case "cake":
        case "white_bed":
        case "orange_bed":
        case "magenta_bed":
        case "light_blue_bed":
        case "yellow_bed":
        case "lime_bed":
        case "pink_bed":
        case "gray_bed":
        case "light_gray_bed":
        case "cyan_bed":
        case "purple_bed":
        case "blue_bed":
        case "brown_bed":
        case "green_bed":
        case "red_bed":
        case "black_bed":
        case "pumpkin_stem":
        case "attached_pumpkin_stem":
        case "melon_stem":
        case "attached_melon_stem":
        case "nether_wart":
        case "brewing_stand":
        case "cauldron":
        case "flower_pot":
        case "potted_poppy":
        case "potted_dandelion":
        case "potted_oak_sapling":
        case "potted_spruce_sapling":
        case "potted_birch_sapling":
        case "potted_jungle_sapling":
        case "potted_red_mushroom":
        case "potted_brown_mushroom":
        case "potted_cactus":
        case "potted_dead_bush":
        case "potted_fern":
        case "potted_acacia_sapling":
        case "potted_dark_oak_sapling":
        case "potted_blue_orchid":
        case "potted_allium":
        case "potted_azure_bluet":
        case "potted_red_tulip":
        case "potted_orange_tulip":
        case "potted_white_tulip":
        case "potted_pink_tulip":
        case "potted_oxeye_daisy":
        case "potted_bamboo":
        case "potted_cornflower":
        case "potted_lily_of_the_valley":
        case "potted_wither_rose":
        case "carrots":
        case "potatoes":
        case "skeleton_skull":
        case "skeleton_wall_skull":
        case "wither_skeleton_skull":
        case "wither_skeleton_wall_skull":
        case "zombie_head":
        case "zombie_wall_head":
        case "player_head":
        case "player_wall_head":
        case "creeper_head":
        case "creeper_wall_head":
        case "dragon_head":
        case "dragon_wall_head":
        case "white_banner":
        case "orange_banner":
        case "magenta_banner":
        case "light_blue_banner":
        case "yellow_banner":
        case "lime_banner":
        case "pink_banner":
        case "gray_banner":
        case "light_gray_banner":
        case "cyan_banner":
        case "purple_banner":
        case "blue_banner":
        case "brown_banner":
        case "green_banner":
        case "red_banner":
        case "black_banner":
        case "white_wall_banner":
        case "orange_wall_banner":
        case "magenta_wall_banner":
        case "light_blue_wall_banner":
        case "yellow_wall_banner":
        case "lime_wall_banner":
        case "pink_wall_banner":
        case "gray_wall_banner":
        case "light_gray_wall_banner":
        case "cyan_wall_banner":
        case "purple_wall_banner":
        case "blue_wall_banner":
        case "brown_wall_banner":
        case "green_wall_banner":
        case "red_wall_banner":
        case "black_wall_banner":
        case "beetroots":
        case "loom":
        case "barrel":
        case "smoker":
        case "blast_furnace":
        case "cartography_table":
        case "fleching_table":
        case "grindstone":
        case "lectern":
        case "smithing_table":
        case "stonecutter":
        case "bell":
        case "lantern":
        case "sweet_berry_bush":
        case "campfire":
        case "frosted_ice":
        case "spawner":
        case "nether_portal":
        case "end_portal":
        case "end_gateway":
        case "command_block":
        case "chain_command_block":
        case "repeating_command_block":
        case "structure_block":
        case "structure_void":
        case "jigsaw_block":
        case "barrier":
          return new UnknownBlock(name);
      }
    }
    return Air.INSTANCE;
  }

}
