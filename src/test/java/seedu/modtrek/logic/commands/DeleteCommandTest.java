package seedu.modtrek.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.modtrek.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modtrek.testutil.TypicalModules.CS1101S;
import static seedu.modtrek.testutil.TypicalModules.CS2100;
import static seedu.modtrek.testutil.TypicalModules.ST2334;
import static seedu.modtrek.testutil.TypicalModules.getTypicalDegreeProgression;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.modtrek.model.DegreeProgression;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.ModelManager;
import seedu.modtrek.model.UserPrefs;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.Module;

class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalDegreeProgression(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Module moduleToDelete = model.getFilteredModuleList().get(0);
        Set<Code> codesToDelete = new HashSet<>();
        codesToDelete.add(CS2100.getCode());
        DeleteCommand deleteCommand = new DeleteCommand(false, codesToDelete);

        String codesFound = String.format(DeleteCommand.MESSAGE_DELETE_MODULE_SUCCESS, codesToDelete);

        ModelManager expectedModel = new ModelManager(model.getDegreeProgression(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);

        assertCommandSuccess(deleteCommand, model, codesFound, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Set<Code> codesToDelete = new HashSet<>();
        codesToDelete.add(CS1101S.getCode());
        DeleteCommand deleteCommand = new DeleteCommand(false, codesToDelete);

        String codesNotFound = String.format(DeleteCommand.MESSAGE_DELETE_MODULE_NOT_FOUND, codesToDelete);

        assertCommandFailure(deleteCommand, model, codesNotFound);
    }

    @Test
    public void execute_validAndInvalidIndexUnfilteredList_success() {
        Module moduleToDelete = model.getFilteredModuleList().get(0);
        Set<Code> codesToDelete = new HashSet<>();
        codesToDelete.add(CS2100.getCode());
        codesToDelete.add(CS1101S.getCode());
        DeleteCommand deleteCommand = new DeleteCommand(false, codesToDelete);

        codesToDelete.remove(CS1101S.getCode());
        String codesFound = String.format(DeleteCommand.MESSAGE_DELETE_MODULE_SUCCESS, codesToDelete);
        codesToDelete.remove(CS2100.getCode());
        codesToDelete.add(CS1101S.getCode());
        String codesNotFound = String.format(DeleteCommand.MESSAGE_DELETE_MODULE_NOT_FOUND, codesToDelete);
        codesToDelete.add(CS2100.getCode());
        String expectedMessage = codesFound + "\n" + codesNotFound;

        ModelManager expectedModel = new ModelManager(model.getDegreeProgression(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validAllKeyword_success() {
        Set<Code> codesToDelete = new HashSet<>();
        DeleteCommand deleteCommand = new DeleteCommand(true, codesToDelete);

        String expectedMessage = DeleteCommand.MESSAGE_DELETE_ALL_MODULES_SUCCESS;

        Model expectedModel = new ModelManager(new DegreeProgression(), new UserPrefs());
        showNoModule(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        Set<Code> codesToDelete = new HashSet<>();
        codesToDelete.add(CS2100.getCode());
        Set<Code> codesToDelete2 = new HashSet<>();
        codesToDelete.add(ST2334.getCode());
        DeleteCommand deleteFirstCommand = new DeleteCommand(false, codesToDelete);
        DeleteCommand deleteSecondCommand = new DeleteCommand(false, codesToDelete2);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(false, codesToDelete);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different module -> returns false
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
