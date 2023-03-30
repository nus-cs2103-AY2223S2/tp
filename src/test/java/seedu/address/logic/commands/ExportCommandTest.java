package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTracker;
import seedu.address.model.Tracker;
import seedu.address.storage.JsonTrackerStorage;
import seedu.address.storage.Storage;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.StorageStub;
import seedu.address.testutil.TypicalModules;


public class ExportCommandTest {
    private static final String TEST_FILE = "test.json";
    @TempDir
    public Path testFolder;
    private Model model = new ModelStubWithTracker();

    private Storage storage = new StorageStubForExport(Paths.get(TEST_FILE));

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

    @Test
    public void execute_fileAlreadyExist_throwCommandException() {
        ExportCommand exportCommand = new ExportCommand("letracker.json", storage, false);
        assertThrows(CommandException.class, () -> exportCommand.execute(model));
    }

    @Test
    public void execute_correctCommand_successful() throws CommandException, DataConversionException, IOException {
        Path savePath = testFolder.resolve(TEST_FILE);
        Storage storage = new StorageStubForExport(savePath);
        ExportCommand exportCommand = new ExportCommand("Random.json", storage, false);
        exportCommand.execute(model);
        assertEquals(storage.readTracker().get(), model.getTracker());
    }


    /**
     * A {@code Model} stub that contains a tracker.
     */
    private class ModelStubWithTracker extends ModelStub {
        private Tracker tracker;

        public ModelStubWithTracker() {
            this.tracker = TypicalModules.getTypicalTracker();
        }

        @Override
        public Tracker getTracker() {
            return tracker;
        }
    }


    /**
     * A {@code Storage} stub that is used to test export command.
     */

    private class StorageStubForExport extends StorageStub {
        private final Path archivePath;
        public StorageStubForExport(Path archivePath) {
            this.archivePath = archivePath;
        }

        public void saveTracker(ReadOnlyTracker tracker, Path filePath) {

            try {
                new JsonTrackerStorage(archivePath)
                        .saveTracker(tracker, archivePath);
            } catch (IOException ioe) {
                throw new AssertionError("There should not be an error writing to the file.", ioe);
            }
        }

        public Optional<ReadOnlyTracker> readTracker() {
            return Optional.of(TypicalModules.getTypicalTracker());
        }
    }
}
