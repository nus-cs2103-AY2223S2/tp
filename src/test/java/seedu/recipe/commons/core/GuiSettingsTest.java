package seedu.recipe.commons.core;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Contains tests that validate that the GuiSettings class performs as expected.
 * If the developer wishes to modify the class defaults or behaviors, they will
 * have to modify these test cases.
 */
public class GuiSettingsTest {
    private static final double DEFAULT_HEIGHT = 600;
    private static final double DEFAULT_WIDTH = 740;

    @Test
    public void testDefaultConstructor() {
        GuiSettings settings = new GuiSettings();
        assertEquals(settings.getWindowHeight(), DEFAULT_HEIGHT);
        assertEquals(settings.getWindowWidth(), DEFAULT_WIDTH);
        assertNull(settings.getWindowCoordinates());
    }

    @Test
    public void testOverloadedConstructor() {
        AtomicReference<GuiSettings> settings = new AtomicReference<>();
        //Validate valid values work
        assertDoesNotThrow(() -> {
            settings.set(new GuiSettings(1, 1, 0, 0));
        });

        //Validate invalid width throws error
        assertThrows(AssertionError.class, () -> settings.set(new GuiSettings(0, 1, 0, 0)));
        assertThrows(AssertionError.class, () -> settings.set(new GuiSettings(-1, 1, 0, 0)));
        //Validate invalid height throws error
        assertThrows(AssertionError.class, () -> settings.set(new GuiSettings(1, 0, 0, 0)));
        assertThrows(AssertionError.class, () -> settings.set(new GuiSettings(1, -1, 0, 0)));

        //Test with valid values, diff x, y coordinates
        assertDoesNotThrow(() -> settings.set(new GuiSettings(1, 1, 1, 0)));
        assertDoesNotThrow(() -> settings.set(new GuiSettings(1, 1, -1, 0)));
        assertDoesNotThrow(() -> settings.set(new GuiSettings(1, 1, 0, -1)));
        assertDoesNotThrow(() -> settings.set(new GuiSettings(1, 1, 0, 1)));

        //Test that the last Point was initialised properly
        assertEquals(new Point(0, 1), settings.get().getWindowCoordinates());
        assertEquals(1, settings.get().getWindowHeight());
        assertEquals(1, settings.get().getWindowWidth());
    }

    @Test
    public void testEquals() {
        GuiSettings settingAlpha = new GuiSettings(10, 5, 0, 5);
        GuiSettings settingAlphaCopy = new GuiSettings(10, 5, 0, 5);
        GuiSettings settingAlphaDiff = new GuiSettings(10, 5, 1, 5);

        assertEquals(settingAlphaCopy, settingAlpha);
        assertEquals(settingAlpha, settingAlpha);
        assertNotEquals(settingAlpha, settingAlphaDiff);
    }

    @Test
    public void test_hashCode() {
        double height = 100.0;
        double width = 50.0;
        int xCoord = 20;
        int yCoord = -15;
        assertEquals(Objects.hash(height, width, new Point(xCoord, yCoord)),
            new GuiSettings(height, width, xCoord, yCoord).hashCode());
    }

    @Test
    public void test_string() {
        double height = 100.0;
        double width = 50.0;
        int xCoord = 20;
        int yCoord = -15;
        String expectedFmt = String.format("Width : %s\nHeight : %s\nPosition : %s",
            width, height, new Point(xCoord, yCoord));
        assertEquals(expectedFmt, new GuiSettings(width, height, xCoord, yCoord).toString());
    }
}
