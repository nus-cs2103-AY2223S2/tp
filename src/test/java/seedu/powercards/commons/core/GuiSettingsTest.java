package seedu.powercards.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class GuiSettingsTest {

    @Test
    public void equals() {
        GuiSettings guiSettings1 = new GuiSettings(500, 600, 100, 200);
        GuiSettings guiSettings2 = new GuiSettings(500, 600, 100, 200);
        GuiSettings guiSettings3 = new GuiSettings(400, 500, 50, 100);

        // equal to itself
        assertEquals(guiSettings1, guiSettings1);

        // equal to other GuiSettings with the same values
        assertEquals(guiSettings1, guiSettings2);

        // not equal to other GuiSettings with different values
        assertNotEquals(guiSettings1, guiSettings3);

        // not equal to a different object type
        assertNotEquals(guiSettings1, new Object());

        // not equal to null
        assertNotEquals(guiSettings1, null);
    }

    @Test
    public void hashCode_sameObject_equal() {
        GuiSettings guiSettings1 = new GuiSettings(500, 600, 100, 200);
        assertEquals(guiSettings1.hashCode(), guiSettings1.hashCode());
    }

    @Test
    public void hashCode_equalObject_equal() {
        GuiSettings guiSettings1 = new GuiSettings(500, 600, 100, 200);
        GuiSettings guiSettings2 = new GuiSettings(500, 600, 100, 200);
        assertEquals(guiSettings1.hashCode(), guiSettings2.hashCode());
    }

    @Test
    public void hashCode_differentObject_notEqual() {
        GuiSettings guiSettings1 = new GuiSettings(500, 600, 100, 200);
        GuiSettings guiSettings2 = new GuiSettings(400, 500, 50, 100);
        assertNotEquals(guiSettings1.hashCode(), guiSettings2.hashCode());
    }

}
