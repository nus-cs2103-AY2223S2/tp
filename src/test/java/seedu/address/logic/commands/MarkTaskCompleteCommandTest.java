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

class MarkTaskCompleteCommandTest {

    private Model model = new ModelManager(getTypicalMathutoring(), new UserPrefs());

    @Test
    public void constructor_nullIndexes_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkTaskCompleteCommand(null, null));
    }

    @Test
    void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundPersonIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        MarkTaskCompleteCommand markTaskCompleteCommand = new MarkTaskCompleteCommand(outOfBoundPersonIndex,
                INDEX_FIRST_TASK);

        assertCommandFailure(markTaskCompleteCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    void execute_validStudentIndexInvalidTaskIndexUnfilteredList_failure() {
        Index outOfBoundTaskIndex = Index.fromOneBased(model.getFilteredStudentList()
                .get(INDEX_FIRST_STUDENT.getZeroBased()).getTaskList().asUnmodifiableObservableList().size() + 1);

        MarkTaskCompleteCommand markTaskCompleteCommand = new MarkTaskCompleteCommand(INDEX_FIRST_STUDENT,
                outOfBoundTaskIndex);

        assertCommandFailure(markTaskCompleteCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MarkTaskCompleteCommand markFirstTaskCompleteCommand = new MarkTaskCompleteCommand(INDEX_FIRST_STUDENT,
                INDEX_FIRST_TASK);
        MarkTaskCompleteCommand markSecondTaskCompleteCommand = new MarkTaskCompleteCommand(INDEX_FIRST_STUDENT,
                INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(markFirstTaskCompleteCommand.equals(markFirstTaskCompleteCommand));

        // same values -> returns true
        MarkTaskCompleteCommand markFirstTaskCompleteCommandCopy = new MarkTaskCompleteCommand(INDEX_FIRST_STUDENT,
                INDEX_FIRST_TASK);
        assertTrue(markFirstTaskCompleteCommand.equals(markFirstTaskCompleteCommandCopy));

        // different types -> returns false
        assertFalse(markFirstTaskCompleteCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstTaskCompleteCommand.equals(null));

        // different student -> returns false
        assertFalse(markFirstTaskCompleteCommand.equals(markSecondTaskCompleteCommand));
    }
}
