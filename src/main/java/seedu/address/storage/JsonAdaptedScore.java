package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.score.Date;
import seedu.address.model.score.Label;
import seedu.address.model.score.Score;
import seedu.address.model.score.ScoreValue;

/**
 * Jackson-friendly version of {@link Score}.
 */
class JsonAdaptedScore {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String label;
    private final String value;
    private final String date;

    /**
     * Constructs a {@code JsonAdaptedScore} with the given score details.
     */
    @JsonCreator
    public JsonAdaptedScore(@JsonProperty("label") String label, @JsonProperty("value") String value,
                            @JsonProperty("date") String date) {
        this.label = label;
        this.value = value;
        this.date = date;
    }

    /**
     * Converts a given {@code Score} into this class for Jackson use.
     */
    public JsonAdaptedScore(Score source) {
        label = source.getLabel().label;
        value = String.valueOf(source.getValue().value);
        date = String.valueOf(source.getDate().date);
    }

    /**
     * Converts this Jackson-friendly adapted score object into the model's {@code Score} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted score.
     */
    public Score toModelType() throws IllegalValueException {

        if (label == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Label.class.getSimpleName()));
        }
        if (!Label.isValidLabel(label)) {
            throw new IllegalValueException(Label.MESSAGE_CONSTRAINTS);
        }
        final Label modelLabel = new Label(label);

        if (value == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ScoreValue.class.getSimpleName()));
        }
        if (!ScoreValue.isValidScoreValue(value)) {
            throw new IllegalValueException(ScoreValue.MESSAGE_CONSTRAINTS);
        }
        final ScoreValue modelScoreValue = new ScoreValue(value);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        return new Score(modelLabel, modelScoreValue, modelDate);
    }
}
