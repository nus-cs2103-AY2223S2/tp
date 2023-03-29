package seedu.address.logic.commands;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ImportDataCommandTest {

    private static final Path INVALID_FILE_FORMAT_DATA = Paths.get("src", "test", "data",
        "JsonAddressBookStorageTest", "notJsonFormatAddressBook.json");

    private static final Path INVALID_DATA_FORMAT_DATA = Paths.get("src", "test", "data",
        "JsonAddressBookStorageTest", "notJsonFormatAddressBook.json");

    private static final Path NON_EXIST_DATA = Paths.get("src", "test", "data",
        "JsonAddressBookStorageTest", "abcdefg.json");

    private Model model = new ModelManager();


    @Test
    public void execute_invalidPathImport_throwsCommandException() {
        assertThrows(CommandException.class, () ->
            new ImportDataCommand("invalid path").execute(model));
    }

    @Test
    public void execute_invalidFileFormatImport_throwsCommandException() {
        assertThrows(CommandException.class, () ->
            new ImportDataCommand(INVALID_FILE_FORMAT_DATA.toString()).execute(model));
    }

    @Test
    public void execute_invalidDataFormatImport_throwsCommandException() {
        assertThrows(CommandException.class, () ->
            new ImportDataCommand(INVALID_DATA_FORMAT_DATA.toString()).execute(model));
    }

    @Test
    public void execute_missingFileImport_throwsCommandException() {
        assertThrows(CommandException.class, () ->
            new ImportDataCommand(NON_EXIST_DATA.toString()).execute(model));
    }
}
