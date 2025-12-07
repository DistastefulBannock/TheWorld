package me.bannock.theworld.ui.swing;

import me.bannock.theworld.data.land.LandLot;

import java.util.Objects;

public class WorldMapInfoNode {

    /**
     * @param x
     * @param y
     * @param watermL
     * @param foodCals
     */
    public WorldMapInfoNode(int x, int y, float watermL, float foodCals){
        this.x = x;
        this.y = y;
        this.watermL = watermL;
        this.foodCals = foodCals;
    }

    private final int x, y;
    private final float watermL, foodCals;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getWatermL() {
        return watermL;
    }

    public float getFoodCals() {
        return foodCals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorldMapInfoNode that)) return false;
        return getX() == that.getX() && getY() == that.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

}
