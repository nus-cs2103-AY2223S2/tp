package seedu.address.testutil;

import seedu.address.model.score.Date;
import seedu.address.model.score.Label;
import seedu.address.model.score.Score;
import seedu.address.model.score.ScoreValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static seedu.address.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Score} objects to be used in tests.
 */
public class TypicalMockScores {
    public static final Score SCORE_1 = mock(Score.class);
    public static final Label SCORE_1_NAME = new Label("MA1521 Final Exam");
    public static final ScoreValue SCORE_1_VALUE = new ScoreValue("80");
    public static final Date SCORE_1_DATE = new Date("2024-03-09");
    public static final Score SCORE_2 = mock(Score.class);
    public static final Label SCORE_2_NAME = new Label("MA2100 Final Exam");
    public static final ScoreValue SCORE_2_VALUE = new ScoreValue("70");
    public static final Date SCORE_2_DATE = new Date("2023-03-09");
    public static final Score SCORE_3 = mock(Score.class);
    public static final Label SCORE_3_NAME = new Label("MA2100 Final Exam");
    public static final ScoreValue SCORE_3_VALUE = new ScoreValue("70");
    public static final Date SCORE_3_DATE = new Date("2023-03-09");

    // Manually added - Score's details found in {@code CommandTestUtil}

    public static final Score SCORE_4 = mock(Score.class);
    public static final Label SCORE_4_NAME = new Label(VALID_SCORE_LABEL);
    public static final ScoreValue SCORE_4_VALUE = new ScoreValue(VALID_SCORE_VALUE);
    public static final Date SCORE_4_DATE = new Date(VALID_SCORE_DATE);

    private TypicalMockScores() {} // prevents instantiation

    public static void setScore1() {
        doReturn("MA1521 Final Exam").when(SCORE_1_NAME).toString();
        doReturn("2023-03-09").when(SCORE_1_DATE).toString();
    }

    public static void setScore2() {
        doReturn("MA2001 Final Exam").when(SCORE_2_NAME).toString();
        doReturn("2023-03-09").when(SCORE_2_DATE).toString();
    }

    public static void setScore3() {
        doReturn("MA1521 Final Exam").when(SCORE_3_NAME).toString();
        doReturn("2023-03-09").when(SCORE_3_DATE).toString();
    }

    public static void setScore4() {
        doReturn(VALID_SCORE_LABEL).when(SCORE_4_NAME).toString();
        doReturn(VALID_SCORE_DATE).when(SCORE_4_DATE).toString();
    }

    public static List<Score> getTypicalScores() {
        setScore1();
        setScore2();
        setScore3();
        setScore4();
        return new ArrayList<>(Arrays.asList(SCORE_1, SCORE_2, SCORE_3, SCORE_4));
    }
}
