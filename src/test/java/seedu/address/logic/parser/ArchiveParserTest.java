package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_KNOWN_COMMANDS;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Paths;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.storage.JsonTrackerStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;

public class ArchiveParserTest {
    private Storage storage;

    private ArchiveParser parser;

    @BeforeEach
    public void setUp() {
        JsonTrackerStorage archiveStorage = new JsonTrackerStorage(Paths.get("lt"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(Paths.get("prefs"));
        storage = new StorageManager(archiveStorage, userPrefsStorage);
        parser = new ArchiveParser(storage);
    }

    @Test
    public void isArchiveCommandTest_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.isArchiveCommand(""));
    }

    @Test
    public void isArchiveCommandTest_exportCommandInput_returnTrue() throws ParseException {
        assertTrue(parser.isArchiveCommand(ExportCommand.COMMAND_WORD));
    }

    @Test
    public void isArchiveCommandTest_importCommandInput_returnTrue() throws ParseException {
        assertTrue(parser.isArchiveCommand(ImportCommand.COMMAND_WORD));
    }

    @Test
    public void isArchiveCommandTest_otherCommandInput_returnFalse() throws ParseException {
        assertFalse(parser.isArchiveCommand(TagCommand.COMMAND_WORD));
    }


    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_exportInput_successful() throws ParseException {
        String userInput = ExportCommand.COMMAND_WORD + " test.json";
        ExportCommand expectedCommand = new ExportCommand("test.json", storage, false);
        assertEquals(expectedCommand, parser.parseCommand(userInput));
    }

    @Test
    public void parseCommand_importInput_successful() throws ParseException {
        String userInput = ImportCommand.COMMAND_WORD + " test.json";
        ImportCommand expectedCommand = new ImportCommand("test.json", storage, new HashSet<>(),
                false, true);
        assertEquals(expectedCommand, parser.parseCommand(userInput));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class,
                MESSAGE_UNKNOWN_COMMAND + MESSAGE_KNOWN_COMMANDS, () -> parser.parseCommand("unknownCommand"));
    }
}
