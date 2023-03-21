package tfifteenfour.clipboard.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandFailure;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static tfifteenfour.clipboard.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static tfifteenfour.clipboard.testutil.TypicalStudents.getTypicalRoster;

import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.commons.core.Messages;
import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.ModelManager;
import tfifteenfour.clipboard.model.UserPrefs;
import tfifteenfour.clipboard.model.student.Student;

public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalRoster(), new UserPrefs());

    @Test
    public void execute_validIndexFilteredList_success() {
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);

        Student studentToView = model.getFilteredStudentList().get(INDEX_SECOND_PERSON.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_SECOND_PERSON);

        String expectedMessage = String.format("Viewing: %s, (%s)",
                studentToView.getName(), studentToView.getStudentId());

        Model expectedModel = new ModelManager(model.getRoster(), new UserPrefs());

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_PERSON);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(INDEX_FIRST_PERSON);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
