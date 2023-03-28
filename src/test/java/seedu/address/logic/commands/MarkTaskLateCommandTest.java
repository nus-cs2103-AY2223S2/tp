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

class MarkTaskLateCommandTest {

    private Model model = new ModelManager(getTypicalMathutoring(), new UserPrefs());

    @Test
    public void constructor_nullIndexes_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkTaskLateCommand(null, null));
    }

    @Test
    void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundPersonIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        MarkTaskLateCommand markTaskLateCommand = new MarkTaskLateCommand(outOfBoundPersonIndex, INDEX_FIRST_TASK);

        assertCommandFailure(markTaskLateCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    void execute_validStudentIndexInvalidTaskIndexUnfilteredList_failure() {
        Index outOfBoundTaskIndex = Index.fromOneBased(model.getFilteredStudentList()
                .get(INDEX_FIRST_STUDENT.getZeroBased()).getTaskList().asUnmodifiableObservableList().size() + 1);

        MarkTaskLateCommand markTaskLateCommand = new MarkTaskLateCommand(INDEX_FIRST_STUDENT, outOfBoundTaskIndex);

        assertCommandFailure(markTaskLateCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkTaskLateCommand markFirstTaskLateCommand = new MarkTaskLateCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_TASK);
        MarkTaskLateCommand markSecondTaskLateCommand = new MarkTaskLateCommand(INDEX_FIRST_STUDENT, INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(markFirstTaskLateCommand.equals(markFirstTaskLateCommand));

        // same values -> returns true
        MarkTaskLateCommand markFirstTaskLateCommandCopy = new MarkTaskLateCommand(INDEX_FIRST_STUDENT,
                INDEX_FIRST_TASK);
        assertTrue(markFirstTaskLateCommand.equals(markFirstTaskLateCommandCopy));

        // different types -> returns false
        assertFalse(markFirstTaskLateCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstTaskLateCommand.equals(null));

        // different student -> returns false
        assertFalse(markFirstTaskLateCommand.equals(markSecondTaskLateCommand));
    }
}
