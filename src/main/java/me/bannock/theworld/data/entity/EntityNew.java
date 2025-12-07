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

public class EntityNew extends Entity {

    public EntityNew(int x, int y){
        super(x, y);
    }

    public EntityNew(Entity parent){
        super(parent.getX(), parent.getY());
        setMinCals(parent.getMinCals());
        setMaxCals(parent.getMaxCals());
        setCalsNeededEachDay(parent.getCalsNeededEachDay());
        setMinWatermL(parent.getMinWatermL());
        setMaxWatermL(parent.getMaxWatermL());
        setWatermLNeededEachDay(parent.getWatermLNeededEachDay());
        setStrikes(parent.getStrikes());
        setMaxStrikes(parent.getMaxStrikes());
        setCals(getMinCals());
        setWatermL(getMinWatermL());
        setOffspringCount(getOffspringCount());

        switch (ThreadLocalRandom.current().nextInt(1000)){
            case 0:{
                setMaxStrikes(getStrikes() + 1);
            }break;
            case 1:{
                setMinCals(getMinCals() + getMinWatermL() / 2);
                setMaxCals(getMinCals() + getMaxWatermL() / 2);
                setMinWatermL(getMinWatermL() / 2);
                setMaxWatermL(getMaxWatermL() / 2);
                setWatermLNeededEachDay(getWatermLNeededEachDay() * 0.9f);
            }break;
            case 2:{
                setMinWatermL(getMinWatermL() + getMinWatermL() / 2);
                setMaxWatermL(getMaxWatermL() + getMaxWatermL() / 2);
                setMinCals(getMinCals() / 2);
                setMaxCals(getMinCals() / 2);
                setCalsNeededEachDay(getCalsNeededEachDay() * 0.9f);
            }break;
            case 3:{
                setMaxCals(getMaxCals() * 2);
            }break;
            case 4:{
                setMaxWatermL(getMaxWatermL() * 2);
            }break;
            case 5:{
                setMaxStrikes(getMaxStrikes() + 15);
            }break;
            case 6:{
                setOffspringCount(getOffspringCount() + 1);
            }break;
            case 7:{
                setOffspringCount(getOffspringCount() + 2);
            }break;
            case 8:{
                setMaxWatermL(Integer.MAX_VALUE);
                setMinCals(0);
                setMaxCals(0);
            }break;
            case 9:{
                setMaxCals(Integer.MAX_VALUE);
                setMinWatermL(0);
                setMaxWatermL(0);
            }break;
            case 10:{
                setStrikes(-getMaxStrikes());
            }break;
            case 11:{
                setMinWatermL(getMinWatermL() / 2);
            }break;
        }
    }

}
