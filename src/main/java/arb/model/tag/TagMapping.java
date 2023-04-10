package arb.model.tag;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Stores how many clients and projects is tagged by a certain {@code Tag}.
 * Guarantees: {@code Tag} tags at least one client or project in the address book.
 */
public class TagMapping {
    private final Tag tag;
    private int numberOfClientsTagged;
    private int numberOfProjectsTagged;

    /**
     * Constructs a {@code TagMapping}.
     *
     * @param tag A tag to store the mappings of.
     */
    public TagMapping(Tag tag) {
        requireNonNull(tag);
        this.tag = tag;
        this.numberOfClientsTagged = 0;
        this.numberOfProjectsTagged = 0;
    }

    /**
     * Increases the number of clients tagged by the {@code Tag}.
     */
    public void tagClient() {
        this.numberOfClientsTagged += 1;
    }

    /**
     * Increases the number of projects tagged by the {@code Tag}.
     */
    public void tagProject() {
        this.numberOfProjectsTagged += 1;
    }

    /**
     * Decreases the number of clients tagged by the {@code Tag}.
     */
    public void untagClient() {
        this.numberOfClientsTagged -= 1;
        assert this.numberOfClientsTagged >= 0 : "Number of client tags are negative";
    }

    /**
     * Decreases the number of projects tagged by the {@code Tag}.
     */
    public void untagProject() {
        this.numberOfProjectsTagged -= 1;
        assert this.numberOfProjectsTagged >= 0 : "Number of project tags are negative";
    }

    /**
     * Resets the number of clients tagged by the {@code Tag} to 0.
     */
    public void resetClientTaggings() {
        this.numberOfClientsTagged = 0;
    }

    /**
     * Resets the number of projects tagged by the {@code Tag} to 0.
     */
    public void resetProjectTaggings() {
        this.numberOfProjectsTagged = 0;
    }

    /**
     * Returns true if {@code otherTagMapping} wraps around the same
     * {@Tag}.
     */
    public boolean isSameTagMapping(TagMapping otherTagMapping) {
        return this.tag.equals(otherTagMapping.tag);
    }

    /**
     * Returns true if {@code otherTag} is the same tag as the
     * wrapped around {@code Tag}.
     */
    public boolean isSameTagMapping(Tag otherTag) {
        return this.tag.equals(otherTag);
    }

    /**
     * Returns true if {@code Tag} is not tagging any client or project.
     */
    public boolean noObjectsTagged() {
        return this.numberOfClientsTagged == 0
                && this.numberOfProjectsTagged == 0;
    }

    public int getNumberOfClientsTagged() {
        return this.numberOfClientsTagged;
    }

    public int getNumberOfProjectsTagged() {
        return this.numberOfProjectsTagged;
    }

    public Tag getTag() {
        return this.tag;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        return (otherObject instanceof TagMapping)
                && ((TagMapping) otherObject).tag.equals(tag)
                && ((TagMapping) otherObject).numberOfClientsTagged == numberOfClientsTagged
                && ((TagMapping) otherObject).numberOfProjectsTagged == numberOfProjectsTagged;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, numberOfClientsTagged, numberOfProjectsTagged);
    }
}
