package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2103;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ARCHIVE_FILE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OVERWRITE;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;

public class ImportCommandParserTest {
    private ImportCommandParser parser = new ImportCommandParser();

    @Test
    public void parseCommand_doNotOverwriteModule_successful() throws ParseException {
        ImportCommand expectedCommand = new ImportCommand(VALID_ARCHIVE_FILE_NAME, new HashSet<>(),
                false, true);
        assertEquals(expectedCommand, parser.parse(VALID_ARCHIVE_FILE_NAME));
    }

    @Test
    public void parseCommand_overwriteModule_successful() throws ParseException {
        String argument = VALID_ARCHIVE_FILE_NAME + " " + PREFIX_OVERWRITE;
        ImportCommand expectedCommand = new ImportCommand(VALID_ARCHIVE_FILE_NAME, new HashSet<>(),
                true, true);
        assertEquals(expectedCommand, parser.parse(argument));
    }

    @Test
    public void parseCommand_importSomeModuleNoOverwrite_successful() throws ParseException {
        String argument = VALID_ARCHIVE_FILE_NAME + " " + PREFIX_MODULE + " "
                + VALID_MODULE_CODE_2040 + ",    " + VALID_MODULE_CODE_2103;
        ImportCommand expectedCommand = new ImportCommand(VALID_ARCHIVE_FILE_NAME,
                new HashSet<>(List.of(new ModuleCode(VALID_MODULE_CODE_2040), new ModuleCode(VALID_MODULE_CODE_2103))),
                false, false);
        assertEquals(expectedCommand, parser.parse(argument));
    }

    @Test
    public void parseCommand_importSomeModule_successful() throws ParseException {
        String argument = VALID_ARCHIVE_FILE_NAME + " " + PREFIX_MODULE + " "
                + VALID_MODULE_CODE_2040 + ", " + VALID_MODULE_CODE_2103 + " " + PREFIX_OVERWRITE;
        ImportCommand expectedCommand = new ImportCommand(VALID_ARCHIVE_FILE_NAME,
                new HashSet<>(List.of(new ModuleCode(VALID_MODULE_CODE_2040), new ModuleCode(VALID_MODULE_CODE_2103))),
                true, false);
        assertEquals(expectedCommand, parser.parse(argument));
    }

    @Test
    public void parseCommand_emptyFileName_throwParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parseCommand_emptyModule_throwParseException() {
        String firstArgument = VALID_ARCHIVE_FILE_NAME + " " + PREFIX_MODULE;
        String secondArgument = firstArgument + "    ";
        assertThrows(ParseException.class, () -> parser.parse(firstArgument));
        assertThrows(ParseException.class, () -> parser.parse(secondArgument));
    }

    @Test
    public void parseCommand_emptyModuleAndOverwrite_throwParseException() {
        String argument = VALID_ARCHIVE_FILE_NAME + " " + PREFIX_MODULE + "    " + PREFIX_OVERWRITE;
        assertThrows(ParseException.class, () -> parser.parse(argument));
    }

    @Test
    public void parseCommand_emptyModuleAndOverwriteDifferentPrefixOrder_throwParseException() {
        String argument = VALID_ARCHIVE_FILE_NAME + " " + PREFIX_OVERWRITE + "    " + PREFIX_MODULE + "  ";
        assertThrows(ParseException.class, () -> parser.parse(argument));
    }
}
