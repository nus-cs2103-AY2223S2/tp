package seedu.address.model.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Represents an Entity in the app.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Entity {
    // Identity fields
    private final Name name;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Entity(Name name, Set<Tag> tags) {
        this.name = name;
        this.tags.addAll(tags);
    }


    public Name getName() {
        return name;
    }
    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }



}
