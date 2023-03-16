package seedu.address.storage;

import java.util.AbstractMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.service.PartMap;

/**
 * Jackson-friendly version of an entry in {@link PartMap}.
 */
class JsonAdaptedPart {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Part's %s field is missing!";

    private final String name;
    private final int quantity;

    /**
     * Constructs a {@code JsonAdaptedPart} with the given part details,
     * Converts a given {@code Part} into this class for Jackson use.
     */
    @JsonCreator
    public JsonAdaptedPart(@JsonProperty("name") String name, @JsonProperty("quantity") int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Map.Entry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted part.
     */
    public Map.Entry<String, Integer> toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Name"));
        }

        if (quantity <= 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Quantity"));
        }

        return new AbstractMap.SimpleEntry<>(name, quantity);
    }


}
