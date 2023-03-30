package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArchiveParser;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.tag.Tag;
import seedu.address.storage.JsonTrackerStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalTags;

public class ExportCommandTest {
    private static final String TEST_FILE = "test.json";

    Storage storage;

    @BeforeEach
    public void setUp() {
        JsonTrackerStorage archiveStorage = new JsonTrackerStorage(Paths.get("lt"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(Paths.get("prefs"));
        storage = new StorageManager(archiveStorage, userPrefsStorage);
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        ExportCommand command = new ExportCommand(TEST_FILE, storage, false);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void equals() {
        ExportCommand exportCommand = new ExportCommand(TEST_FILE, storage, false);

        ExportCommand exportCommandCopy = new ExportCommand(TEST_FILE, storage, false);
        ExportCommand exportCommandOverwrite = new ExportCommand(TEST_FILE, storage, true);

        assertTrue(exportCommand.equals(exportCommand));
        assertTrue(exportCommand.equals(exportCommandCopy));
        assertFalse(exportCommand.equals(1));
        assertFalse(exportCommand.equals(exportCommandOverwrite));
    }
}