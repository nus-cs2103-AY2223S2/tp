package seedu.wife.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HelpMenuTest {
    @Test
    void parseCommand_invalidArg_returnsInvalidSuccess() {
        //non-existent command
        String invalidArg = "abcdef";
        assertEquals(HelpMenu.INVALID, HelpMenu.parseCommand(invalidArg));

        //empty string
        String emptyArg = "";
        assertEquals(HelpMenu.INVALID, HelpMenu.parseCommand(emptyArg));

        //null input
        assertEquals(HelpMenu.INVALID, HelpMenu.parseCommand(null));
    }

    @Test
    void parseCommand_validArg_returnsCommandSuccess() {
        //valid command
        String validArgAdd = "add";
        assertEquals(HelpMenu.ADD, HelpMenu.parseCommand(validArgAdd));
    }
    @Test
    void getCommandUsage_checkCorrectResult_success() {
        String expected = "Add food item - add n/NAME u/UNIT q/QUANTITY e/EXPIRY DATE";
        assertEquals(expected, HelpMenu.ADD.getCommandUsage());
    }

    @Test
    void displayHelpMenu_checkCorrectOutput_success() {
        String expected = "Type 'help COMMAND' to see specific help for a command."
                + System.lineSeparator()
                + "Commands Available: add, edit, dec, inc, delete, find, list, expiry, view, "
                + "tag, untag, delbytag, listbytag, createtag, deltag, listtag, clear, help, exit";
        String actual = HelpMenu.displayHelpMenu();
        assertEquals(expected, actual);
    }
}
