package seedu.address.testutil;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_LABEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_VALUE;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.score.Date;
import seedu.address.model.score.Label;
import seedu.address.model.score.Score;
import seedu.address.model.score.ScoreValue;
import seedu.address.model.score.UniqueScoreList;

/**
 * A utility class containing a list of {@code Score} objects to be used in tests.
 */
public class TypicalMockScores {
    public static final Score SCORE_1 = mock(Score.class);
    public static final Label SCORE_1_NAME = new Label("MA1521 Final Exam");
    public static final ScoreValue SCORE_1_VALUE = new ScoreValue("80");
    public static final Date SCORE_1_DATE = new Date("2022-03-09");
    public static final Score SCORE_2 = mock(Score.class);
    public static final Label SCORE_2_NAME = new Label("MA2100 Final Exam");
    public static final ScoreValue SCORE_2_VALUE = new ScoreValue("70");
    public static final Date SCORE_2_DATE = new Date("2022-03-09");
    public static final Score SCORE_3 = mock(Score.class);
    public static final Label SCORE_3_NAME = new Label("MA2100 Final Exam");
    public static final ScoreValue SCORE_3_VALUE = new ScoreValue("70");
    public static final Date SCORE_3_DATE = new Date("2022-03-09");

    // Manually added - Score's details found in {@code CommandTestUtil}

    public static final Score SCORE_4 = mock(Score.class);
    public static final Label SCORE_4_NAME = new Label(VALID_SCORE_LABEL);
    public static final ScoreValue SCORE_4_VALUE = new ScoreValue(VALID_SCORE_VALUE);
    public static final Date SCORE_4_DATE = new Date(VALID_SCORE_DATE);

    private TypicalMockScores() {} // prevents instantiation

    public static void setScore1() {
        doReturn(SCORE_1_NAME).when(SCORE_1).getLabel();
        doReturn(SCORE_1_DATE).when(SCORE_1).getDate();
        doReturn(SCORE_1_VALUE).when(SCORE_1).getValue();
    }

    public static void setScore2() {
        doReturn(SCORE_2_NAME).when(SCORE_2).getLabel();
        doReturn(SCORE_2_DATE).when(SCORE_2).getDate();
        doReturn(SCORE_2_VALUE).when(SCORE_2).getValue();
    }

    public static void setScore3() {
        doReturn(SCORE_3_NAME).when(SCORE_3).getLabel();
        doReturn(SCORE_3_DATE).when(SCORE_3).getDate();
        doReturn(SCORE_3_VALUE).when(SCORE_3).getValue();
    }

    public static void setScore4() {
        doReturn(SCORE_4_NAME).when(SCORE_4).getLabel();
        doReturn(SCORE_4_DATE).when(SCORE_4).getDate();
        doReturn(SCORE_4_VALUE).when(SCORE_4).getValue();
    }

    public static UniqueScoreList getTypicalScores() {
        setScore1();
        setScore2();
        setScore3();
        setScore4();
        UniqueScoreList uniqueScoreList = mock(UniqueScoreList.class);
        uniqueScoreList.add(SCORE_1);
        uniqueScoreList.add(SCORE_2);
        uniqueScoreList.add(SCORE_3);
        uniqueScoreList.add(SCORE_4);
        ObservableList<Score> observableList = FXCollections.observableArrayList();
        observableList.add(SCORE_1);
        observableList.add(SCORE_2);
        observableList.add(SCORE_3);
        observableList.add(SCORE_4);
        doReturn(observableList).when(uniqueScoreList).getSortedScoreList();
        return uniqueScoreList;
    }
}
