package seedu.address.logic.archive;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.trackereventsystem.TrackerEventSystem;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTracker;
import seedu.address.model.Tracker;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.storage.JsonTrackerStorage;
import seedu.address.storage.Storage;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.StorageStub;
import seedu.address.testutil.TypicalModules;

public class ArchiveTest {
    private static final String EXISTING_FILE_IN_ARCHIVE = "EmptyTracker.json";
    private static final String EXISTING_FILE_IN_ARCHIVE_FOR_IMPORT = "FilledTracker.json";
    private static final String NON_EXISTING_FILE_IN_ARCHIVE = "NewFile.json";
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");
    @TempDir
    public Path testFolder;
    private Storage storage;
    private Archive archive;

    @BeforeEach
    public void setUp() throws IOException {
        storage = new StorageStubForArchiveTest(testFolder.resolve(EXISTING_FILE_IN_ARCHIVE));
        storage.saveTracker(new Tracker(), testFolder.resolve(EXISTING_FILE_IN_ARCHIVE));
        archive = new Archive(storage);
    }

    @Test
    public void exportToArchive_saveToExistingFileNoOverwrite_throwCommandException() throws IOException,
            DataConversionException {
        Tracker tracker = TypicalModules.getTypicalTracker();
        assertThrows(CommandException.class, () -> archive.exportToArchive(testFolder.resolve(EXISTING_FILE_IN_ARCHIVE),
                tracker, false, testFolder.resolve(EXISTING_FILE_IN_ARCHIVE)));
        assertFalse(storage.readTracker().get().hasModule(TypicalModules.getCs2040s().getCode()));
    }

    @Test
    public void exportToArchive_saveToNewFile_success() throws CommandException, DataConversionException, IOException {
        Tracker tracker = TypicalModules.getTypicalTracker();
        archive.exportToArchive(testFolder.resolve(NON_EXISTING_FILE_IN_ARCHIVE), tracker, false,
                testFolder.resolve(EXISTING_FILE_IN_ARCHIVE));
        assertTrue(storage.readTracker().get().hasModule(TypicalModules.getCs2040s().getCode()));
    }

    @Test
    public void exportToArchive_saveToCurrentDirectoryWithOverwrite_throwsCommandException() {
        Tracker tracker = TypicalModules.getTypicalTracker();
        assertThrows(CommandException.class, () -> archive.exportToArchive(testFolder.resolve(EXISTING_FILE_IN_ARCHIVE),
                tracker, true,
                testFolder.resolve(EXISTING_FILE_IN_ARCHIVE)));
        //assertTrue(storage.readTracker().get().hasModule(TypicalModules.getCs2040s().getCode()));
    }

    @Test
    public void exportToArchive_saveToExistingFileWithOverwrite_success() throws CommandException,
            DataConversionException, IOException {
        archive.exportToArchive(testFolder.resolve(NON_EXISTING_FILE_IN_ARCHIVE),
                new Tracker(), false,
                testFolder.resolve(EXISTING_FILE_IN_ARCHIVE));
        archive.exportToArchive(testFolder.resolve(NON_EXISTING_FILE_IN_ARCHIVE),
                TypicalModules.getTypicalTracker(), true,
                testFolder.resolve(EXISTING_FILE_IN_ARCHIVE));
        assertTrue(storage.readTracker().get().hasModule(TypicalModules.getCs2040s().getCode()));
    }

    @Test
    public void exportToArchive_storageThrowsIoException_throwsCommandException() {
        Tracker tracker = TypicalModules.getTypicalTracker();
        Storage storageThrowIoe = new StorageStubThrowIoe(testFolder.resolve(EXISTING_FILE_IN_ARCHIVE));
        Archive archiveThrowIoe = new Archive(storageThrowIoe);
        assertThrows(CommandException.class, () -> archiveThrowIoe.exportToArchive(
                testFolder.resolve(NON_EXISTING_FILE_IN_ARCHIVE),
                        tracker, false, testFolder.resolve(EXISTING_FILE_IN_ARCHIVE)));
    }


    @Test
    public void importFromArchive_fileNotFound_throwCommandException() {
        assertThrows(CommandException.class, () -> archive.importFromArchive(
                testFolder.resolve(NON_EXISTING_FILE_IN_ARCHIVE),
                        new ModelStubWithFilledTracker(), true, false, new HashSet<>(), new TrackerEventSystem()));
    }

    @Test
    public void importFromArchive_storageThrowIoe_throwCommandException() {
        Storage storageThrowIoe = new StorageStubThrowIoe(testFolder.resolve(EXISTING_FILE_IN_ARCHIVE));
        Archive archiveThrowIoe = new Archive(storageThrowIoe);
        assertThrows(CommandException.class, () -> archiveThrowIoe.importFromArchive(
                testFolder.resolve(EXISTING_FILE_IN_ARCHIVE),
                        new ModelStubWithFilledTracker(), true, false, new HashSet<>(), new TrackerEventSystem()));
    }

    @Test
    public void importFromArchive_storageThrowDataConversionError_throwCommandException() {
        Storage storageThrowDataConversionError = new StorageStubThrowDataConversionError();
        Archive archiveThrowDataConversionError = new Archive(storageThrowDataConversionError);
        assertThrows(CommandException.class, () -> archiveThrowDataConversionError.importFromArchive(
                testFolder.resolve(EXISTING_FILE_IN_ARCHIVE),
                        new ModelStubWithFilledTracker(), true, false, new HashSet<>(), new TrackerEventSystem()));
    }

    @Test
    public void importFromArchive_importWholeArchive_successful() throws IOException, CommandException {
        Storage importStorage = new StorageStubForArchiveTest(testFolder.resolve(EXISTING_FILE_IN_ARCHIVE_FOR_IMPORT));
        importStorage.saveTracker(TypicalModules.getTypicalTracker(),
                testFolder.resolve(EXISTING_FILE_IN_ARCHIVE_FOR_IMPORT));
        Archive importArchive = new Archive(importStorage);
        Model model = new ModelStubWithEmptyTracker();
        importArchive.importFromArchive(testFolder.resolve(EXISTING_FILE_IN_ARCHIVE_FOR_IMPORT),
                model, true, false, new HashSet<>(), new TrackerEventSystem());
        assertTrue(model.hasModule(TypicalModules.getCs2040s().getCode()));
    }

    @Test
    public void importFromArchive_importOneModule_successful() throws IOException, CommandException {
        Storage importStorage = new StorageStubForArchiveTest(testFolder.resolve(EXISTING_FILE_IN_ARCHIVE_FOR_IMPORT));
        importStorage.saveTracker(TypicalModules.getTypicalTracker(),
                testFolder.resolve(EXISTING_FILE_IN_ARCHIVE_FOR_IMPORT));
        Archive importArchive = new Archive(importStorage);
        Model model = new ModelStubWithEmptyTracker();
        Set<ModuleCode> moduleCodesToAdd = new HashSet<>(List.of(TypicalModules.getSt2334().getCode()));
        importArchive.importFromArchive(testFolder.resolve(EXISTING_FILE_IN_ARCHIVE_FOR_IMPORT),
                model, false, false, moduleCodesToAdd, new TrackerEventSystem());
        assertTrue(model.hasModule(TypicalModules.getSt2334().getCode()));
    }


    @Test
    public void importFromArchive_modulesExistInTracker_throwsCommandException() {
        storage = new StorageStubWithTrackerWithCS2040S(testFolder.resolve(EXISTING_FILE_IN_ARCHIVE));
        archive = new Archive(storage);
        Model model = new ModelStubWithFilledTracker();
        Set<ModuleCode> moduleCodesToAdd = new HashSet<>(List.of(TypicalModules.getCs2040s().getCode()));
        assertThrows(CommandException.class, () -> archive.importFromArchive(
                testFolder.resolve(EXISTING_FILE_IN_ARCHIVE),
                        model, false, false, moduleCodesToAdd, new TrackerEventSystem()));
    }

    @Test
    public void importFromArchive_modulesDoesNotExistInArchive_throwsCommandException() {
        storage = new StorageStubWithTrackerWithCS2040S(testFolder.resolve(EXISTING_FILE_IN_ARCHIVE));
        archive = new Archive(storage);
        Model model = new ModelStubWithFilledTracker();
        Set<ModuleCode> moduleCodesToAdd = new HashSet<>(List.of(TypicalModules.getCs2107().getCode()));
        assertThrows(CommandException.class, () -> archive.importFromArchive(
                testFolder.resolve(EXISTING_FILE_IN_ARCHIVE),
                        model, false, false, moduleCodesToAdd, new TrackerEventSystem()));
    }

    @Test
    public void importFromArchive_overwriteOneModuleInTracker_throwsCommandException() throws CommandException {
        storage = new StorageStubWithTrackerWithCS2040S(testFolder.resolve(EXISTING_FILE_IN_ARCHIVE));
        archive = new Archive(storage);
        Model model = new ModelStubWithFilledTracker();
        Set<ModuleCode> moduleCodesToAdd = new HashSet<>(List.of(TypicalModules.getCs2040s().getCode()));
        archive.importFromArchive(testFolder.resolve(EXISTING_FILE_IN_ARCHIVE),
                model, false, true, moduleCodesToAdd, new TrackerEventSystem());
        assertTrue(model.hasModule(TypicalModules.getCs2040s().getCode()));
        assertTrue(model.hasModule(TypicalModules.getCs2107().getCode()));
    }

    @Test
    public void importFromArchive_overwriteAllModulesInTracker_throwsCommandException()
            throws CommandException, IOException {
        storage = new StorageStubForArchiveTest(testFolder.resolve(EXISTING_FILE_IN_ARCHIVE));
        storage.saveTracker(TypicalModules.getTypicalTracker(), testFolder.resolve(EXISTING_FILE_IN_ARCHIVE));
        archive = new Archive(storage);
        Model model = new ModelStubWithFilledTracker();
        Set<ModuleCode> moduleCodesToAdd = new HashSet<>();
        archive.importFromArchive(testFolder.resolve(EXISTING_FILE_IN_ARCHIVE),
                model, true, true, moduleCodesToAdd, new TrackerEventSystem());
        assertTrue(model.hasModule(TypicalModules.getCs2040s().getCode()));
        assertTrue(model.hasModule(TypicalModules.getSt2334().getCode()));
    }

    /**
     * A storage stub that facilitates the saving and reading tracker of export and import test.
     */

    private class StorageStubForArchiveTest extends StorageStub {
        private final Path archivePath;
        private ReadOnlyTracker tracker;
        public StorageStubForArchiveTest(Path archivePath) {
            this.archivePath = archivePath;
            this.tracker = new Tracker();
        }

        public void saveTracker(ReadOnlyTracker newTracker, Path filePath) {
            try {
                new JsonTrackerStorage(archivePath)
                        .saveTracker(newTracker, archivePath);
                tracker = newTracker;
            } catch (IOException ioe) {
                throw new AssertionError("There should not be an error writing to the file.", ioe);
            }
        }

        public Optional<ReadOnlyTracker> readTracker() {
            return Optional.of(this.tracker);
        }

        public Optional<ReadOnlyTracker> readTracker(Path archivePath) {
            return Optional.of(this.tracker);
        }
    }

    /**
     * A storage stub that contains a tracker with the module CS2040S.
     */

    private class StorageStubWithTrackerWithCS2040S extends StorageStub {
        private final Path archivePath;
        private Tracker tracker;
        public StorageStubWithTrackerWithCS2040S(Path archivePath) {
            this.archivePath = archivePath;
            this.tracker = new Tracker();
            this.tracker.addModule(TypicalModules.getCs2040s());

        }

        public void saveTracker(ReadOnlyTracker newTracker, Path filePath) {
            try {
                new JsonTrackerStorage(archivePath)
                        .saveTracker(newTracker, archivePath);
                tracker = (Tracker) newTracker;
            } catch (IOException ioe) {
                throw new AssertionError("There should not be an error writing to the file.", ioe);
            }
        }

        public Optional<ReadOnlyTracker> readTracker() {
            return Optional.of(this.tracker);
        }

        public Optional<ReadOnlyTracker> readTracker(Path archivePath) {
            return Optional.of(this.tracker);
        }
    }


    /**
     * A stub class to throw an {@code IOException} when the save and read method is called.
     */
    private static class StorageStubThrowIoe extends StorageStub {
        private final Path archivePath;
        private ReadOnlyTracker tracker;
        public StorageStubThrowIoe(Path archivePath) {
            this.archivePath = archivePath;
            this.tracker = new Tracker();
        }

        @Override
        public void saveTracker(ReadOnlyTracker tracker, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }

        @Override
        public Optional<ReadOnlyTracker> readTracker(Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code DataConversionError} when the read method is called.
     */
    private static class StorageStubThrowDataConversionError extends StorageStub {
        private static final Path TEST_DATA_FOLDER = Paths.get("src",
                "test", "data", "JsonTrackerStorageTest");
        private ReadOnlyTracker tracker;
        public StorageStubThrowDataConversionError() {
            this.tracker = new Tracker();
        }

        @Override
        public Optional<ReadOnlyTracker> readTracker(Path path) throws DataConversionException, IOException {
            try {
                readTracker("invalidModuleTracker.json");
                return Optional.of(TypicalModules.getTypicalTracker());
            } catch (DataConversionException dce) {
                throw dce;
            }
        }

        private java.util.Optional<ReadOnlyTracker> readTracker(String filePath)
                throws DataConversionException, IOException {
            return new JsonTrackerStorage(Paths.get(filePath)).readTracker(addToTestDataPathIfNotNull(filePath));
        }

        private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
            return prefsFileInTestDataFolder != null
                    ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                    : null;
        }
    }

    /**
     * A {@code Model} stub that contains a tracker filled with CS2040S and ST2334.
     */

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

    /**
     * A {@code Model} stub that contains a tracker.
     */

    private class ModelStubWithEmptyTracker extends ModelStub {
        private Tracker tracker;
        private List<Module> moduleList = new ArrayList<>();
        private List<ModuleCode> moduleCodeList = new ArrayList<>();

        public ModelStubWithEmptyTracker() {
            this.tracker = new Tracker();
        }

        @Override
        public Tracker getTracker() {
            return tracker;
        }

        @Override
        public boolean hasModule(ModuleCode moduleCode) {
            return moduleCodeList.contains(moduleCode);
        }

        @Override
        public void setModule(ReadOnlyModule target, Module editedModule) {
            tracker.setModule(target, editedModule);
        }

        @Override
        public ReadOnlyModule getModule(ModuleCode moduleCode) {
            return tracker.getModule(moduleCode);
        }

        @Override
        public void addModule(Module module) {
            moduleList.add(module);
            moduleCodeList.add(module.getCode());
        }
    }
}
