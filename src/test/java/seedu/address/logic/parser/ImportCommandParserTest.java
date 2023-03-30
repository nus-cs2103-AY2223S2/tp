package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.storage.JsonTrackerStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;

public class ImportCommandParserTest {
    private static final String TEST_FILE = "test.json";
    private static final String MODULE_1 = "CS2040S";
    private static final String MODULE_2 = "EG2310";

    private ImportCommandParser parser = new ImportCommandParser();

    private Storage storage;

    @BeforeEach
    public void setUp() {
        JsonTrackerStorage archiveStorage = new JsonTrackerStorage(Paths.get("lt"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(Paths.get("prefs"));
        storage = new StorageManager(archiveStorage, userPrefsStorage);
    }

    @Test
    public void parseCommand_doNotOverwriteModule_successful() throws ParseException {
        ImportCommand expectedCommand = new ImportCommand(TEST_FILE, new HashSet<>(),
                false, true);
        assertEquals(expectedCommand, parser.parse(TEST_FILE));
    }

    @Test
    public void parseCommand_overwriteModule_successful() throws ParseException {
        String argument = TEST_FILE + " /overwrite true";
        ImportCommand expectedCommand = new ImportCommand(TEST_FILE, new HashSet<>(),
                true, true);
        assertEquals(expectedCommand, parser.parse(argument));
    }

    @Test
    public void parseCommand_importSomeModule_successful() throws ParseException {
        String argument = TEST_FILE + " " + PREFIX_MODULE + " " + MODULE_1 + ", " + MODULE_2 + " /overwrite true";
        ImportCommand expectedCommand = new ImportCommand(TEST_FILE,
                new HashSet<>(List.of(new ModuleCode(MODULE_1), new ModuleCode(MODULE_2))),
                true, false);
        assertEquals(expectedCommand, parser.parse(argument));
    }

    @Test
    public void parseCommand_emptyFileName_throwParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parseCommand_emptyModule_throwParseException() {
        String argument = TEST_FILE + " " + PREFIX_MODULE;
        assertThrows(ParseException.class, () -> parser.parse(argument));
    }
}
