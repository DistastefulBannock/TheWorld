package me.bannock.theworld.ui;

import me.bannock.theworld.data.World;

import javax.swing.JFrame;

public interface UserInterface {

    /**
     * Creates the window for the graphical representation of a world
     */
    void createWindow();

    /**
     * Controls whether the interface window(s) are currently visible to the user
     * @param visible whether the window is visible to the user
     */
    void setWindowVisible(boolean visible);

    /**
     * Updates all graphics, should be called after new data has been processed
     */
    void updateGraphics();

}
