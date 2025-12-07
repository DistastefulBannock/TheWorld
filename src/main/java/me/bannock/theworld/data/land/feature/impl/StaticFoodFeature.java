package me.bannock.theworld.data.land.feature.impl;

import me.bannock.theworld.data.land.feature.FeatureType;
import me.bannock.theworld.data.land.feature.LandFeature;

public class StaticFoodFeature extends LandFeature {

    public StaticFoodFeature(float calories) {
        super(FeatureType.FOOD, calories);
    }

}
