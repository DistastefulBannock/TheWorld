package me.bannock.theworld.data.utils;

import me.bannock.theworld.data.land.LandLot;
import me.bannock.theworld.data.land.feature.FeatureType;
import me.bannock.theworld.data.land.feature.LandFeature;

public class LandFeatureUtils {

    public static float getMlOfWaterForLot(LandLot landLot){
        float waterMl = 0;
        LandFeature feature = landLot.getFeatures();
        while(feature != null){
            if (feature.getType() == FeatureType.WATER)
                waterMl += feature.getCount();
            feature = feature.getNext();
        }
        return waterMl;
    }

    public static float drinkWaterAtLot(LandLot landLot, float watermLToDrink){
        LandFeature feature = landLot.getFeatures();
        while(feature != null){
            if (feature.getType() == FeatureType.WATER) {
                feature.setCount(feature.getCount() - watermLToDrink);
                if (feature.getCount() < 0){
                    watermLToDrink = Math.abs(feature.getCount());
                    LandFeature lastFeature = feature;
                    feature = feature.getNext();
//                    lastFeature.setCount(Math.max(0, lastFeature.getCount()));
                    landLot.removeFeature(lastFeature);
                    continue;
                }else{
                    return 0;
                }
            }
            feature = feature.getNext();
        }
        return watermLToDrink;
    }

    public static float getCalOfFoodForLot(LandLot landLot){
        float foodCal = 0;
        LandFeature feature = landLot.getFeatures();
        while(feature != null){
            try{
                if (feature.getType() != FeatureType.FOOD)
                    continue;
                foodCal += feature.getCount();
            }finally {
                feature = feature.getNext();
            }
        }
        return foodCal;
    }

    public static float eatFoodAtLot(LandLot landLot, float calsToEat){
        LandFeature feature = landLot.getFeatures();
        while(feature != null){
            if (feature.getType() == FeatureType.FOOD){
                feature.setCount(feature.getCount() - calsToEat);
                if (feature.getCount() < 0){
                    calsToEat = Math.abs(feature.getCount());
                    LandFeature lastFeature = feature;
                    feature = feature.getNext();
//                    lastFeature.setCount(Math.max(0, lastFeature.getCount()));
                    landLot.removeFeature(lastFeature);
                    continue;
                }else{
                    return 0;
                }
            }
            feature = feature.getNext();
        }
        return calsToEat;
    }

}
