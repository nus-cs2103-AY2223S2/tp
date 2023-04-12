package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.PolicyTag;

/**
 * Jackson-friendly version of {@link PolicyTag}.
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

    /**
     * Converts a given {@code PolicyTag} into this class for Jackson use.
     */
    public JsonAdaptedTag(PolicyTag source) {
        tagName = source.tagName;
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code PolicyTag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public PolicyTag toModelType() throws IllegalValueException {
        if (!PolicyTag.isValidTagName(tagName)) {
            throw new IllegalValueException(PolicyTag.MESSAGE_CONSTRAINTS);
        }
        return new PolicyTag(tagName);
    }

}
