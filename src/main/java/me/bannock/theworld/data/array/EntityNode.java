package me.bannock.theworld.data.array;

import me.bannock.theworld.data.entity.Entity;

import java.util.Objects;

public class EntityNode {

    public EntityNode(Entity entity){
        Objects.requireNonNull(entity);
        this.entity = entity;
    }

    private Entity entity;
    private EntityNode next;

    public Entity getEntity() {
        return entity;
    }

    public EntityNode getNext() {
        return next;
    }

    public void setNext(EntityNode next) {
        this.next = next;
    }

}
