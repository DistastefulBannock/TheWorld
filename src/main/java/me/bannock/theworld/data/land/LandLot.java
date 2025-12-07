package me.bannock.theworld.data.land;

import me.bannock.theworld.data.World;
import me.bannock.theworld.data.land.feature.LandFeature;

public interface LandLot {

    /**
     * @return The features present at this lot of land, or null if none exist
     */
    LandFeature getFeatures();

    /**
     * Adds a feature to the linked list
     * @param newFeature The feature to add
     */
    void addFeature(LandFeature newFeature);

    /**
     * Removes feature from linked list
     * @param removeFeature The feature to remove
     */
    void removeFeature(LandFeature removeFeature);

    int getFeatureCount();

    /**
     * Simulates one day at this lot of land
     * @param world The world that this land exists within
     * @param x The x coordinate of this lot
     * @param y The y coordinate of this lot
     */
    void simulate(World world, int x, int y);

}
