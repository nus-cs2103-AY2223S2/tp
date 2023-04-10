package seedu.address.logic.commands.delete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2103;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.ModuleEditInfo;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.TypicalModules;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteModuleCommand}
 */
public class DeleteModuleCommandTest {

    private Model model = new ModelManager(TypicalModules.getTypicalTracker(), new UserPrefs());

    @Test
    public void execute_deleteModule_success() throws CommandException {
        ModuleCode validModCode = new ModuleCode(VALID_MODULE_CODE_2040);
        DeleteModuleCommand delete = new DeleteModuleCommand(validModCode);

        CommandResult actual = delete.execute(model);
        Model expectedModel = new ModelManager(TypicalModules.getTypicalTracker(), new UserPrefs());
        expectedModel.deleteModule(expectedModel.getModule(validModCode));

        // tests string output
        assertEquals(new CommandResult(String.format(
                        DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS,
                        TypicalModules.getCs2040s().getCode()),
                new ModuleEditInfo(TypicalModules.getCs2040s(), null)),
                actual);
        // tests model
        assertEquals(expectedModel.getTracker().getModuleList(),
                model.getTracker().getModuleList());
    }

    @Test
    public void execute_moduleDoesNotExist_throwsCommandException() {
        // CS2103 module is not in the testing model
        assertThrows(CommandException.class, () -> new DeleteModuleCommand(
                                                                    new ModuleCode(VALID_MODULE_CODE_2103)
                                                            ).execute(model));
    }

    @Test
    public void equals() {
        ModuleCode modCs2040s = TypicalModules.getCs2040s().getCode();
        ModuleCode modSt2334 = TypicalModules.getSt2334().getCode();

        DeleteModuleCommand deleteCs2040sCommand = new DeleteModuleCommand(modCs2040s);
        DeleteModuleCommand deleteSt2334Command = new DeleteModuleCommand(modSt2334);

        // same object -> returns true
        assertTrue(deleteCs2040sCommand.equals(deleteCs2040sCommand));

        // same value -> returns true
        DeleteModuleCommand secondDeleteCs2040sCommand = new DeleteModuleCommand(modCs2040s);
        assertTrue(deleteCs2040sCommand.equals(secondDeleteCs2040sCommand));

        // different values -> returns false
        assertFalse(deleteCs2040sCommand.equals(deleteSt2334Command));

        // different types -> returns false
        assertFalse(deleteSt2334Command.equals("command"));

        // null -> returns false
        assertFalse(deleteSt2334Command.equals(null));
    }
}
