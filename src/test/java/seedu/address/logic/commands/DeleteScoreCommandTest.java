package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DeleteScoreCommand.MESSAGE_DELETE_SCORE_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SCORE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_SCORE;
import static seedu.address.testutil.TypicalScores.SCORE_0;
import static seedu.address.testutil.TypicalStudents.getTypicalMathutoring;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;

class DeleteScoreCommandTest {

    private Model model = new ModelManager(getTypicalMathutoring(), new UserPrefs());

    /*@Test
    public void constructor_nullIndexes_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteScoreCommand(null, null));
    }*/

    @Test
    void execute_validIndexScoreDelete_success() {
        Student studentToDeleteScore = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        if (studentToDeleteScore.getScoreList().size() == 0) {
            studentToDeleteScore.addScore(SCORE_0);
        }

        DeleteScoreCommand scoreToDelete = new DeleteScoreCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_SCORE);

        String expectedMessage = String.format(MESSAGE_DELETE_SCORE_SUCCESS,
                studentToDeleteScore.getName(), studentToDeleteScore.getScoreList().get(0).toString());

        ModelManager expectedModel = new ModelManager(model.getMathutoring(), new UserPrefs());

        assertCommandSuccess(scoreToDelete, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundPersonIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        DeleteScoreCommand deleteScoreCommand = new DeleteScoreCommand(outOfBoundPersonIndex, INDEX_FIRST_SCORE);

        assertCommandFailure(deleteScoreCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    void execute_validStudentIndexInvalidScoreIndexUnfilteredList_failure() {
        Index outOfBoundTaskIndex = Index.fromOneBased(model.getFilteredStudentList().get(INDEX_FIRST_STUDENT
                .getZeroBased()).getTaskList().asUnmodifiableObservableList().size() + 1);

        DeleteScoreCommand deleteScoreCommand = new DeleteScoreCommand(INDEX_FIRST_STUDENT, outOfBoundTaskIndex);

        assertCommandFailure(deleteScoreCommand, model, Messages.MESSAGE_INVALID_SCORE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteScoreCommand deleteFirstScoreCommand = new DeleteScoreCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_SCORE);
        DeleteScoreCommand deleteSecondScoreCommand = new DeleteScoreCommand(INDEX_FIRST_STUDENT, INDEX_SECOND_SCORE);

        // same object -> returns true
        assertTrue(deleteFirstScoreCommand.equals(deleteFirstScoreCommand));

        // same values -> returns true
        DeleteScoreCommand deleteFirstScoreCommandCopy = new DeleteScoreCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_SCORE);
        assertTrue(deleteFirstScoreCommand.equals(deleteFirstScoreCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstScoreCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstScoreCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteFirstScoreCommand.equals(deleteSecondScoreCommand));
    }
}
