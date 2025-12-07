package me.bannock.theworld.data.land.factory;

import me.bannock.theworld.data.land.BasicLandLotImpl;
import me.bannock.theworld.data.land.LandLot;
import me.bannock.theworld.data.land.feature.impl.FoodFeature;
import me.bannock.theworld.data.land.feature.impl.WaterFeature;

public class CheckerBoardFactoryImpl implements LandLotFactory {

    private static final int GRID_SIZE_X = 5, GRID_SIZE_Y = 5;
    private static final float SLOPE = 1.5f;

    @Override
    public LandLot generateLandLot(int x, int y) {
        y = (int) (y * SLOPE);
        x = (int) (x * SLOPE);
        float resourceAmount = 10_000;

        LandLot newLot = new BasicLandLotImpl();

        int chunkX = x / GRID_SIZE_X;
        int chunkY = y / GRID_SIZE_Y;

        if ((chunkY & 0b1) == 1){
            if ((chunkX & 0b1) == 1){
                newLot.addFeature(new WaterFeature(resourceAmount));
                newLot.addFeature(new FoodFeature(resourceAmount / 5));
            }else{
                newLot.addFeature(new FoodFeature(resourceAmount));
                newLot.addFeature(new WaterFeature(resourceAmount / 5));
            }
        }else{
            if ((chunkX & 0b1) == 0){
                newLot.addFeature(new WaterFeature(resourceAmount));
                newLot.addFeature(new FoodFeature(resourceAmount / 5));
            }else{
                newLot.addFeature(new FoodFeature(resourceAmount));
                newLot.addFeature(new WaterFeature(resourceAmount / 5));
            }
        }

        return newLot;
    }

}
