package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalScores.SCORE_1;
import static seedu.address.testutil.TypicalScores.SCORE_2;
import static seedu.address.testutil.TypicalScores.SCORE_3;
import static seedu.address.testutil.TypicalStudents.getTypicalMathutoring;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.score.Score;
import seedu.address.model.student.Student;
import seedu.address.testutil.ScoreBuilder;


class AddScoreCommandTest {

    private Model model = new ModelManager(getTypicalMathutoring(), new UserPrefs());

    @Test
    public void constructor_nullScoreAndIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddScoreCommand(null, null));
    }

    @Test
    void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        AddScoreCommand addScoreCommand = new AddScoreCommand(outOfBoundIndex, SCORE_1);

        assertCommandFailure(addScoreCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_addValidScore_success() throws Exception {
        Score validScore = new ScoreBuilder().build();
        Student studentToAddScoreTo = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        CommandResult commandResult = new AddScoreCommand(INDEX_FIRST_STUDENT, validScore).execute(model);

        assertEquals(String.format(AddScoreCommand.MESSAGE_SUCCESS, studentToAddScoreTo.getName(),
                "Label: " + validScore.getLabel().toString() + "; "
                        + "Score: " + validScore.getValue().toString() + "; "
                        + "Date: " + validScore.getDate().toString()), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_addScoreDuplicate_throwsCommandException() {
        assertThrows(CommandException.class, (
                ) -> {
            new AddScoreCommand(INDEX_FIRST_STUDENT, SCORE_2).execute(model);
            new AddScoreCommand(INDEX_FIRST_STUDENT, SCORE_3).execute(model);
        }
        );
    }

    @Test
    public void equals() {
        Score firstScore = new ScoreBuilder().withLabel("Do Homework").withValue("80").withDate("2023-01-09").build();
        Score secondScore = new ScoreBuilder().withLabel("Do Homework").withValue("63").withDate("2022-04-12").build();
        AddScoreCommand addFirstScoreCommand = new AddScoreCommand(INDEX_FIRST_STUDENT, firstScore);
        AddScoreCommand addSecondScoreCommand = new AddScoreCommand(INDEX_FIRST_STUDENT, secondScore);

        // same object -> returns true
        assertTrue(addFirstScoreCommand.equals(addFirstScoreCommand));

        // same values -> returns true
        AddScoreCommand addFirstScoreCommandCopy = new AddScoreCommand(INDEX_FIRST_STUDENT, firstScore);
        assertTrue(addFirstScoreCommand.equals(addFirstScoreCommandCopy));

        // different types -> returns false
        assertFalse(addFirstScoreCommand.equals(1));

        // null -> returns false
        assertFalse(addFirstScoreCommand.equals(null));

        // different student -> returns false
        assertFalse(addFirstScoreCommand.equals(addSecondScoreCommand));
    }
}
