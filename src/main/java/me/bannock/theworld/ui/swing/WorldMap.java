package me.bannock.theworld.ui.swing;

import me.bannock.theworld.data.World;
import me.bannock.theworld.data.array.EntityNode;
import me.bannock.theworld.data.entity.Entity;
import me.bannock.theworld.data.land.LandLot;
import me.bannock.theworld.data.utils.LandFeatureUtils;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.util.HashMap;

public class WorldMap extends JComponent {

    public WorldMap(World world){
        this.world = world;
        setDoubleBuffered(true);
    }

    protected final World world;
//    private int maxEntities = 0;
//    private float maxWatermL = 0;
//    private float maxFoodCals = 0;

    @Override
    protected void paintComponent(Graphics gr) {
        Graphics2D graphics2D = (Graphics2D) gr;
        graphics2D.setColor(Color.BLACK);

        int maxEntities = 0;
        HashMap<WorldMapInfoNode, Integer> entityStats = new HashMap<>();
        EntityNode currentNode = world.getEntities();
        while (currentNode != null){
            Entity entity = currentNode.getEntity();
            WorldMapInfoNode fakeNode = new WorldMapInfoNode(entity.getX(), entity.getY(), 0, 0);
            if (!entityStats.containsKey(fakeNode)){
                entityStats.put(fakeNode, 1);

            }
            entityStats.put(fakeNode, entityStats.get(fakeNode) + 1);
            maxEntities = Math.max(maxEntities, entityStats.get(fakeNode));
            currentNode = currentNode.getNext();
        }
        if (maxEntities == 0)
            maxEntities = 1;

        HashMap<WorldMapInfoNode, WorldMapInfoNode> landStats = new HashMap<>();
        float maxWatermL = 0;
        float maxFoodCals = 0;
        for (int x = 0; x < world.getWidth(); x++){
            for (int y = 0; y < world.getHeight(); y++){
                LandLot landLot = world.getLand(x, y);

                int watermL = (int)LandFeatureUtils.getMlOfWaterForLot(landLot);
                maxWatermL = Math.max(maxWatermL, watermL);
                int foodCals = (int)LandFeatureUtils.getCalOfFoodForLot(landLot);
                maxFoodCals = Math.max(maxFoodCals, foodCals);

                WorldMapInfoNode worldMapInfoNode = new WorldMapInfoNode(x, y, watermL, foodCals);
                landStats.put(worldMapInfoNode, worldMapInfoNode);
            }
        }

        for (int x = 0; x < world.getWidth(); x++){
            for (int y = 0; y < world.getHeight(); y++){
                WorldMapInfoNode worldMapInfoNode = landStats.get(new WorldMapInfoNode(x, y, 0, 0));

                float foodCals = worldMapInfoNode.getFoodCals();
                float r = foodCals / maxFoodCals;

                int entities = entityStats.getOrDefault(worldMapInfoNode, 0);
//                float g = (float)entities / maxEntities;
                float g = entities > 0 ? 0.25f : 0;
                g = Math.min(1f, Math.max(g, (float)entities / maxEntities));

                float watermL = worldMapInfoNode.getWatermL();
                float b = Math.max(Math.min(watermL / maxWatermL, 1), 0);

                graphics2D.setColor(new Color(r, g, b));
                graphics2D.fillRect(
                        x * SwingUserInterfaceImpl.PIXELS_PER_LANDLOT,
                        y * SwingUserInterfaceImpl.PIXELS_PER_LANDLOT,
                        SwingUserInterfaceImpl.PIXELS_PER_LANDLOT, SwingUserInterfaceImpl.PIXELS_PER_LANDLOT
                );
            }
        }
    }

    @Override
    public int getWidth() {
        return world.getWidth() * SwingUserInterfaceImpl.PIXELS_PER_LANDLOT;
    }

    @Override
    public int getHeight() {
        return world.getWidth() * SwingUserInterfaceImpl.PIXELS_PER_LANDLOT;
    }

}
