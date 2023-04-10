package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2103;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.ModuleEditInfo;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.edit.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.LectureUtil;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ObjectUtil;
import seedu.address.testutil.TypicalModules;

/**
 * Contains integration tests (interactions with the Model) and unit tests for {@code EditModuleCommand}.
 */
public class EditModuleCommandTest {

    private final ModuleCode moduleCode = new ModuleCode(VALID_MODULE_CODE_2040);
    private final EditModuleDescriptor descriptor = CommandTestUtil.getEditModuleDescriptorCs2103();

    private final Model model = new ModelManager();

    @Test
    public void constructor_nullModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditModuleCommand(null, descriptor));
    }

    @Test
    public void constructor_nullEditModuleDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditModuleCommand(moduleCode, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        EditModuleCommand command = new EditModuleCommand(moduleCode, descriptor);

        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_editAcceptedByModel_success() {
        /* Setup */
        // Setup modules
        Module originalModule = TypicalModules.getCs2040s();
        Module editedModule = new ModuleBuilder(TypicalModules.getSt2334())
                .withLectures(LectureUtil.convertToLectureArray(originalModule.getLectureList()))
                .build();

        // Setup command
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        EditModuleCommand command = new EditModuleCommand(originalModule.getCode(), descriptor);

        // Setup model
        model.addModule(originalModule);

        /* Create expected results */
        // Create expected command result
        String expectedMessage = String.format(EditModuleCommand.MESSAGE_SUCCESS, editedModule);
        ModuleEditInfo expectedEditInfo = new ModuleEditInfo(originalModule, editedModule);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, expectedEditInfo);

        // Create expected model
        Model expectedModel = new ModelManager();
        expectedModel.addModule(editedModule);

        /* Execute test */
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_moduleDoesNotExist_failure() {
        /* Setup */
        EditModuleCommand command = new EditModuleCommand(moduleCode, descriptor);

        /* Create expected results */
        String expectedMessage = String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode);

        /* Execute test */
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_duplicateModule_failure() {
        /* Setup */
        // Setup modules
        Module originalModule = new ModuleBuilder(TypicalModules.getCs2040s()).build();
        Module editedModule = new ModuleBuilder(TypicalModules.getSt2334())
                .withLectures(LectureUtil.convertToLectureArray(originalModule.getLectureList()))
                .build();

        // Setup descriptor
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();

        // Setup model
        model.addModule(originalModule);
        model.addModule(editedModule);

        // Setup command
        EditModuleCommand command = new EditModuleCommand(originalModule.getCode(), descriptor);

        /* Create expected results */
        String expectedMessage = EditModuleCommand.MESSAGE_DUPLICATE_MODULE;

        /* Execute test */
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        EditModuleCommand command = new EditModuleCommand(moduleCode, descriptor);

        EditModuleCommand commandWithSameValues = new EditModuleCommand(moduleCode, descriptor);

        EditModuleCommand commandWithDiffModuleCode =
                new EditModuleCommand(new ModuleCode(VALID_MODULE_CODE_2103), descriptor);
        EditModuleCommand commandWithDiffDescriptor =
                new EditModuleCommand(moduleCode, CommandTestUtil.getEditModuleDescriptorCs2040s());

        ObjectUtil.testEquals(command, commandWithSameValues, 1,
                commandWithDiffModuleCode, commandWithDiffDescriptor);
    }

}
