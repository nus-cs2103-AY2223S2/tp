package seedu.wife.testutil;

import seedu.wife.model.tag.Tag;
import seedu.wife.model.tag.TagName;

/**
 * A utility class to help with building Tag objects.
 */
public class TagBuilder {
    public static final String DEFAULT_NAME = "testtag";

    private TagName name;

    /**
     * Creates a {@code TagBuilder} with the default details.
     */
    public TagBuilder() {
        name = new TagName(DEFAULT_NAME);
    }

    /**
     * Initializes the TagBuilder with the data of {@code tagToCopy}.
     */
    public TagBuilder(Tag tagToCopy) {
        name = new TagName(tagToCopy.getTagName());
    }

    /**
     * Sets the {@code Name} of the {@link Tag} that we are building.
     */
    public TagBuilder withTagName(String name) {
        this.name = new TagName(name);
        return this;
    }

    public Tag build() {
        return new Tag(name.toString());
    }
}
