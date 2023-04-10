package seedu.address.logic.commands.delete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandResult.ModuleEditInfo;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.testutil.TypicalModules;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteMultipleModulesCommand}
 */
public class DeleteMultipleModulesCommandTest {

    @Test
    public void execute_deleteMultipleModules_success() throws CommandException {
        ReadOnlyModule mod1 = TypicalModules.getCs2040s();
        ReadOnlyModule mod2 = TypicalModules.getCs2107();

        DeleteMultipleModulesCommand dmmc = new DeleteMultipleModulesCommand(mod1.getCode(), mod2.getCode());
        DeleteCommandModelStub model = new DeleteCommandModelStub();

        CommandResult actual = dmmc.execute(model);

        DeleteCommandModelStub expectedModel = new DeleteCommandModelStub();
        expectedModel.deleteModule(mod1);
        expectedModel.deleteModule(mod2);

        // tests string output
        assertEquals(new CommandResult(String.format(DeleteMultipleModulesCommand.MESSAGE_SUCCESS,
                        "2", mod1.getCode() + ", " + mod2.getCode()),
                new ModuleEditInfo(mod1, null),
                new ModuleEditInfo(mod2, null)),
                actual);

        // tests model
        assertEquals(expectedModel, model);
    }

    @Test
    public void execute_toDeleteDoesNotExist_throwsCommandException() {
        // module does not exist
        assertThrows(CommandException.class, () -> new DeleteMultipleModulesCommand(
                        TypicalModules.getCs2040s().getCode(),
                        TypicalModules.getSt2334().getCode()
                ).execute(new ModelStubNoModule()));
    }

    @Test
    public void equals() {
        ModuleCode modCs2040s = TypicalModules.getCs2040s().getCode();
        ModuleCode modSt2334 = TypicalModules.getSt2334().getCode();

        DeleteMultipleModulesCommand deleteCs2040sSt2334Command =
                new DeleteMultipleModulesCommand(modCs2040s, modSt2334);
        DeleteMultipleModulesCommand deleteSt2334Command =
                new DeleteMultipleModulesCommand(modSt2334);

        // same object -> returns true
        assertTrue(deleteCs2040sSt2334Command.equals(deleteCs2040sSt2334Command));

        // same value -> returns true
        DeleteMultipleModulesCommand secondDeleteCs2040sCommand =
                new DeleteMultipleModulesCommand(modCs2040s, modSt2334);
        assertTrue(deleteCs2040sSt2334Command.equals(secondDeleteCs2040sCommand));

        // different values -> returns false
        assertFalse(deleteCs2040sSt2334Command.equals(deleteSt2334Command));

        // different types -> returns false
        assertFalse(deleteSt2334Command.equals("command"));

        // null -> returns false
        assertFalse(deleteSt2334Command.equals(null));
    }
}
