package me.bannock.theworld.data.land.factory;

import me.bannock.theworld.data.land.BasicLandLotImpl;
import me.bannock.theworld.data.land.LandLot;
import me.bannock.theworld.data.land.feature.impl.FoodFeature;
import me.bannock.theworld.data.land.feature.impl.WaterFeature;

import java.util.concurrent.ThreadLocalRandom;

public class GooberLandLotFactoryImpl implements LandLotFactory {

    private static final int GRID_SIZE_X = 2, GRID_SIZE_Y = 2;
    private static final float SLOPE = 1;

    @Override
    public LandLot generateLandLot(int x, int y) {
        y = (int) (y * SLOPE);
        x = (int) (x * SLOPE);
        float resourceAmount = 10_000;

        LandLot newLot = new BasicLandLotImpl();

        int chunkX = x / GRID_SIZE_X;
        int chunkY = y / GRID_SIZE_Y;

        int test = chunkY / 3;
        if (chunkX / 3 == test){
            newLot.addFeature(new WaterFeature(resourceAmount));
            newLot.addFeature(new FoodFeature(resourceAmount / 5));
        }

        return newLot;
    }

}
