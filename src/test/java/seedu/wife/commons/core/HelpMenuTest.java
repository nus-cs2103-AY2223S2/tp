package seedu.wife.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HelpMenuTest {
    @Test
    void parseCommand_invalidArg_returnsInvalidSuccess() {
        //non-existent command
        String invalidArg = "8792jhw";
        assertEquals(HelpMenu.INVALID, HelpMenu.parseCommand(invalidArg));

        //empty string
        String emptyArg = "";
        assertEquals(HelpMenu.INVALID, HelpMenu.parseCommand(emptyArg));

        //null input
        assertEquals(HelpMenu.INVALID, HelpMenu.parseCommand(null));
    }

    @Test
    void getCommandUsage_checkCorrectResult_success() {
        String format = "add n/NAME u/UNIT q/QUANTITY e/EXPIRY DATE [t/TAG]";
        assertEquals(format, HelpMenu.ADD.getFormat());
    }
//
//    @Test
//    void displayHelpMenu_checkCorrectOutput_success() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Command Examples: " + System.lineSeparator());
//        for (HelpMenu h : HelpMenu.values()) {
//            sb.append(h.getDescription() + h.getFormat());
//            sb.append(System.lineSeparator());
//        }
//        String result = sb.toString();
//        assertEquals(result, HelpMenu.getGeneralHelp());
//    }
}
