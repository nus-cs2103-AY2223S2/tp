package seedu.address.logic.commands.add;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.ModuleEditInfo;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.module.Module;
import seedu.address.testutil.ObjectUtil;
import seedu.address.testutil.TypicalModules;

/**
 * Contains integration tests (interaction with the {@code Model}) and unit tests for {@code AddModuleCommand}.
 */
public class AddModuleCommandTest {

    private final Module module = TypicalModules.getCs2040s();

    private final Model model = new ModelManager();

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddModuleCommand(null));
    }

    @Test
    public void execute_moduleAcceptedByModel_success() throws CommandException {
        /* Setup */
        AddModuleCommand command = new AddModuleCommand(module);

        /* Create expected results */
        String expectedMessage = String.format(AddModuleCommand.MESSAGE_SUCCESS, module);
        ModuleEditInfo expectedEditInfo = new ModuleEditInfo(null, module);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, expectedEditInfo);

        Model expectedModel = new ModelManager();
        expectedModel.addModule(module);

        /* Execute test */
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        AddModuleCommand command = new AddModuleCommand(TypicalModules.getCs2040s());

        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_duplicateModule_failure() {
        /* Setup */
        AddModuleCommand command = new AddModuleCommand(module);
        model.addModule(module);

        /* Create expected results */
        String expectedMessage = String.format(AddModuleCommand.MESSAGE_DUPLICATE_MODULE);

        /* Execute test */
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        AddModuleCommand command = new AddModuleCommand(module);
        AddModuleCommand commandWithSameValue = new AddModuleCommand(module);
        AddModuleCommand commandWithDiffModule = new AddModuleCommand(TypicalModules.getSt2334());

        ObjectUtil.testEquals(command, commandWithSameValue, 1, commandWithDiffModule);
    }

}
