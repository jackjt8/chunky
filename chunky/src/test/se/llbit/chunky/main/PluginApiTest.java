/*
 * Copyright (c) 2016 Jesper Öqvist <jesper@llbit.se>
 *
 * This file is part of Chunky.
 *
 * Chunky is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Chunky is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with Chunky.  If not, see <http://www.gnu.org/licenses/>.
 */
package se.llbit.chunky.main;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import se.llbit.chunky.resources.Texture;
import se.llbit.chunky.world.Block;

import static org.junit.Assert.assertSame;

public class PluginApiTest {
  @Rule public ExpectedException thrown = ExpectedException.none();

  @Test public void testSetBlock1() {
    Block newGrass = new Block(Block.GRASS_ID, "foo", Texture.EMPTY_TEXTURE);
    Block.set(Block.GRASS_ID, newGrass);
    assertSame(newGrass, Block.get(Block.GRASS_ID));
  }

  @Test public void testSetBlock2() {
    // This will not work well in the renderer, but it is allowed.
    Block.set(Block.STONE_ID, Block.AIR);
    assertSame(Block.AIR, Block.get(Block.STONE_ID));
  }

  @Test public void testSetBlockFail1() {
    thrown.expect(IllegalArgumentException.class);
    Block.set(Block.GRASS_ID, null);
  }

  @Test public void testSetBlockFail2() {
    // Illegal block ID.
    thrown.expect(IllegalArgumentException.class);
    Block.set(-1, Block.AIR);
  }

  @Test public void testSetBlockFail3() {
    // Illegal block ID.
    thrown.expect(IllegalArgumentException.class);
    Block.set(256, Block.AIR);
  }
}