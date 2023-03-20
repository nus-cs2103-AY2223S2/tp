package seedu.address.commons.core;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Serializable class that contains the GUI settings.
 * Guarantees: immutable.
 */
public class GuiSettings implements Serializable {

    /**
     * The current elements being displayed
     */
    public enum GuiMode { DISPLAY_TANKS_TASKS, DISPLAY_FISHES_TASKS }

    private static final double DEFAULT_HEIGHT = 600;
    private static final double DEFAULT_WIDTH = 740;
    private static final GuiMode DEFAULT_GUI_MODE = GuiMode.DISPLAY_TANKS_TASKS;

    private final double windowWidth;
    private final double windowHeight;
    private final Point windowCoordinates;
    private final GuiMode guiMode;


    /**
     * Constructs a {@code GuiSettings} with the default height, width and position.
     */
    public GuiSettings() {
        windowWidth = DEFAULT_WIDTH;
        windowHeight = DEFAULT_HEIGHT;
        windowCoordinates = null; // null represent no coordinates
        guiMode = DEFAULT_GUI_MODE;
    }

    /**
     * Constructs a {@code GuiSettings} with the specified height, width and position.
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);
        guiMode = DEFAULT_GUI_MODE;
    }

    /**
     * Constructs a {@code GuiSettings} with the specified height, width, position, and guiMode.
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition, GuiMode guiMode) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);
        this.guiMode = guiMode;
    }

    private GuiSettings(double windowWidth, double windowHeight, Point windowCoordinates, GuiMode guiMode) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.windowCoordinates = windowCoordinates;
        this.guiMode = guiMode;
    }

    public GuiSettings changeGuiMode(GuiMode newMode) {
        return new GuiSettings(windowWidth, windowHeight, windowCoordinates, newMode);
    }

    public double getWindowWidth() {
        return windowWidth;
    }

    public double getWindowHeight() {
        return windowHeight;
    }

    public Point getWindowCoordinates() {
        return windowCoordinates != null ? new Point(windowCoordinates) : null;
    }

    public GuiMode getGuiMode() {
        return guiMode;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof GuiSettings)) { //this handles null as well.
            return false;
        }

        GuiSettings o = (GuiSettings) other;

        return windowWidth == o.windowWidth
                && windowHeight == o.windowHeight
                && Objects.equals(windowCoordinates, o.windowCoordinates)
                && guiMode == o.guiMode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(windowWidth, windowHeight, windowCoordinates, guiMode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Width : " + windowWidth + "\n");
        sb.append("Height : " + windowHeight + "\n");
        sb.append("Position : " + windowCoordinates);
        return sb.toString();
    }
}
