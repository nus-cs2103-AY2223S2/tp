package arb.testutil;

import arb.model.tag.Tag;
import arb.model.tag.TagMapping;

/**
 * A utility class to help with building TagMapping objects.
 */
public class TagMappingBuilder {

    public static final String DEFAULT_TAG = "friend";

    private Tag tag;

    /**
     * Creates a {@code TagMappingBuilder} with the default details.
     */
    public TagMappingBuilder() {
        tag = new Tag(DEFAULT_TAG);
    }

    /**
     * Initializes the TagMappingBuilder with the data of {@code tagMappingToCopy}.
     */
    public TagMappingBuilder(TagMapping tagMappingToCopy) {
        tag = tagMappingToCopy.getTag();
    }

    /**
     * Sets the {@code Tag} of the {@code TagMapping} being built.
     * @param tag Tag to set.
     * @return The TagMappingBuilder object.
     */
    public TagMappingBuilder withTag(String tag) {
        this.tag = new Tag(tag);
        return this;
    }

    /**
     * Builds the TagMapping.
     * @return The new TagMapping.
     */
    public TagMapping build() {
        return new TagMapping(this.tag);
    }

}
