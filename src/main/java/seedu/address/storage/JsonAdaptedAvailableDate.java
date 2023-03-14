package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.information.AvailableDate;

public class JsonAdaptedAvailableDate {
    private final String startDate;
    private final String endDate;

    @JsonCreator
    public JsonAdaptedAvailableDate(String dateRange) {
        String[] dates = dateRange.split(" to ");
        this.startDate = dates[0];
        this.endDate = dates[1];
    }

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
