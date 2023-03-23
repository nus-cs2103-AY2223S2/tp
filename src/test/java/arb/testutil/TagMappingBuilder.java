package arb.testutil;

import arb.model.tag.Tag;
import arb.model.tag.TagMapping;

/**
 * A utility class to help with building TagMapping objects.
 */
public class TagMappingBuilder {

    public static final String DEFAULT_TAG = "friend";

    private Tag tag;
    private int numberOfClientsTagged;
    private int numberOfProjectsTagged;

    /**
     * Creates a {@code TagMappingBuilder} with the default details.
     */
    public TagMappingBuilder() {
        tag = new Tag(DEFAULT_TAG);
        numberOfClientsTagged = 0;
        numberOfProjectsTagged = 0;
    }

    /**
     * Initializes the TagMappingBuilder with the data of {@code tagMappingToCopy}.
     */
    public TagMappingBuilder(TagMapping tagMappingToCopy) {
        tag = tagMappingToCopy.getTag();
        numberOfClientsTagged = tagMappingToCopy.getNumberOfClientsTagged();
        numberOfProjectsTagged = tagMappingToCopy.getNumberOfProjectsTagged();
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
    * Sets the number of clients the tag mapping being built is meant to tag.
    */
    public TagMappingBuilder withNumberOfClientsTagged(int numberOfClientsTagged) {
        assert numberOfClientsTagged >= 0;
        this.numberOfClientsTagged = numberOfClientsTagged;
        return this;
    }

    /**
    * Sets the number of projects the tag mapping being built is meant to tag.
    */
    public TagMappingBuilder withNumberOfProjectsTagged(int numberOfProjectsTagged) {
        assert numberOfProjectsTagged >= 0;
        this.numberOfProjectsTagged = numberOfProjectsTagged;
        return this;
    }

    /**
     * Builds the TagMapping.
     * @return The new TagMapping.
     */
    public TagMapping build() {
        TagMapping mapping = new TagMapping(this.tag);
        for (int i = 0; i < numberOfClientsTagged; i++) {
            mapping.tagClient();
        }
        for (int i = 0; i < numberOfProjectsTagged; i++) {
            mapping.tagProject();
        }
        return mapping;
    }

}
