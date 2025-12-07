package me.bannock.theworld.data.land.feature.impl;

import me.bannock.theworld.data.World;
import me.bannock.theworld.data.land.LandLot;
import me.bannock.theworld.data.land.feature.FeatureType;
import me.bannock.theworld.data.land.feature.LandFeature;

import java.util.concurrent.ThreadLocalRandom;

public class FoodFeature extends LandFeature {

    public FoodFeature(float calories) {
        super(FeatureType.FOOD, calories);
    }

    private int t = 0;

    @Override
    public void simulate(World world, LandLot landLot, int x, int y) {
        int tt = ++t; // This method is extremely heavy on the profiler unless I do this
        if (tt % 7 == 0) {
            count += 2100 / 3;
//            if ((t + 182) % 365 == 0){
//                count *= 15;
//            }
//            if (t % 365 == 0)
//                t = 0;
        }
        if (tt % 50 == 0){
            count *= 1f / ThreadLocalRandom.current().nextFloat(15, 45);
        }
        if (tt % 365 == 0){
            count *= ThreadLocalRandom.current().nextFloat(10, 15);
            t = 0;
        }
    }

}
