package seedu.wife.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HelpMenuTest {
    @Test
    void getDescription_checkCorrectDescription_success() {
        String description = "Add food item - ";
        assertEquals(description, HelpMenu.ADD.getDescription());
    }

    @Test
    void getFormat_checkCorrectFormat_success() {
        String format = "add n/NAME u/UNIT q/QUANTITY e/EXPIRY DATE [t/TAG]";
        assertEquals(format, HelpMenu.ADD.getFormat());
    }

    @Test
    void displayHelpMenu_checkCurrentOutput_success() {
        StringBuilder sb = new StringBuilder();
        sb.append("Command Examples: " + System.lineSeparator());
        for (HelpMenu h : HelpMenu.values()) {
            sb.append(h.getDescription() + h.getFormat());
            sb.append(System.lineSeparator());
        }
        String result = sb.toString();
        assertEquals(result, HelpMenu.displayHelpMenu());
    }
}
