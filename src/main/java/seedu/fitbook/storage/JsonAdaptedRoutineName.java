package seedu.fitbook.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.fitbook.commons.exceptions.IllegalValueException;
import seedu.fitbook.model.routines.RoutineName;

/**
 * Jackson-friendly version of {@link RoutineName}.
 */
class JsonAdaptedRoutineName {

    private final String routineName;

    /**
     * Constructs a {@code JsonAdaptedRoutineName} with the given {@code routineName}.
     */
    @JsonCreator
    public JsonAdaptedRoutineName(String routineName) {
        this.routineName = routineName;
    }

    /**
     * Converts a given {@code RoutineName} into this class for Jackson use.
     */
    public JsonAdaptedRoutineName(RoutineName source) {
        routineName = source.routineName;
    }

    @JsonValue
    public String getRoutineName() {
        return routineName;
    }

    /**
     * Converts this Jackson-friendly adapted routine name object into the model's {@code RoutineName} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted routine name.
     */
    public RoutineName toFitBookModelType() throws IllegalValueException {
        if (!RoutineName.isValidRoutineName(routineName)) {
            throw new IllegalValueException(RoutineName.MESSAGE_CONSTRAINTS);
        }
        return new RoutineName(routineName);
    }

}
