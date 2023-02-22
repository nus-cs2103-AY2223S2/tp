package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {
    @Test
    public void constructor_validInputs_success() {
        double windowWidth = 3.4;
        double windowHeight = 4.5;
        int xPosition = 6;
        int yPosition = 8;
        GuiSettings guiSettings = new GuiSettings(windowWidth,
                windowHeight, xPosition, yPosition);
        assertEquals(guiSettings.getWindowWidth(), windowWidth);
        assertEquals(guiSettings.getWindowHeight(), windowHeight);
    }

    @Test
    public void equals_sameObject_true() {
        double windowWidth = 3.4;
        double windowHeight = 4.5;
        int xPosition = 6;
        int yPosition = 8;
        GuiSettings guiSettings = new GuiSettings(windowWidth,
                windowHeight, xPosition, yPosition);
        assertEquals(guiSettings, guiSettings);
    }

    @Test
    public void equals_notGuiSettings_false() {
        double windowWidth = 3.4;
        double windowHeight = 4.5;
        int xPosition = 6;
        int yPosition = 8;
        GuiSettings guiSettings = new GuiSettings(windowWidth,
                windowHeight, xPosition, yPosition);
        assertNotEquals(guiSettings, null);
    }

    @Test
    public void equals_sameFields_true() {
        double windowWidth = 3.4;
        double windowHeight = 4.5;
        int xPosition = 6;
        int yPosition = 8;
        GuiSettings guiSettings = new GuiSettings(windowWidth,
                windowHeight, xPosition, yPosition);
        GuiSettings otherSettings = new GuiSettings(windowWidth,
                windowHeight, xPosition, yPosition);
        assertEquals(guiSettings, otherSettings);
    }

    @Test
    public void hashCode_sameFields_true() {
        double windowWidth = 3.4;
        double windowHeight = 4.5;
        int xPosition = 6;
        int yPosition = 8;
        GuiSettings guiSettings = new GuiSettings(windowWidth,
                windowHeight, xPosition, yPosition);
        GuiSettings otherSettings = new GuiSettings(windowWidth,
                windowHeight, xPosition, yPosition);
        assertEquals(guiSettings.hashCode(), otherSettings.hashCode());
    }
}
