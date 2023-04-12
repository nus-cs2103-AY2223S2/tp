package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalMathutoring;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CheckCommand}.
 */
class CheckCommandTest {
    private Model model = new ModelManager(getTypicalMathutoring(), new UserPrefs());

    @Test
    public void execute_validCheckIndexList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentToCheck = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        CheckCommand checkCommand = new CheckCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(CheckCommand.MESSAGE_CHECK_STUDENT_SUCCESS, studentToCheck);

        Model expectedModel = new ModelManager(model.getMathutoring(), new UserPrefs());
        expectedModel.checkStudent(studentToCheck);

        showStudentAtIndex(expectedModel, INDEX_FIRST_STUDENT);

        assertCommandSuccess(checkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToCheck = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        CheckCommand checkCommand = new CheckCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(CheckCommand.MESSAGE_CHECK_STUDENT_SUCCESS, studentToCheck);

        ModelManager expectedModel = new ModelManager(model.getMathutoring(), new UserPrefs());
        expectedModel.checkStudent(studentToCheck);

        assertCommandSuccess(checkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidCheckIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        CheckCommand checkCommand = new CheckCommand(outOfBoundIndex);

        assertCommandFailure(checkCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidCheckIndexFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMathutoring().getStudentList().size());

        CheckCommand checkCommand = new CheckCommand(outOfBoundIndex);

        assertCommandFailure(checkCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        CheckCommand checkFirstCommand = new CheckCommand(INDEX_FIRST_STUDENT);
        CheckCommand checkSecondCommand = new CheckCommand(INDEX_SECOND_STUDENT);

        // same object -> returns true
        assertTrue(checkFirstCommand.equals(checkFirstCommand));

        // same values -> returns true
        CheckCommand checkFirstCommandCopy = new CheckCommand(INDEX_FIRST_STUDENT);
        assertTrue(checkFirstCommand.equals(checkFirstCommandCopy));

        // different types -> returns false
        assertFalse(checkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(checkFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(checkFirstCommand.equals(checkSecondCommand));
    }
}
