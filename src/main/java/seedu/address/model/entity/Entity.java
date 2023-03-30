package seedu.address.model.entity;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.util.Pair;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book. Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public abstract class Entity {

    // Identity fields
    private final Name name;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Initial declaration of entity
     * @param name name of the entity
     */
    public Entity(Name name) {
        requireNonNull(name);
        this.name = name;
    }

    /**
     * Every field must be present and not null.
     */
    public Entity(Name name, Set<Tag> tags) {
        requireAllNonNull(name, tags);
        this.name = name;
        this.tags.addAll(tags);
    }

    public abstract List<Pair<String, String>> getFields();

    public Name getName() {
        return name;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException} if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same identity and data fields. This defines a stronger notion of equality
     * between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Entity)) {
            return false;
        }

        Entity otherEntity = (Entity) other;
        if (other == this) {
            return true;
        }
        boolean isBothCharacter = (otherEntity instanceof Character) && (this instanceof Character);
        boolean isBothItem = (otherEntity instanceof Item) && (this instanceof Item);
        boolean isBothMob = (otherEntity instanceof Mob) && (this instanceof Mob);
        boolean hasSameName = otherEntity.getName().equals(this.getName());

        return (isBothCharacter || isBothItem || isBothMob) && hasSameName;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Arrays.hashCode(getFields().toArray());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        List<Pair<String, String>> fields = getFields();
        for (Pair<String, String> p : fields) {
            String field = String.format("%s: %s\n", p.getKey(), p.getValue());
            builder.append(field);
        }
        return builder.toString();
    }

    public abstract boolean isSameEntity(Entity otherEntity);
}
