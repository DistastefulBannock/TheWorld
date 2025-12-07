package me.bannock.theworld.data.land;

import me.bannock.theworld.data.World;
import me.bannock.theworld.data.land.feature.FeatureType;
import me.bannock.theworld.data.land.feature.LandFeature;

import java.util.Objects;

public class BasicLandLotImpl implements LandLot {

    private LandFeature features = new LandFeature(FeatureType.GENESIS, 1);

    @Override
    public LandFeature getFeatures() {
        return features;
    }

    @Override
    public void addFeature(LandFeature newFeature) {
        Objects.requireNonNull(newFeature);
        LandFeature current = getFeatures();
        newFeature.setNext(current);
        features = newFeature;
    }

    @Override
    public void removeFeature(LandFeature removeFeature) {
        Objects.requireNonNull(removeFeature);
        if (removeFeature.getType() == FeatureType.GENESIS)
            throw new IllegalArgumentException("Cannot remove genesis block");
        if (features == removeFeature) {
            features = features.getNext();
            return;
        }

        LandFeature feature = features;
        while(feature != null){
            if (feature.getNext() == removeFeature){
                feature.setNext(feature.getNext().getNext());
                return;
            }
            feature = feature.getNext();
        }
    }

    @Override
    public int getFeatureCount() {
        int i = 0;
        LandFeature feature = getFeatures();
        while(feature != null){
            i++;
            feature = feature.getNext();
        }
        return i;
    }

    @Override
    public void simulate(World world, int x, int y) {
        Objects.requireNonNull(world);
        LandFeature feature = getFeatures();
        while(feature != null){
            feature.simulate(world, this, x, y);
            feature = feature.getNext();
        }
    }

}
