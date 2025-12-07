package me.bannock.theworld.data.land.feature;

import me.bannock.theworld.data.land.LandLot;
import me.bannock.theworld.data.World;

public class LandFeature {

    public LandFeature(FeatureType type, float count) {
        this.type = type;
        this.count = count;
    }


    /**
     * Simulates one day at this land feature
     * @param world The world that this land exists within
     * @param landLot The lot of land that this feature exists within
     * @param x The x coordinate of the lot this feature exists within
     * @param y The y coordinate of the lot this feature exists within
     */
    public void simulate(World world, LandLot landLot, int x, int y){}

    /**
     * Type of feature
     */
    private FeatureType type;

    /**
     * The amount of this feature that are present
     */
    protected float count;

    /**
     * The next feature in the linked list, or null if the end
     */
    private LandFeature next = null;

    public FeatureType getType() {
        return type;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public LandFeature getNext() {
        return next;
    }

    public void setNext(LandFeature next) {
        this.next = next;
    }

}
