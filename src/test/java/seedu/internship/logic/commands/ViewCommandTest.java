package seedu.internship.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.internship.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internship.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.internship.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.internship.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.internship.testutil.TypicalInternships.getTypicalInternBuddy;

import org.junit.jupiter.api.Test;

import seedu.internship.commons.core.Messages;
import seedu.internship.commons.core.index.Index;
import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;
import seedu.internship.model.UserPrefs;
import seedu.internship.model.internship.Internship;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewCommand}.
 */
public class ViewCommandTest {



    private Model model = new ModelManager(getTypicalInternBuddy(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Internship internshipToView = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_INTERNSHIP);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_INTERNSHIP_SUCCESS, internshipToView);

        ModelManager expectedModel = new ModelManager(model.getInternBuddy(), new UserPrefs());
        expectedModel.viewInternship(internshipToView);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Internship internshipToView = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_INTERNSHIP);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_INTERNSHIP_SUCCESS, internshipToView);


        Model expectedModel = new ModelManager(model.getInternBuddy(), new UserPrefs());
        showInternshipAtIndex(expectedModel, INDEX_FIRST_INTERNSHIP);
        expectedModel.viewInternship(internshipToView);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        // ensures that outOfBoundIndex is still in bounds of internship list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternBuddy().getInternshipList().size());

        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_INTERNSHIP);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_SECOND_INTERNSHIP);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(INDEX_FIRST_INTERNSHIP);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different internship -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

}
