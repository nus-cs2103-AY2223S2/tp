package seedu.loyaltylift.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;

public class BadgeTest {

    @Test
    public void createCustomerTypeBadge_nullCustomerType_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Badge.createCustomerTypeBadge(null));
    }

    @Test
    public void colorToHex() {
        assertEquals(Badge.colorToHex(Color.WHITE), "#ffffff");
        assertEquals(Badge.colorToHex(Color.valueOf("#1a1a1a")), "#1a1a1a");
    }
}
