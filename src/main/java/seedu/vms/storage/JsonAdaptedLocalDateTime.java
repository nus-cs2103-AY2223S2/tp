package seedu.vms.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * A JSON friendly representation of {@link LocalDateTime}.
 */
public class JsonAdaptedLocalDateTime {
    public final LocalDateTime value;


    /**
     * Constructs a {@code JsonAdaptedLocalDateTime}.
     */
    @JsonCreator
    public JsonAdaptedLocalDateTime(LocalDateTime value) {
        this.value = value;
    }


    /**
     * Converts an {@code LocalDateTime} to a {@code JsonAdaptedLocalDateTime}.
     *
     * @param time - the LocalDateTime to convert.
     * @return the {@code JsonAdaptedLocalDateTime} representation of the specified
     *      {@code LocalDateTime}.
     */
    public static JsonAdaptedLocalDateTime fromModelType(LocalDateTime time) {
        return new JsonAdaptedLocalDateTime(time);
    }


    @JsonValue
    public LocalDateTime getValue() {
        return value;
    }


    /**
     * Returns the {@code LocalDateTime} that this
     *      {@code JsonAdaptedLocalDateTime} represents.
     */
    public LocalDateTime toModelType() {
        return value;
    }
}
