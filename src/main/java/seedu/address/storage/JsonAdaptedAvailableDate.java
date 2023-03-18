package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.information.AvailableDate;

/**
 * Jackson-friendly version of {@link AvailableDate}.
 */
public class JsonAdaptedAvailableDate {
    private final String startDate;
    private final String endDate;

    /**
     * Constructs a {@code JsonAdaptedAvailableDate} with the given {@code dateRange}.
     */
    @JsonCreator
    public JsonAdaptedAvailableDate(String dateRange) throws IllegalValueException {
        String[] dates = dateRange.split(" to ");

        if (dates.length < 2) {
            throw new IllegalValueException("Start or end date not specified");
        }

        this.startDate = dates[0];
        this.endDate = dates[1];
    }

    /**
     * Constructs a {@code JsonAdaptedAvailableDate} with the given {@code AvailableDate}.
     */
    public JsonAdaptedAvailableDate(AvailableDate src) {
        this.endDate = src.getEndDate().toString();
        this.startDate = src.getStartDate().toString();
    }

    @JsonValue
    public String getAvailableDate() {
        return startDate + " to " + endDate;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code AvailableDate} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted availableDate.
     */
    public AvailableDate toModelType() throws IllegalValueException {
        if (!AvailableDate.isValidDate(startDate, endDate)) {
            throw new IllegalValueException(AvailableDate.MESSAGE_CONSTRAINTS);
        }
        return new AvailableDate(startDate, endDate);
    }
}
