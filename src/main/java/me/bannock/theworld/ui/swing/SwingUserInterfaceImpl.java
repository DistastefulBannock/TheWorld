package me.bannock.theworld.ui.swing;

import me.bannock.theworld.data.World;
import me.bannock.theworld.data.array.EntityNode;
import me.bannock.theworld.data.entity.Entity;
import me.bannock.theworld.ui.UserInterface;
import me.bannock.theworld.ui.swing.img.WorldMapWithImageOutput;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;

public class SwingUserInterfaceImpl extends JFrame implements UserInterface {

    public static final String WINDOW_TITLE = "Populationing simulator 26";
    public static final int BORDER_SIZE = 4;
    public static final int CONTROL_PANEL_HEIGHT = 8;

    public SwingUserInterfaceImpl(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    protected static int PIXELS_PER_LANDLOT = 3;

    public static int getPixelsPerLandlot() {
        return PIXELS_PER_LANDLOT;
    }

    /**
     * The world itself is null until the user enters the configuration details in the startup menu
     */
    private World world = null;
    /**
     * The worldpane is the main screen of the window. It is null until the window is created
     */
    private JPanel worldPane = null;
    private JPanel bottomControlPane;
    private JLabel infoLabel;
    private boolean saveFrames;
    private File currentFramesFolder = null;
    private int dayFrameRatio = 1;


    @Override
    public void createWindow() {
        if (this.worldPane != null)
            throw new IllegalStateException("Cannot create window twice!");
        this.worldPane = new JPanel(true);
        worldPane.setLayout(new BoxLayout(worldPane, BoxLayout.Y_AXIS));
        worldPane.add(new SwingStartupMenu(this));

        refreshWindowSize();
        setTitle(WINDOW_TITLE);

        this.bottomControlPane = new JPanel();
        bottomControlPane.setLayout(new BoxLayout(bottomControlPane, BoxLayout.X_AXIS));
        bottomControlPane.setSize(Integer.MAX_VALUE, CONTROL_PANEL_HEIGHT);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
        contentPane.setLayout(new BorderLayout());
        contentPane.add(worldPane, BorderLayout.CENTER);
        contentPane.add(bottomControlPane, BorderLayout.SOUTH);
        setContentPane(contentPane);
    }

    protected void refreshWindowSize(){
        int worldPaneWidth = 200 * PIXELS_PER_LANDLOT;
        int worldPaneHeight = 200 * PIXELS_PER_LANDLOT;
        if (world != null){
            worldPaneWidth = world.getWidth() * PIXELS_PER_LANDLOT;
            worldPaneHeight = world.getHeight() * PIXELS_PER_LANDLOT;
        }

        worldPane.setSize(worldPaneWidth, worldPaneHeight);

        int windowWidth = worldPaneWidth + BORDER_SIZE * 2;
        int windowHeight = worldPaneHeight + BORDER_SIZE * 2 + CONTROL_PANEL_HEIGHT;
        setSize(windowWidth, windowHeight);
    }

    @Override
    public void setWindowVisible(boolean visible) {
        setVisible(visible);
    }

    @Override
    public void updateGraphics() {
//        infoLabel.setText("Pop %d".formatted(world.getEntities().size()));
        infoLabel.repaint();
        worldPane.repaint();
//        worldPane.paintImmediately(0, 0, world.getWidth() * PIXELS_PER_LANDLOT, world.getWidth() * PIXELS_PER_LANDLOT);
    }

    public World getWorld() {
        return world;
    }

    protected void setWorld(World world) {
        this.world = world;
        getContentPane().remove(worldPane);
        worldPane = new JPanel(true);
        worldPane.setLayout(new GridLayout(1, 1));

        worldPane.removeAll();
        if (saveFrames){
            worldPane.add(new WorldMapWithImageOutput(world, currentFramesFolder));
        }else{
            worldPane.add(new WorldMap(world));
        }
        getContentPane().add(worldPane, BorderLayout.CENTER);
        worldPane.revalidate();

        JButton neverStopRunning = new JButton("Play");
        neverStopRunning.addActionListener(evt -> {
            bottomControlPane.remove(neverStopRunning);

            Thread awesomeRunningThread = new Thread(() -> {
                try{
                    for (;;){
                        updateGraphics();
                        for (int i = 0; i < dayFrameRatio; i++)
                            world.simulate();
                    }
                }catch (RuntimeException e){
                    e.printStackTrace();
                    updateGraphics();
                    infoLabel.setText("Everyone's dead");
                }
            });
            awesomeRunningThread.setName("Awesome running thread");

            JButton reset = new JButton("Reset");
            reset.addActionListener(evt1 -> {
                bottomControlPane.removeAll();
                bottomControlPane.revalidate();
                bottomControlPane.repaint();

                awesomeRunningThread.stop();
                this.world = null;
                worldPane.setLayout(new BoxLayout(worldPane, BoxLayout.Y_AXIS));
                worldPane.removeAll();
                worldPane.add(new SwingStartupMenu(this));
                infoLabel.repaint();
                worldPane.revalidate();
                worldPane.repaint();
            });
            bottomControlPane.add(reset);
            bottomControlPane.revalidate();
            bottomControlPane.repaint();

            awesomeRunningThread.start();
        });
        bottomControlPane.add(neverStopRunning);
        infoLabel = new JLabel();
        bottomControlPane.add(infoLabel);
    }

    public boolean isSaveFrames() {
        return saveFrames;
    }

    public void setSaveFrames(boolean saveFrames, long folderId) {
        this.saveFrames = saveFrames;
        if (saveFrames){
            this.currentFramesFolder = new File("./%d/".formatted(folderId));
            currentFramesFolder.mkdirs();
        }
    }

    public int getDayFrameRatio() {
        return dayFrameRatio;
    }

    public void setDayFrameRatio(int dayFrameRatio) {
        this.dayFrameRatio = dayFrameRatio;
    }

}
