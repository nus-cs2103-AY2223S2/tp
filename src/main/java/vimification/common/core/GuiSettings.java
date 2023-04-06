package vimification.common.core;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Serializable class that contains the GUI settings. Guarantees: immutable.
 */
public class GuiSettings implements Serializable {

    private static final double DEFAULT_HEIGHT = 600;
    private static final double DEFAULT_WIDTH = 740;

    private final double windowWidth;
    private final double windowHeight;
    private final Point windowCoordinates;

    /**
     * Constructs a {@code GuiSettings} with the default height, width and position.
     */
    public GuiSettings() {
        windowWidth = DEFAULT_WIDTH;
        windowHeight = DEFAULT_HEIGHT;
        windowCoordinates = null; // null represent no coordinates
    }

    /**
     * Constructs a {@code GuiSettings} with the specified height, width and position.
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof GuiSettings)) { // this handles null as well.
            return false;
        }
        GuiSettings otherSetting = (GuiSettings) other;
        return windowWidth == otherSetting.windowWidth
                && windowHeight == otherSetting.windowHeight
                && Objects.equals(windowCoordinates, otherSetting.windowCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(windowWidth, windowHeight, windowCoordinates);
    }

    @Override
    public String toString() {
        return "GuiSettings [windowWidth=" + windowWidth + ", windowHeight=" + windowHeight
                + ", windowCoordinates=" + windowCoordinates + "]";
    }
}
