package me.bannock.theworld.ui.swing;

import me.bannock.theworld.data.World;
import me.bannock.theworld.data.array.ArrayWorldImpl;
import me.bannock.theworld.data.land.factory.CheckerBoardFactoryImpl;
import me.bannock.theworld.data.land.factory.DiamondsLandLotFactoryImpl;
import me.bannock.theworld.data.land.factory.GooberLandLotFactoryImpl;
import me.bannock.theworld.data.land.factory.LandLotFactory;
import me.bannock.theworld.data.land.factory.NoiseLandLotFactoryImpl;
import me.bannock.theworld.data.land.factory.RandomLandLotFactoryImpl;
import me.bannock.theworld.data.land.factory.StripesLandLotFactoryImpl;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class SwingStartupMenu extends JPanel {

    private static final int MENU_ITEM_PADDING = 3;
    private static final String X_CHARACTER = "\u2715";
    private static final String CHECK_CHARACTER = "\u2713";

    public SwingStartupMenu(SwingUserInterfaceImpl parent){
        List<JPanel> menuListings = new ArrayList<>();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.widthSlider = new JSlider(3, (int)screenSize.getWidth(), 225);
        JLabel widthValueOutput = new JLabel(widthSlider.getValue() + "");
        widthSlider.addChangeListener(evt -> {
            widthValueOutput.setText(widthSlider.getValue() + "");
            widthValueOutput.getParent().repaint();
        });
        menuListings.add(formatStartupMenuListing("World width", widthValueOutput, widthSlider));

        this.heightSlider = new JSlider(3, (int)screenSize.getHeight(), 225);
        JLabel heightValueOutput = new JLabel(heightSlider.getValue() + "");
        heightSlider.addChangeListener(evt -> {
            heightValueOutput.setText(heightSlider.getValue() + "");
            heightValueOutput.getParent().repaint();
        });
        menuListings.add(formatStartupMenuListing("World height", heightValueOutput, heightSlider));

        this.dayFrameRatioRatioSlider = new JSlider(1, 31, parent.getDayFrameRatio());
        JLabel dayFrameRatioOutput = new JLabel(dayFrameRatioRatioSlider.getValue() + "");
        dayFrameRatioRatioSlider.addChangeListener(evt -> {
            dayFrameRatioOutput.setText(dayFrameRatioRatioSlider.getValue() + "");
            parent.setDayFrameRatio(dayFrameRatioRatioSlider.getValue());
            dayFrameRatioOutput.getParent().repaint();
        });
        menuListings.add(formatStartupMenuListing("Take frame every x days", dayFrameRatioOutput, dayFrameRatioRatioSlider));

        this.entityCountSlider = new JSlider(1, 50, 1);
        JLabel entityCountOutput = new JLabel(entityCountSlider.getValue() + "");
        entityCountSlider.addChangeListener(evt -> {
            entityCountOutput.setText(entityCountSlider.getValue() + "");
            entityCountOutput.getParent().repaint();
        });
        menuListings.add(formatStartupMenuListing("Starting entity count", entityCountOutput, entityCountSlider));

        JSlider pixelSize = new JSlider(1, 10, SwingUserInterfaceImpl.PIXELS_PER_LANDLOT);
        JLabel pixelSizeOutput = new JLabel(pixelSize.getValue() + "");
        pixelSize.addChangeListener(evt -> {
            pixelSizeOutput.setText(pixelSize.getValue() + "");
            pixelSizeOutput.getParent().repaint();
            SwingUserInterfaceImpl.PIXELS_PER_LANDLOT = pixelSize.getValue();
        });
        menuListings.add(formatStartupMenuListing("Pixel size", pixelSizeOutput, pixelSize));

        Class<LandLotFactory>[] factories = new Class[]{
                NoiseLandLotFactoryImpl.class, CheckerBoardFactoryImpl.class, DiamondsLandLotFactoryImpl.class,
                RandomLandLotFactoryImpl.class, StripesLandLotFactoryImpl.class, GooberLandLotFactoryImpl.class
        };
        this.landFactories = new JComboBox<>(factories);
        menuListings.add(formatStartupMenuListing("Land factory", landFactories));

        this.mutations = new JToggleButton(CHECK_CHARACTER, true);
        mutations.addChangeListener(evt -> {
            boolean toggled = mutations.isSelected();
            mutations.setText(toggled ? CHECK_CHARACTER : X_CHARACTER);
        });
        menuListings.add(formatStartupMenuListing("Child traits mutate from parents?", mutations));

        this.saveFrames = new JToggleButton(parent.isSaveFrames() ? CHECK_CHARACTER : X_CHARACTER, parent.isSaveFrames());
        saveFrames.addChangeListener(evt -> {
            boolean toggled = saveFrames.isSelected();
            parent.setSaveFrames(toggled, creationTime);
            saveFrames.setText(toggled ? CHECK_CHARACTER : X_CHARACTER);
        });
        menuListings.add(formatStartupMenuListing(
                "Save frames as pictures? (./%d/*.png)".formatted(creationTime), saveFrames));

        this.createButton = new JButton("Create world!");
        createButton.addActionListener(evt -> {
            LandLotFactory landFactory;
            try {
                Constructor<?> ctor = ((Class<LandLotFactory>)landFactories.getSelectedItem()).getConstructor();
                landFactory = (LandLotFactory) ctor.newInstance(new Object[0]);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            World world = new ArrayWorldImpl(
                    widthSlider.getValue(), heightSlider.getValue(), entityCountSlider.getValue(), landFactory);
            world.setMutationsEnabled(mutations.isSelected());
            parent.setWorld(world);
            parent.refreshWindowSize();
            parent.updateGraphics();
        });
        menuListings.add(formatStartupMenuListing(null, createButton));

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(menuListings.size(), 1));
        menuListings.forEach(container::add);

        setLayout(new BorderLayout());
        add(container, BorderLayout.NORTH);
    }

    private final JSlider widthSlider;
    private final JSlider heightSlider;
    private final JSlider dayFrameRatioRatioSlider;
    private final JSlider entityCountSlider;
    private final JComboBox<Class<LandLotFactory>> landFactories;
    private final JToggleButton mutations;
    private final JToggleButton saveFrames;
    private final JButton createButton;
    private final long creationTime = System.currentTimeMillis();

    private int listingCount = 0;

    /**
     * @param settingName The name of the setting, or null if none should be shown
     * @param controllers The components the user can use to control the setting
     * @return A jpanel containing both that can be listed in the start menu
     */
    private JPanel formatStartupMenuListing(String settingName, Component... controllers){
        JPanel menuListing = new JPanel();
        menuListing.setLayout(new BorderLayout());
        menuListing.setBorder(new EmptyBorder(MENU_ITEM_PADDING, MENU_ITEM_PADDING, MENU_ITEM_PADDING, MENU_ITEM_PADDING));
        if ((++listingCount & 0b1) == 1){
            menuListing.setBackground(Color.lightGray);
        }

        if (settingName != null)
            menuListing.add(new JLabel(settingName + ": "), BorderLayout.WEST);
        if (controllers.length == 1)
            menuListing.add(controllers[0], BorderLayout.EAST);
        else if (controllers.length > 1){
            JPanel bar = new JPanel(true);
            bar.setLayout(new BoxLayout(bar, BoxLayout.X_AXIS));
            for (Component component : controllers) bar.add(component);
            if ((listingCount & 0b1) == 1)
                bar.setBackground(Color.lightGray);
            menuListing.add(bar, BorderLayout.EAST);
        }
        return menuListing;
    }

}
