package seedu.address.testutil;

import seedu.address.model.score.Date;
import seedu.address.model.score.Label;
import seedu.address.model.score.Score;
import seedu.address.model.score.ScoreValue;

/**
 * A utility class to help with building Score objects.
 */
public class ScoreBuilder {
    public static final String DEFAULT_LABEL = "Final Assessment 2022";
    public static final String DEFAULT_SCORE_VALUE = "80";
    public static final String DEFAULT_DATE = "2023-03-09";

    private Label label;
    private ScoreValue value;
    private Date date;


    /**
     * Creates a {@code ScoreBuilder} with the default details.
     */
    public ScoreBuilder() {
        label = new Label(DEFAULT_LABEL);
        value = new ScoreValue(DEFAULT_SCORE_VALUE);
        date = new Date(DEFAULT_DATE);
    }

    /**
     * Initializes the ScoreBuilder with the data of {@code scoreToCopy}.
     */
    public ScoreBuilder(Score scoreToCopy) {
        label = scoreToCopy.getLabel();
        value = scoreToCopy.getValue();
        date = scoreToCopy.getDate();
    }

    /**
     * Sets the {@code Label} of the {@code Score} that we are building.
     */
    public ScoreBuilder withLabel(String label) {
        this.label = new Label(label);
        return this;
    }

    /**
     * Sets the {@code ScoreValue} of the {@code Score} that we are building.
     */
    public ScoreBuilder withValue(String score) {
        this.value = new ScoreValue(score);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Score} that we are building.
     */
    public ScoreBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }


    public Score build() {
        return new Score(label, value, date);
    }
}
