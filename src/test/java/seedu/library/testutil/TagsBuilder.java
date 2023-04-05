package seedu.library.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.library.model.Tags;
import seedu.library.model.tag.Tag;

/**
 * A utility class to help with building Tags objects.
 */
public class TagsBuilder {
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Builds a set of tags with 2 default values.
     */
    public TagsBuilder() {
        tags.add(new Tag("novel"));
        tags.add(new Tag("plant"));
    }

    /**
     * Adds a {@code tagName} of the {@code Tag} that we are building.
     */
    public TagsBuilder addTag(String tagName) {
        tags.add(new Tag(tagName));
        return this;
    }

    /**
     * Builds a default tag list.
     * @return Set of tag list.
     */
    public Set<Tag> build() {
        return tags;
    }

    public List<Tag> asList() {
        return new ArrayList<>(tags);
    }

    public Tags getTypicalTags() {
        Tags tags = new Tags();
        tags.setTags(asList());
        return tags;
    }
}
