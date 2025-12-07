package me.bannock.theworld.data.land.factory;

import me.bannock.theworld.data.land.LandLot;

public interface LandLotFactory {

    /**
     * Generates a land lot with features for the provided x and y
     * @param x The x coord of the land lot
     * @param y The y coord of the land lot
     * @return The generated land lot
     */
    LandLot generateLandLot(int x, int y);

}
