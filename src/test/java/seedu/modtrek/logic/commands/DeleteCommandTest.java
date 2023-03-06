package seedu.modtrek.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.modtrek.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modtrek.logic.commands.DeleteCommand.MESSAGE_DELETE_MODULE_NOT_FOUND;
import static seedu.modtrek.testutil.TypicalModules.CS1101S;
import static seedu.modtrek.testutil.TypicalModules.CS2100;
import static seedu.modtrek.testutil.TypicalModules.ST2334;
import static seedu.modtrek.testutil.TypicalModules.getTypicalDegreeProgression;

import org.junit.jupiter.api.Test;

import seedu.modtrek.model.Model;
import seedu.modtrek.model.ModelManager;
import seedu.modtrek.model.UserPrefs;
import seedu.modtrek.model.module.Module;

class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalDegreeProgression(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Module personToDelete = model.getFilteredModuleList().get(0);
        DeleteCommand deleteCommand = new DeleteCommand(CS2100.getCode());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_MODULE_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getDegreeProgression(), new UserPrefs());
        expectedModel.deleteModule(personToDelete);
        showNoModule(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(CS1101S.getCode());

        assertCommandFailure(deleteCommand, model, String.format(MESSAGE_DELETE_MODULE_NOT_FOUND, CS1101S.getCode()));
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Module personToDelete = model.getFilteredModuleList().get(0);
        DeleteCommand deleteCommand = new DeleteCommand(CS2100.getCode());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_MODULE_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getDegreeProgression(), new UserPrefs());
        expectedModel.deleteModule(personToDelete);
        showNoModule(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(CS2100.getCode());
        DeleteCommand deleteSecondCommand = new DeleteCommand(ST2334.getCode());

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(CS2100.getCode());
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoModule(Model model) {
        model.updateFilteredModuleList(p -> false);

        assertTrue(model.getFilteredModuleList().isEmpty());
    }

}
