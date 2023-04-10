package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.GroupTag;

/**
 * Jackson-friendly version of {@link GroupTag}.
 */
public class JsonAdaptedGroupTag extends JsonAdaptedTag {
    private final String groupTagName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedGroupTag(String groupTagName) {
        this.groupTagName = groupTagName;
    }

    /**
     * Converts a given {@code GroupTag} into this class for Jackson use.
     */
    public JsonAdaptedGroupTag(GroupTag source) {
        groupTagName = source.tagName;
    }

    @JsonValue
    public String getTagName() {
        return groupTagName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code GroupTag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public GroupTag toModelType() throws IllegalValueException {
        if (groupTagName == null) {
            throw new IllegalValueException("Group Tag name missing!");
        }

        if (!GroupTag.isValidTagName(groupTagName)) {
            throw new IllegalValueException(GroupTag.MESSAGE_CONSTRAINTS);
        }
        return new GroupTag(groupTagName);
    }
}
