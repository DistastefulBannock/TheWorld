package me.bannock.theworld.data;

import me.bannock.theworld.data.array.EntityNode;
import me.bannock.theworld.data.entity.Entity;
import me.bannock.theworld.data.land.LandLot;

import java.util.List;

public interface World {

    /**
     * Simulates the world
     */
    void simulate();

    /**
     * @return The width of the world
     */
    int getWidth();

    /**
     * @return The height of the world
     */
    int getHeight();

    LandLot getLand(int x, int y);

    void killEntity(Entity entityToKill);

    void makeChildEntity(Entity parent);

    EntityNode getEntities();

    /**
     * @param mutationsEnabled Whether traits of entity children should be random or a branch from the parent's original traits
     */
    void setMutationsEnabled(boolean mutationsEnabled);

    /**
     * @return Whether traits of entity children should be random or a branch from the parent's original traits
     */
    boolean isMutationsEnabled();

}
