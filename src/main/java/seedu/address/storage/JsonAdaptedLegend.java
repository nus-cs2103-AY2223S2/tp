package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.Legend;

/**
 * Jackson-friendly version of {@link Legend}
 */
public class JsonAdaptedLegend {

    private final boolean status;

    /**
     * Constructs a {@code JsonAdaptedLegend} with the given progression details.
     */
    @JsonCreator
    public JsonAdaptedLegend(@JsonProperty("status") boolean status) {
        this.status = status;
    }

    /**
     * Converts a given {@code Legend} into this class for Jackson use.
     */
    public JsonAdaptedLegend(Legend legend) {
        this.status = legend.getStatus();
    }

    /**
     * Converts this Jackson-friendly adapted Legend object into the model's {@code Legend} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Legend.
     */
    public Legend toModalType() throws IllegalValueException {
        return new Legend(status);
    }

}
