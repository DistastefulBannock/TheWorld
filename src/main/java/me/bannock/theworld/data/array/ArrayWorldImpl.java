package me.bannock.theworld.data.array;

import me.bannock.theworld.data.World;
import me.bannock.theworld.data.entity.Entity;
import me.bannock.theworld.data.entity.EntityNew;
import me.bannock.theworld.data.land.LandLot;
import me.bannock.theworld.data.land.factory.LandLotFactory;
import me.bannock.theworld.data.land.factory.RandomLandLotFactoryImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ArrayWorldImpl implements World {

    public ArrayWorldImpl(int width, int height, int startingEntities, LandLotFactory landLotFactory){
        if (width < 0)
            throw new IllegalArgumentException("World width cannot be less than 0");
        if (height < 0)
            throw new IllegalArgumentException("World height cannot be less than 0");
        lands = new LandLot[width][height];

        for (int x = 0; x < lands.length; x++){
            for (int y = 0; y < lands[x].length; y++){
                lands[x][y] = landLotFactory.generateLandLot(x, y);
            }
        }

        int entitiesNum = startingEntities;
        for (int i = 0; i < entitiesNum; i++)
            makeChildEntity(new Entity(ThreadLocalRandom.current().nextInt(getWidth()),
                    ThreadLocalRandom.current().nextInt(getHeight())));
//        System.out.printf("Spawned %d entities\n", entitiesNum);
    }

    public ArrayWorldImpl(){
        this(100, 100, 2, new RandomLandLotFactoryImpl());
    }

    private final LandLot[][] lands;
    private EntityNode entities;
    private HashSet<Entity> entitiesToKill = new HashSet<>();
    private boolean mutationsEnabled;

    @Override
    public void simulate() {
        simulateEnvironment();
        simulateEntities();
    }

    private void simulateEntities(){
        EntityNode currentNode = entities;
        while (currentNode != null){
            Entity entity = currentNode.getEntity();
            entity.simulate(this);
            currentNode = currentNode.getNext();
        }

        currentNode = entities;
        while (currentNode != null && currentNode.getNext() != null){
            EntityNode tmpNode = currentNode.getNext();
            if (entitiesToKill.contains(currentNode.getNext().getEntity())){
                currentNode.setNext(currentNode.getNext().getNext());
            }
            currentNode = tmpNode;
        }
        if (entities != null && entitiesToKill.contains(entities.getEntity())){
            this.entities = entities.getNext();
        }
        entitiesToKill.clear();
        if (entities == null){
            throw new RuntimeException("Life did not find a way");
        }
    }

    private void simulateEnvironment(){
        for (int x = 0; x < lands.length; x++){
            for (int y = 0; y < lands[x].length; y++){
                lands[x][y].simulate(this, x, y);
            }
        }
    }

    @Override
    public int getWidth() {
        return lands.length;
    }

    @Override
    public int getHeight() {
        return lands[0].length;
    }

    @Override
    public LandLot getLand(int x, int y) {
        if (x < 0 || y < 0)
            throw new IllegalArgumentException("Bounds cannot be less than 0");
        if (x >= lands.length || y >= lands[x].length)
            throw new IllegalArgumentException("Bounds must exist within the width and height of the world");
        return lands[x][y];
    }

    @Override
    public void killEntity(Entity entityToKill) {
        entitiesToKill.add(entityToKill);
    }

    @Override
    public void makeChildEntity(Entity parent) {
        Entity entity;
        if (mutationsEnabled){
            entity = new EntityNew(parent);
        }else{
            entity = new Entity(parent.getX(), parent.getY());
        }
        EntityNode node = new EntityNode(entity);
        node.setNext(entities);
        entities = node;
//        System.out.printf("Birthed new entity %d from parent %d\n", entity.getId(), parent.getId());
    }

    @Override
    public EntityNode getEntities() {
        return entities;
    }

    @Override
    public void setMutationsEnabled(boolean mutationsEnabled) {
        this.mutationsEnabled = mutationsEnabled;
    }

    @Override
    public boolean isMutationsEnabled() {
        return mutationsEnabled;
    }

}
