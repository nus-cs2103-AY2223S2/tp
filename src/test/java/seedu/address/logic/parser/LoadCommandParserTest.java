package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LoadCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.exceptions.UiInputRequiredException;

class LoadCommandParserTest {
    private static final String TEST_PATH = "C:\\Users\\joellow88\\Desktop\\Modules\\CS2103\\tp\\src\\test\\data"
            + "\\JsonAddressBookStorageTest\\ValidAddressBook1.json";

    @Test
    void parse_noInput() {
        assertThrows(UiInputRequiredException.class, () -> new LoadCommandParser().parse(""));
    }

    @Test
    void parse_noPath() {
        try {
            assertEquals(new LoadCommand(null), new LoadCommandParser().parse("!"));
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    void parse_invalidPath() {
        try {
            assertEquals(new LoadCommand("INVALID PATH"), new LoadCommandParser().parse("INVALID PATH"));
        } catch (ParseException e) {
            fail();
        }
    }
    @Test
    void parse_validPath() {
        try {
            assertEquals(
                    new LoadCommand(TEST_PATH),
                    new LoadCommandParser().parse(TEST_PATH));
        } catch (ParseException e) {
            fail();
        }
    }
}
