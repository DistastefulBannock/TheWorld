package me.bannock.theworld.data.land.feature.impl;

import me.bannock.theworld.data.land.feature.FeatureType;
import me.bannock.theworld.data.land.feature.LandFeature;

public class StaticWaterFeature extends LandFeature {

    public StaticWaterFeature(float mL) {
        super(FeatureType.WATER, mL);
    }

}
