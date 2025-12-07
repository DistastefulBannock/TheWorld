package me.bannock.theworld.data.land.feature.impl;

import me.bannock.theworld.data.World;
import me.bannock.theworld.data.land.LandLot;
import me.bannock.theworld.data.land.feature.FeatureType;
import me.bannock.theworld.data.land.feature.LandFeature;

import java.util.concurrent.ThreadLocalRandom;

public class WaterFeature extends LandFeature {

    public WaterFeature(float mL) {
        super(FeatureType.WATER, mL);
    }

    private int t = 0;

    @Override
    public void simulate(World world, LandLot landLot, int x, int y) {
        int tt = ++t; // This method is extremely heavy on the profiler unless I do this
        if (tt % 7 == 0) {
            count += 3500f / 3;
        }
        if (tt % 50 == 0){
            count *= 1f / ThreadLocalRandom.current().nextFloat(1, 25);
        }
        if (tt % 365 == 0){
            count *= ThreadLocalRandom.current().nextFloat(1, 10);
            count += ThreadLocalRandom.current().nextFloat(1000, 9000);
            t = 0;
        }
    }

}
