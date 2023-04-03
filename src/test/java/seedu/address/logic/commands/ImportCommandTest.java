package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ARCHIVE_FILE_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2103;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Tracker;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.TypicalModules;

public class ImportCommandTest {
    @TempDir
    public Path testFolder;
    private final Model model = new ModelStubWithFilledTracker();

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        ImportCommand command = new ImportCommand(VALID_ARCHIVE_FILE_NAME, new HashSet<>(), false,
                true);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void equals() {
        ImportCommand importCommand = new ImportCommand(VALID_ARCHIVE_FILE_NAME, new HashSet<>(), false,
                true);

        ImportCommand importCommandCopy = new ImportCommand(VALID_ARCHIVE_FILE_NAME, new HashSet<>(), false,
                true);

        ImportCommand exportCommandOverwrite = new ImportCommand(VALID_ARCHIVE_FILE_NAME, new HashSet<>(),
                true, true);

        assertTrue(importCommand.equals(importCommand));
        assertTrue(importCommand.equals(importCommandCopy));
        assertFalse(importCommand.equals(1));
        assertFalse(importCommand.equals(exportCommandOverwrite));
    }

    @Test
    public void execute_correctCommand_returnCommandResult() throws CommandException {
        Path archivePath = testFolder.resolve(VALID_ARCHIVE_FILE_NAME);
        List<String> listOfModuleCodes = List.of(new ModuleCode(VALID_MODULE_CODE_2040).code,
                new ModuleCode(VALID_MODULE_CODE_2103).code);
        CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS,
                String.join(", ", listOfModuleCodes)), archivePath,
                false, false,
                new HashSet<>(List.of(new ModuleCode(VALID_MODULE_CODE_2040), new ModuleCode(VALID_MODULE_CODE_2103))));
        ImportCommand importCommand = new ImportCommand(VALID_ARCHIVE_FILE_NAME,
                new HashSet<>(List.of(new ModuleCode(VALID_MODULE_CODE_2040), new ModuleCode(VALID_MODULE_CODE_2103))),
                false, false);
        assertEquals(expectedCommandResult, importCommand.execute(model));
        assertEquals(expectedCommandResult.getPath().get(), archivePath);
    }


    /**
     * A {@code Model} stub that contains a tracker.
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
}

