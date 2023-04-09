package seedu.address.testutil;

import static seedu.address.model.entity.Character.CharacterBuilder;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.entity.Character;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Item;
import seedu.address.model.entity.Mob;
import seedu.address.model.entity.Name;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Entity objects.
 */
public class EntityBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";

    private Name name;

    private Set<Tag> tags;

    /**
     * Creates a {@code EntityBuilder} with the default details.
     */
    public EntityBuilder() {
        name = new Name(DEFAULT_NAME);
        tags = new HashSet<>();
    }

    /**
     * Initializes the EntityBuilder with the data of {@code entityToCopy}.
     */
    public EntityBuilder(Entity entityToCopy) {
        name = entityToCopy.getName();
        tags = new HashSet<>(entityToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Entity} that we are building.
     */
    public EntityBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    public Character buildChar() {
        return new CharacterBuilder(name).build();
    }

    public Item buildItem() {
        return new Item(name);
    }

    public Mob buildMob() {
        return new Mob(name);
    }

}
