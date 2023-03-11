package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_LABEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_VALUE;

import seedu.address.model.score.Score;

/**
 * A utility class containing a list of {@code Score} objects to be used in tests.
 */
public class TypicalScores {
    public static final Score SCORE_1 = new ScoreBuilder().withLabel("MA1521 Final Exam")
        .withValue("80")
        .withDate("2023-03-09").build();
    public static final Score SCORE_2 = new ScoreBuilder().withLabel("MA2100 Final Exam")
        .withValue("40")
        .withDate("2023-03-09").build();
    public static final Score SCORE_3 = new ScoreBuilder().withLabel("ST2334 Final Exam")
        .withValue("0")
        .withDate("2023-03-09").build();

    // Manually added - Score's details found in {@code CommandTestUtil}
    public static final Score SCORE_4 = new ScoreBuilder().withLabel(VALID_SCORE_LABEL)
        .withValue(VALID_SCORE_VALUE)
        .withDate(VALID_SCORE_DATE).build();

    private TypicalScores() {} // prevents instantiation
}
