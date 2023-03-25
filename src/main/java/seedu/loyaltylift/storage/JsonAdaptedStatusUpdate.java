package seedu.loyaltylift.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.loyaltylift.model.order.StatusUpdate;
import seedu.loyaltylift.model.order.StatusValue;

import java.time.LocalDate;

public class JsonAdaptedStatusUpdate {

    private final String statusValue;
    private final String date;

    /**
     * Constructs a {@code JsonAdaptedStatusUpdate} with the given {@code statusValue} and
     * {@code date}.
     */
    @JsonCreator
    public JsonAdaptedStatusUpdate(@JsonProperty("statusValue") String statusValue,
                                   @JsonProperty("date") String date) {
        this.statusValue = statusValue;
        this.date = date;
    }

    /**
     * Converts a given {@code StatusUpdate} into this class for Jackson use.
     */
    public JsonAdaptedStatusUpdate(StatusUpdate source) {
        statusValue = source.getStatusValue().toString();
        date = source.getDate().format(StatusUpdate.DATE_FORMATTER);
    }

    /**
     * Converts this Jackson-friendly adapted status update object into the model's {@code StatusUpdate} object.
     */
    public StatusUpdate toModelType() {
        StatusValue modelStatusValue = StatusValue.fromString(statusValue);
        LocalDate modelDate = LocalDate.parse(date, StatusUpdate.DATE_FORMATTER);

        return new StatusUpdate(modelStatusValue, modelDate);
    }
}
