package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.CsvUtil;
import seedu.address.model.person.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class CsvAdaptedTag {

    private final String tagName;

    /**
     * Constructs a {@code CsvAdaptedTag} with the given {@code tagName}.
     */

    public CsvAdaptedTag(String tagName) {
        this.tagName = CsvUtil.toCsvField(tagName);
    }

    /**
     * Converts a given {@code Tag} into this class for CSV use.
     */
    public CsvAdaptedTag(Tag source) {
        tagName = source.tagName;
    }

    public String getTagName() {
        return tagName;
    }

    /**
     * Converts this CSV-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Tag toModelType() throws IllegalValueException {
        if (!Tag.isValidTagName(tagName)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(tagName);
    }

    @Override
    public String toString() {
        return getTagName();
    }

}
