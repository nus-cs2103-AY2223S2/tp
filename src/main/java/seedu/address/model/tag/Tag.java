package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

/**
 * Represents an abstract version of Tag.
 */
public abstract class Tag {

    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        this.tagName = tagName;
    }

    abstract boolean isValidTagName(String test, String regex);
}
