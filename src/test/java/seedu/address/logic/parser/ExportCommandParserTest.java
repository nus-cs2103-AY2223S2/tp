package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ARCHIVE_FILE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OVERWRITE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ExportCommandParserTest {
    private final ExportCommandParser parser = new ExportCommandParser();

    @Test
    public void parseCommand_doNotOverwriteFile_successful() throws ParseException {
        ExportCommand expectedCommand = new ExportCommand(VALID_ARCHIVE_FILE_NAME, false);
        assertEquals(expectedCommand, parser.parse(VALID_ARCHIVE_FILE_NAME));
    }

    @Test
    public void parseCommand_overwriteFileCommand_successful() throws ParseException {
        String argument = VALID_ARCHIVE_FILE_NAME + " " + PREFIX_OVERWRITE;
        ExportCommand expectedCommand = new ExportCommand(VALID_ARCHIVE_FILE_NAME, true);
        assertEquals(expectedCommand, parser.parse(argument));
    }

    @Test
    public void parseCommand_overwriteFileCommandWithExtraSpace_successful() throws ParseException {
        String argument = VALID_ARCHIVE_FILE_NAME + " " + PREFIX_OVERWRITE + "   ";
        ExportCommand expectedCommand = new ExportCommand(VALID_ARCHIVE_FILE_NAME, true);
        assertEquals(expectedCommand, parser.parse(argument));
    }

    @Test
    public void parseCommand_emptyFileName_throwParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" "));
    }
}
