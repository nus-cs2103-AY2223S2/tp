package seedu.internship.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.logic.commands.CommandTestUtil.ONE_FIELD_PREDICATE;
import static seedu.internship.logic.commands.CommandTestUtil.SIMPLE_PREDICATE;
import static seedu.internship.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internship.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.internship.logic.commands.DeleteFieldCommand.MESSAGE_DELETE_INTERNSHIP_SUCCESS;
import static seedu.internship.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.internship.testutil.TypicalInternships.getTypicalInternBuddy;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.UserPrefs;
import seedu.internship.model.internship.Internship;




/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteFieldCommandTest {
    private Model model = new ModelManager(getTypicalInternBuddy(), new UserPrefs());

    @Test
    public void execute_simplePredicateUnfilteredList_success() {
        List<Internship> internshipsToDelete = model.getFilteredInternshipList().stream()
                .filter(SIMPLE_PREDICATE)
                .collect(Collectors.toList());

        DeleteFieldCommand deleteCommand = new DeleteFieldCommand(SIMPLE_PREDICATE);

        String expectedMessage = String.format(MESSAGE_DELETE_INTERNSHIP_SUCCESS, 1);

        ModelManager expectedModel = new ModelManager(model.getInternBuddy(), new UserPrefs());
        for (Internship internshipToDelete: internshipsToDelete) {
            expectedModel.deleteInternship(internshipToDelete);
        }

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneFieldPredicateUnfilteredList_success() {
        List<Internship> internshipsToDelete = model.getFilteredInternshipList().stream()
                .filter(ONE_FIELD_PREDICATE)
                .collect(Collectors.toList());

        DeleteFieldCommand deleteCommand = new DeleteFieldCommand(ONE_FIELD_PREDICATE);

        String expectedMessage = String.format(MESSAGE_DELETE_INTERNSHIP_SUCCESS, 2);

        ModelManager expectedModel = new ModelManager(model.getInternBuddy(), new UserPrefs());

        for (Internship internshipToDelete: internshipsToDelete) {
            expectedModel.deleteInternship(internshipToDelete);
        }

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_simplePredicateFilteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        List<Internship> internshipsToDelete = model.getFilteredInternshipList().stream()
                .filter(SIMPLE_PREDICATE)
                .collect(Collectors.toList());

        DeleteFieldCommand deleteCommand = new DeleteFieldCommand(SIMPLE_PREDICATE);

        String expectedMessage = String.format(DeleteFieldCommand.MESSAGE_DELETE_INTERNSHIP_SUCCESS, 1);

        Model expectedModel = new ModelManager(model.getInternBuddy(), new UserPrefs());

        for (Internship internshipToDelete: internshipsToDelete) {
            expectedModel.deleteInternship(internshipToDelete);
        }

        showNoInternship(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void equals() {
        DeleteFieldCommand deleteFirstCommand = new DeleteFieldCommand(SIMPLE_PREDICATE);
        DeleteFieldCommand deleteSecondCommand = new DeleteFieldCommand(ONE_FIELD_PREDICATE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteFieldCommand deleteFirstCommandCopy = new DeleteFieldCommand(SIMPLE_PREDICATE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different internship -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no internship entry.
     */
    private void showNoInternship(Model model) {
        model.updateFilteredInternshipList(p -> false);

        assertTrue(model.getFilteredInternshipList().isEmpty());
    }
}
