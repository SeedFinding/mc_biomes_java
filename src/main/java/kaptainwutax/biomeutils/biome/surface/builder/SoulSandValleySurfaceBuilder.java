package kaptainwutax.biomeutils.biome.surface.builder;

import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.biome.surface.SurfaceConfig;
import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.mcutils.block.Block;
import kaptainwutax.mcutils.block.Blocks;
import kaptainwutax.mcutils.rand.ChunkRand;

import java.util.Arrays;
import java.util.List;

public class SoulSandValleySurfaceBuilder extends ValleySurfaceBuilder {
	public static final List<Block> FLOOR_BLOCK_STATES = Arrays.asList(Blocks.SOUL_SAND, Blocks.SOUL_SOIL);
	public static final List<Block> CEILING_BLOCK_STATES = Arrays.asList(Blocks.SOUL_SAND, Blocks.SOUL_SOIL);

	public SoulSandValleySurfaceBuilder(SurfaceConfig surfaceConfig) {
		super(surfaceConfig);
	}

}
