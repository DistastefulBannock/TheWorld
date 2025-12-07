package me.bannock.theworld.data.land.factory;

import me.bannock.theworld.data.land.BasicLandLotImpl;
import me.bannock.theworld.data.land.LandLot;
import me.bannock.theworld.data.land.feature.LandFeature;
import me.bannock.theworld.data.land.feature.impl.FoodFeature;
import me.bannock.theworld.data.land.feature.impl.WaterFeature;

import java.util.concurrent.ThreadLocalRandom;

public class RandomLandLotFactoryImpl implements LandLotFactory {

    @Override
    public LandLot generateLandLot(int x, int y) {
        LandLot newLot = new BasicLandLotImpl();
        int baseBound = 1700;
        if (ThreadLocalRandom.current().nextBoolean() && ThreadLocalRandom.current().nextBoolean()
                && ThreadLocalRandom.current().nextBoolean() && ThreadLocalRandom.current().nextBoolean()
                && ThreadLocalRandom.current().nextBoolean() && ThreadLocalRandom.current().nextBoolean()){
            baseBound *= 2;
        }
        if (ThreadLocalRandom.current().nextBoolean())
            for (int i = 0; i < ThreadLocalRandom.current().nextInt(4); i++){
                newLot.addFeature(new WaterFeature(ThreadLocalRandom.current().nextFloat(baseBound + 300)));
            }
        if (ThreadLocalRandom.current().nextBoolean())
            for (int i = 0; i < ThreadLocalRandom.current().nextInt(4); i++){
                newLot.addFeature(new FoodFeature(ThreadLocalRandom.current().nextFloat(baseBound + 50)));
            }

        float waterMl = 0;
        float foodCals = 0;
        LandFeature feature = newLot.getFeatures();
        while(feature != null){
            switch (feature.getType()){
                case WATER:{
                    waterMl += feature.getCount();
                }break;
                case FOOD:{
                    foodCals += feature.getCount();
                }break;
            }
            feature = feature.getNext();
        }

//        System.out.printf("Created lot with %d features (%fmL water & %fcals food)\n",
//                newLot.getFeatureCount(), waterMl, foodCals);
        return newLot;
    }

}
