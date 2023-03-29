package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_MODULE_DESC_CS2040S;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_MODULE_DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2103;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.ModuleEditInfo;
import seedu.address.logic.commands.edit.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.lecture.Lecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TypicalModules;

/**
 * Contains integration tests (interactions with the Model) and unit tests for {@code EditModuleCommand}.
 */
public class EditModuleCommandTest {

    private final Model model = new ModelManager();

    @Test
    public void constructor_nullModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditModuleCommand(null, EDIT_MODULE_DESC_CS2103));
    }

    @Test
    public void constructor_nullEditModuleDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditModuleCommand(new ModuleCode(VALID_MODULE_CODE_2040), null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        EditModuleCommand command = new EditModuleCommand(
                new ModuleCode(VALID_MODULE_CODE_2040), EDIT_MODULE_DESC_CS2103);

        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_editAcceptedByModel_success() {
        Module originalModule = new ModuleBuilder(TypicalModules.getCs2040s()).build();
        Module editedModule = new ModuleBuilder(TypicalModules.getSt2334()).withLectures().build();
        originalModule.getLectureList().stream().forEach(l -> editedModule.addLecture((Lecture) l));

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();

        EditModuleCommand command = new EditModuleCommand(originalModule.getCode(), descriptor);

        model.addModule(originalModule);

        String expectedMessage = String.format(EditModuleCommand.MESSAGE_SUCCESS, editedModule);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage,
                new ModuleEditInfo(originalModule, editedModule));

        Model expectedModel = new ModelManager();
        expectedModel.addModule(editedModule);

        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_moduleDoesNotExist_failure() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        EditModuleDescriptor descriptor = EDIT_MODULE_DESC_CS2040S;

        EditModuleCommand command = new EditModuleCommand(moduleCode, descriptor);

        String expectedMessage = String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode);
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_duplicateModule_failure() {
        Module originalModule = new ModuleBuilder(TypicalModules.getCs2040s()).build();
        Module editedModule = new ModuleBuilder(TypicalModules.getSt2334()).withLectures().build();
        originalModule.getLectureList().stream().forEach(l -> editedModule.addLecture((Lecture) l));

        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();

        model.addModule(originalModule);
        model.addModule(editedModule);

        EditModuleCommand command = new EditModuleCommand(originalModule.getCode(), descriptor);

        String expectedMessage = EditModuleCommand.MESSAGE_DUPLICATE_MODULE;
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2103);
        EditModuleCommand command = new EditModuleCommand(moduleCode, EDIT_MODULE_DESC_CS2040S);

        // same values -> returns true
        EditModuleCommand commandWithSameValues = new EditModuleCommand(moduleCode, EDIT_MODULE_DESC_CS2040S);
        assertTrue(command.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(command.equals(command));

        // null -> returns false
        assertFalse(command.equals(null));

        // different types -> returns false
        assertFalse(command.equals(1));

        // different module code -> returns false
        assertFalse(command.equals(
                new EditModuleCommand(new ModuleCode(VALID_MODULE_CODE_2040), EDIT_MODULE_DESC_CS2040S)));

        // different descriptor -> returns false
        assertFalse(command.equals(
                new EditModuleCommand(moduleCode, EDIT_MODULE_DESC_CS2103)));
    }

}
