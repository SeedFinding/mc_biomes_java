package kaptainwutax.biomeutils.layer.scale;

import kaptainwutax.biomeutils.layer.BiomeLayer;
import kaptainwutax.biomeutils.layer.land.NoiseLayer;
import kaptainwutax.seedutils.mc.MCVersion;

public class ScaleLayer extends BiomeLayer {

    private final Type type;

    public ScaleLayer(MCVersion version, long worldSeed, long salt, Type type, BiomeLayer parent) {
        this(version, worldSeed, salt, type, parent, true);
    }

    public ScaleLayer(MCVersion version, long worldSeed, long salt, Type type, BiomeLayer parent, boolean shouldInit) {
        super(version, worldSeed, salt, parent);
        this.type = type;
        // this line needs an explanation : basically back when the stack was recursively initialized, only if the parent was initialized
        // but the hills layer only had one parent the other branch was never initialized recursively, so we simulate this stuff here.
        if (!shouldInit && version.isOlderThan(MCVersion.v1_13)) {
            // added a safeguard as 1.13+ should not have this bug
            this.layerSeed = 0;
        }

    }

    public Type getType() {
        return this.type;
    }

    @Override
    public int sample(int x, int y, int z) {
        int i = this.getParent().get(x >> 1, y, z >> 1);
        this.setSeed(x & -2, z & -2);
        int xb = x & 1, zb = z & 1;

        if (xb == 0 && zb == 0) return i;

        int l = this.getParent().get(x >> 1, y, (z + 1) >> 1);
        int m = this.choose(i, l);

        if (xb == 0) return m;

        int n = this.getParent().get((x + 1) >> 1, y, z >> 1);
        int o = this.choose(i, n);

        if (zb == 0) return o;

        int p = getParent().get((x + 1) >> 1, y, (z + 1) >> 1);
        return this.sample(i, n, l, p);
    }

    public int sample(int center, int e, int s, int se) {
        int ret = this.choose(center, e, s, se);

        if (this.type == Type.FUZZY) {
            return ret;
        }

        if (e == s && e == se) return e;
        if (center == e && (center == se || s != se)) return center;
        if (center == s && (center == se || e != se)) return center;
        if (center == se && e != s) return center;
        if (e == s && center != se) return e;
        if (e == se && center != s) return e;
        if (s == se && center != e) return s;
        return ret;
    }

    public enum Type {
        NORMAL, FUZZY
    }

}
