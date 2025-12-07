package me.bannock.theworld.data.land.factory;

import me.bannock.theworld.data.land.BasicLandLotImpl;
import me.bannock.theworld.data.land.LandLot;
import me.bannock.theworld.data.land.feature.impl.FoodFeature;
import me.bannock.theworld.data.land.feature.impl.WaterFeature;

public class StripesLandLotFactoryImpl implements LandLotFactory {

    @Override
    public LandLot generateLandLot(int x, int y) {
        if (Math.cos(Math.toRadians(x / 10f)) > 0){
            x *= -1;
        }
        if (Math.cos(Math.toRadians(y / 5f)) > 0){
            y *= -1;
        }
        float resourceAmount = 10_000;

        LandLot newLot = new BasicLandLotImpl();
        int asdf = (x + y / 2) / 5;
        if ((asdf & 0b1) == 1){
            newLot.addFeature(new WaterFeature(resourceAmount));
            newLot.addFeature(new FoodFeature(resourceAmount / 5));
        }else{
            newLot.addFeature(new FoodFeature(resourceAmount));
            newLot.addFeature(new WaterFeature(resourceAmount / 5));
        }
        return newLot;
    }

}
