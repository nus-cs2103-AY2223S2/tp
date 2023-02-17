package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.GroupTag;

/**
 * Jackson-friendly version of {@link GroupTag}.
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
     * Converts a given {@code GroupTag} into this class for Jackson use.
     */
    public JsonAdaptedTag(GroupTag source) {
        tagName = source.tagName;
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code GroupTag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public GroupTag toModelType() throws IllegalValueException {
        if (!GroupTag.isValidTagName(tagName)) {
            throw new IllegalValueException(GroupTag.MESSAGE_CONSTRAINTS);
        }
        return new GroupTag(tagName);
    }

}
