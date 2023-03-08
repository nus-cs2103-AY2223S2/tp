package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTag {

    private final String tagName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTag(String tagName) {
        this.tagName = tagName;
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

}
