package me.bannock.theworld.ui.swing.img;

import me.bannock.theworld.data.World;
import me.bannock.theworld.ui.swing.SwingUserInterfaceImpl;
import me.bannock.theworld.ui.swing.WorldMap;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WorldMapWithImageOutput extends WorldMap {

    public WorldMapWithImageOutput(World world, File saveFolder) {
        super(world);
        this.saveFolder = saveFolder;
    }

    private final File saveFolder;
    private int frameId = 0;

    @Override
    protected void paintComponent(Graphics gr) {
        BufferedImage currentFrame = new BufferedImage(
                world.getWidth() * SwingUserInterfaceImpl.getPixelsPerLandlot(),
                world.getHeight() * SwingUserInterfaceImpl.getPixelsPerLandlot(),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = new GraphicsWrapper((Graphics2D) gr, currentFrame.createGraphics());
        super.paintComponent(graphics2D);

        File outputFile = new File(saveFolder, "%d.png".formatted(++frameId));
        try {
            ImageIO.write(currentFrame, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
