package me.bannock.theworld.data.entity;

import me.bannock.theworld.Main;
import me.bannock.theworld.data.World;
import me.bannock.theworld.data.land.LandLot;
import me.bannock.theworld.data.utils.LandFeatureUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Entity {

    public Entity(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static long globalId = 0;

    private long id = globalId++;
    private int x, y;
    private float minCals = ThreadLocalRandom.current().nextFloat(600, 1200);
    private float maxCals = ThreadLocalRandom.current().nextFloat(minCals, 3000);
    private float calsNeededEachDay = minCals * (1/7f);
    private float minWatermL = ThreadLocalRandom.current().nextFloat(1000, 2700);
    private float maxWatermL = ThreadLocalRandom.current().nextFloat(minWatermL, 3700);
    private float watermLNeededEachDay = minWatermL * (1/3f);
    private int strikes = 0, maxStrikes = ThreadLocalRandom.current().nextInt(3);
    private int offspringCount = 1;

    private float cals = maxCals, watermL = maxWatermL;

    public void simulate(World world){
        cals -= calsNeededEachDay;
        watermL -= watermLNeededEachDay;

        move(world);
        LandLot landLot = world.getLand(x, y);

        float waterToDrink = maxWatermL - watermL;
        if (waterToDrink > 0){
            watermL += waterToDrink;
            watermL -= LandFeatureUtils.drinkWaterAtLot(landLot, waterToDrink);
        }

        float calsToEat = maxCals - cals;
        if (calsToEat > 0){
            cals += calsToEat;
            cals -= LandFeatureUtils.eatFoodAtLot(landLot, calsToEat);
        }

        if (((int)cals) == (int)maxCals && ((int)watermL) == (int)maxWatermL){
            watermL = (maxWatermL + minWatermL) / 2;
            cals = (maxCals + minCals) / 2;
            for (int i = 0; i < offspringCount; i++)
                world.makeChildEntity(this);
        }

        if (cals < minCals){
            die(world, "lack of cals (%f < %f)".formatted(cals, minCals));
            Main.starve++;
        }
        else if (watermL < minWatermL){
            die(world, "lack of water (%f < %f)".formatted(watermL, minWatermL));
            Main.parch++;
        }else{
            strikes = 0;
        }
    }

    private void move(World world){
        int[][] offsets = new int[][]{
                {0, 0}, // Current position
                {1, 0}, {-1, 0}, {0, 1}, {0, -1}, // Sides
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1}, // Corners
                {-2, 0}, {2, 0}, {0, -2}, {0, 2}, // Double straights
                {-1, 2}, {1, 2}, {-2, 1}, {2, 1}, {-2, -1}, {2, -1}, {-1, -2}, {1, -2} // Extended range
        };
        shuffleArray(offsets);
        PriorityQueue<int[]> options = new PriorityQueue<>(Comparator.comparingDouble(in -> {
            int newX = x + in[0];
            int newY = y + in[1];
            if (newX < 0 || newY < 0 || newX >= world.getWidth() || newY >= world.getHeight())
                return 1;
            LandLot landLot = world.getLand(newX, newY);
            float water = LandFeatureUtils.getMlOfWaterForLot(landLot);
            float cals = LandFeatureUtils.getCalOfFoodForLot(landLot);
            if (starving() && !parching()){
                return -cals;
            }
            else if (parching() && !starving()){
                return -water;
            }
            return -(water + cals);
        }));
        options.addAll(Arrays.asList(offsets));
        int[] bestOffsets = options.poll();
        if (bestOffsets[0] == 0 && bestOffsets[1] == 0)
            return;
        x += bestOffsets[0];
        y += bestOffsets[1];
//        LandLot oldLand = world.getLand(x - bestOffsets[0], y - bestOffsets[1]);
//        LandLot newLand = world.getLand(x, y);
//        System.out.printf("Moving %d from (%d, %d) to (%d, %d); (%fmL, %fcals)->((%fmL, %fcals))\n",
//                id, x - bestOffsets[0], y - bestOffsets[1], x, y,
//                LandFeatureUtils.getMlOfWaterForLot(oldLand), LandFeatureUtils.getCalOfFoodForLot(oldLand),
//                LandFeatureUtils.getMlOfWaterForLot(newLand), LandFeatureUtils.getCalOfFoodForLot(newLand));
    }

    private void shuffleArray(int[][] array) {
        for (int i = array.length; i > 0; i--) {
            int index = ThreadLocalRandom.current().nextInt(i);
            int[] item = array[index];
            array[index] = array[i - 1];
            array[i - 1] = item;
        }
    }

    private boolean starving(){
        return cals < minCals;
    }

    private boolean parching(){
        return watermL < minWatermL;
    }

    public long getId() {
        return id;
    }

    private void die(World world, String dueTo){
        if (++strikes < maxStrikes)
            return;
        Objects.requireNonNull(world);
        Objects.requireNonNull(dueTo);
        LandLot land = world.getLand(x, y);
//        System.out.printf("Entity %d has died due to %s; coords (%d, %d); (%fmL, %fcals)\n",
//                id, dueTo, x, y, LandFeatureUtils.getMlOfWaterForLot(land), LandFeatureUtils.getCalOfFoodForLot(land));
        world.killEntity(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity entity)) return false;
        return getId() == entity.getId();
    }

    public float getMinCals() {
        return minCals;
    }

    public float getMaxCals() {
        return maxCals;
    }

    public float getCalsNeededEachDay() {
        return calsNeededEachDay;
    }

    public float getMinWatermL() {
        return minWatermL;
    }

    public float getMaxWatermL() {
        return maxWatermL;
    }

    public float getWatermLNeededEachDay() {
        return watermLNeededEachDay;
    }

    public int getStrikes() {
        return strikes;
    }

    public int getMaxStrikes() {
        return maxStrikes;
    }

    public float getCals() {
        return cals;
    }

    public float getWatermL() {
        return watermL;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    protected void setMinCals(float minCals) {
        this.minCals = minCals;
    }

    protected void setMaxCals(float maxCals) {
        this.maxCals = maxCals;
    }

    protected void setCalsNeededEachDay(float calsNeededEachDay) {
        this.calsNeededEachDay = calsNeededEachDay;
    }

    protected void setMinWatermL(float minWatermL) {
        this.minWatermL = minWatermL;
    }

    protected void setMaxWatermL(float maxWatermL) {
        this.maxWatermL = maxWatermL;
    }

    protected void setWatermLNeededEachDay(float watermLNeededEachDay) {
        this.watermLNeededEachDay = watermLNeededEachDay;
    }

    protected void setStrikes(int strikes) {
        this.strikes = strikes;
    }

    protected void setMaxStrikes(int maxStrikes) {
        this.maxStrikes = maxStrikes;
    }

    protected void setCals(float cals) {
        this.cals = cals;
    }

    protected void setWatermL(float watermL) {
        this.watermL = watermL;
    }

    public int getOffspringCount() {
        return offspringCount;
    }

    protected void setOffspringCount(int offspringCount) {
        this.offspringCount = offspringCount;
    }

}
