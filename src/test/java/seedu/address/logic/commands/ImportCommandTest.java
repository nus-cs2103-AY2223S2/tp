/*
package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTracker;
import seedu.address.model.Tracker;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.storage.Storage;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.StorageStub;
import seedu.address.testutil.TypicalModules;

public class ImportCommandTest {
    private static final String TEST_FILE = "test.json";
    @TempDir
    public Path testFolder;
    private Model model = new ModelStubWithFilledTracker();

    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new StorageStubForImport(testFolder.resolve(TEST_FILE));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        ImportCommand command = new ImportCommand(TEST_FILE, storage, new HashSet<>(), false,
                true);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void equals() {
        ImportCommand importCommand = new ImportCommand(TEST_FILE, new HashSet<>(), false,
                true);

        ImportCommand importCommandCopy = new ImportCommand(TEST_FILE, new HashSet<>(), false,
                true);

        ImportCommand exportCommandOverwrite = new ImportCommand(TEST_FILE, new HashSet<>(), true,
                true);

        assertTrue(importCommand.equals(importCommand));
        assertTrue(importCommand.equals(importCommandCopy));
        assertFalse(importCommand.equals(1));
        assertFalse(importCommand.equals(exportCommandOverwrite));
    }

    @Test
    public void execute_fileDoNotExist_throwCommandException() {
        ImportCommand importCommand = new ImportCommand("DoNotExist.json", storage, new HashSet<>(), false,
                true);
        assertThrows(CommandException.class, () -> importCommand.execute(model));
    }

    @Test
    public void execute_allModuleExistInTracker_throwCommandException() {
        ImportCommand importCommand = new ImportCommand("letracker.json", storage,
                new HashSet<>(List.of(TypicalModules.getCs2040s().getCode())),
                false,
                false);

        assertThrows(CommandException.class, () -> importCommand.execute(model));
    }

    @Test
    public void execute_someModuleExistInTracker_throwCommandException() {
        ImportCommand importCommand = new ImportCommand("letracker.json", storage,
                new HashSet<>(),
                false,
                true);

        assertThrows(CommandException.class, () -> importCommand.execute(model));
    }

    @Test
    public void execute_moduleDoesNotExistInArchive_throwCommandException() {
        ImportCommand importCommand = new ImportCommand("letracker.json", storage,
                new HashSet<>(List.of(TypicalModules.getCs2107().getCode())),
                false,
                false);

        assertThrows(CommandException.class, () -> importCommand.execute(model));
    }
    */
/*
    @Test
    public void execute_doNotOverwriteModule_successful() throws CommandException {
        ImportCommand importCommand = new ImportCommand("DummyFile.json", storage,
                new HashSet<>(List.of(TypicalModules.getCs2040s().getCode())),
                false,
                true);

        Model modelWithEmptyTracker = new ModelStubWithEmptyTracker();
        importCommand.execute(modelWithEmptyTracker);
        assertTrue(modelWithEmptyTracker.getTracker()
                .hasModule(TypicalModules.getCs2040s().getCode()));
    }

    @Test
    public void execute_overwriteModule_successful() throws CommandException {
        ImportCommand importCommand = new ImportCommand("DummyFile.json", storage,
                new HashSet<>(List.of(TypicalModules.getCs2040s().getCode())),
                true,
                true);

        Model model = new ModelStubWithFilledTracker();
        importCommand.execute(model);
        assertTrue(model.getTracker()
                .hasModule(TypicalModules.getCs2040s().getCode()));
    }
    *//*






    */
/**
     * A {@code Model} stub that contains a tracker.
     *//*

    private class ModelStubWithFilledTracker extends ModelStub {
        private Tracker tracker;

        public ModelStubWithFilledTracker() {
            this.tracker = TypicalModules.getTypicalTracker();
        }

        @Override
        public Tracker getTracker() {
            return tracker;
        }

        @Override
        public boolean hasModule(ModuleCode moduleCode) {
            return true;
        }

        @Override
        public void setModule(ReadOnlyModule target, Module editedModule) {
            tracker.setModule(target, editedModule);
        }

        @Override
        public ReadOnlyModule getModule(ModuleCode moduleCode) {
            return tracker.getModule(moduleCode);
        }
    }

    private class ModelStubWithEmptyTracker extends ModelStub {
        private Tracker tracker;

        public ModelStubWithEmptyTracker() {
            this.tracker = new Tracker();
        }

        @Override
        public Tracker getTracker() {
            return tracker;
        }

        @Override
        public boolean hasModule(ModuleCode moduleCode) {
            return false;
        }

        @Override
        public void addModule(Module module) {
            tracker.addModule(module);
        }
    }



    */
/**
     * A {@code Storage} stub that is used to test import command.
     *//*


    private class StorageStubForImport extends StorageStub {
        private final Tracker tracker = TypicalModules.getTypicalTracker();
        private final Path archivePath;
        public StorageStubForImport(Path archivePath) {
            this.archivePath = archivePath;
        }

        @Override
        public Optional<ReadOnlyTracker> readTracker(Path archivePath) {
            return Optional.of(this.tracker);
        }
    }
}
*/
