package me.bannock.theworld.data.land.factory;

import me.bannock.theworld.data.land.BasicLandLotImpl;
import me.bannock.theworld.data.land.LandLot;
import me.bannock.theworld.data.land.feature.impl.FoodFeature;
import me.bannock.theworld.data.land.feature.impl.WaterFeature;

import java.util.concurrent.ThreadLocalRandom;

public class DiamondsLandLotFactoryImpl implements LandLotFactory {

    private static final int DIAMOND_SIZE = 3;

    @Override
    public LandLot generateLandLot(int x, int y) {
        x += (int)ThreadLocalRandom.current().nextGaussian(0, 1);
        if ((y & 0b1) == 1){
            x *= -1;
        }
        float resourceAmount = 10_000;

        LandLot newLot = new BasicLandLotImpl();
        int asdf = (x + y / 2) / DIAMOND_SIZE;
        if ((asdf & 0b1) == 1){
            newLot.addFeature(new WaterFeature(resourceAmount));
            newLot.addFeature(new FoodFeature(resourceAmount / 2));
        }else{
            newLot.addFeature(new FoodFeature(resourceAmount));
            newLot.addFeature(new WaterFeature(resourceAmount / 2));
        }
        return newLot;
    }

}
