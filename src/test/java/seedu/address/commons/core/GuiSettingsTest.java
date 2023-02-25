package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.awt.Point;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {
    private static final double WINDOW_WIDTH = 3.4;
    private static final double WINDOW_HEIGHT = 4.5;
    private static final int X_POSITION = 6;
    private static final int Y_POSITION = 8;
    private static final GuiSettings GUI_SETTINGS = new GuiSettings(WINDOW_WIDTH, WINDOW_HEIGHT,
            X_POSITION, Y_POSITION);

    @Test
    public void constructor_validInputs_success() {
        assertEquals(GUI_SETTINGS.getWindowWidth(), WINDOW_WIDTH);
        assertEquals(GUI_SETTINGS.getWindowHeight(), WINDOW_HEIGHT);
    }

    @Test
    public void equals_sameObject_true() {
        assertEquals(GUI_SETTINGS, GUI_SETTINGS);
    }

    @Test
    public void equals_notGuiSettings_false() {
        assertNotEquals(GUI_SETTINGS, null);
        assertNotEquals(GUI_SETTINGS, 6);
    }

    @Test
    public void equals_sameFields_true() {
        GuiSettings otherSettings = new GuiSettings(WINDOW_WIDTH,
                WINDOW_HEIGHT, X_POSITION, Y_POSITION);
        assertEquals(GUI_SETTINGS, otherSettings);
    }

    @Test
    public void equals_differentWindowWidth_false() {
        GuiSettings otherSettings = new GuiSettings(0,
                WINDOW_HEIGHT, X_POSITION, Y_POSITION);
        assertNotEquals(GUI_SETTINGS, otherSettings);
    }

    @Test
    public void equals_differentWindowHeight_false() {
        GuiSettings otherSettings = new GuiSettings(WINDOW_WIDTH,
                0, X_POSITION, Y_POSITION);
        assertNotEquals(GUI_SETTINGS, otherSettings);
    }

    @Test
    public void equals_differentXPosition_false() {
        GuiSettings otherSettings = new GuiSettings(WINDOW_WIDTH,
                WINDOW_HEIGHT, 0, Y_POSITION);
        assertNotEquals(GUI_SETTINGS, otherSettings);
    }

    @Test
    public void equals_differentYPosition_false() {
        GuiSettings otherSettings = new GuiSettings(WINDOW_WIDTH,
                WINDOW_HEIGHT, X_POSITION, 0);
        assertNotEquals(GUI_SETTINGS, otherSettings);
    }

    @Test
    public void getWindowCoordinates_samePoint_success() {
        Point windowCoordinates = new Point(X_POSITION, Y_POSITION);
        assertEquals(windowCoordinates,
                GUI_SETTINGS.getWindowCoordinates());
    }

    @Test
    public void hashCode_sameFields_true() {
        GuiSettings otherSettings = new GuiSettings(WINDOW_WIDTH,
                WINDOW_HEIGHT, X_POSITION, Y_POSITION);
        assertEquals(GUI_SETTINGS.hashCode(), otherSettings.hashCode());
    }
}
