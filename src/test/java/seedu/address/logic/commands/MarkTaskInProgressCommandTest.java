package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalStudents.getTypicalMathutoring;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class MarkTaskInProgressCommandTest {

    private Model model = new ModelManager(getTypicalMathutoring(), new UserPrefs());

    @Test
    public void constructor_nullIndexes_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkTaskInProgressCommand(null, null));
    }

    @Test
    void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundPersonIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        MarkTaskInProgressCommand markTaskInProgressCommand = new MarkTaskInProgressCommand(outOfBoundPersonIndex,
                INDEX_FIRST_TASK);

        assertCommandFailure(markTaskInProgressCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    void execute_validStudentIndexInvalidTaskIndexUnfilteredList_failure() {
        Index outOfBoundTaskIndex = Index.fromOneBased(model.getFilteredStudentList()
                .get(INDEX_FIRST_STUDENT.getZeroBased()).getTaskList().asUnmodifiableObservableList().size() + 1);

        MarkTaskInProgressCommand markTaskInProgressCommand = new MarkTaskInProgressCommand(INDEX_FIRST_STUDENT,
                outOfBoundTaskIndex);

        assertCommandFailure(markTaskInProgressCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkTaskInProgressCommand markFirstTaskInProgressCommand = new MarkTaskInProgressCommand(INDEX_FIRST_STUDENT,
                INDEX_FIRST_TASK);
        MarkTaskInProgressCommand markSecondTaskInProgressCommand = new MarkTaskInProgressCommand(INDEX_FIRST_STUDENT,
                INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(markFirstTaskInProgressCommand.equals(markFirstTaskInProgressCommand));

        // same values -> returns true
        MarkTaskInProgressCommand markFirstTaskInProgressCommandCopy = new MarkTaskInProgressCommand(
                INDEX_FIRST_STUDENT, INDEX_FIRST_TASK);
        assertTrue(markFirstTaskInProgressCommand.equals(markFirstTaskInProgressCommandCopy));

        // different types -> returns false
        assertFalse(markFirstTaskInProgressCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstTaskInProgressCommand.equals(null));

        // different student -> returns false
        assertFalse(markFirstTaskInProgressCommand.equals(markSecondTaskInProgressCommand));
    }
}
