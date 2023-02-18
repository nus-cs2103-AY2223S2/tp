package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
abstract class JsonAdaptedTag {
    abstract Tag toModelType() throws IllegalValueException;
}
