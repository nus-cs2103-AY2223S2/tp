package seedu.address.model.entity;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import javafx.util.Pair;
import seedu.address.model.tag.Tag;

/**
 * Represents an Entity in Reroll. Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public abstract class Entity {

    // Identity fields
    private final Name name;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Entity(Name name, Set<Tag> tags) {
        requireAllNonNull(name, tags);
        this.name = name;
        this.tags.addAll(tags);
    }

    /**
     * Initial declaration of entity
     *
     * @param name name of the entity
     */
    public Entity(Name name) {
        requireAllNonNull(name);
        this.name = name;
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
        List<Pair<String, String>> fields = getFields();
        List<String> details = fields
                .stream()
                .map(p -> p.getKey() + ": " + p.getValue())
                .collect(Collectors.toList());
        return StringUtils.join(details, " | ");
    }

    public abstract boolean isSameEntity(Entity otherEntity);
}


