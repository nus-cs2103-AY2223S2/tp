package seedu.address.model.entity;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * A template class to create a new Character.
 */
public class Template extends Character {
    /**
     * Minimal constructor with just name and stats
     * @param name name of template
     * @param stats stats of template
     */
    public Template(Name name, Stats stats) {
        super(name, stats);
    }

    /**
     * Full constructor for template with all fields inputted
     */
    public Template(Name name, Stats stats, int level, int xp, Inventory inventory, Set<Tag> tags) {
        super(name, stats, level, xp, inventory, tags);
    }

    /**
     * Primitive version of generating character from template.
     */
    public Character generateCharacter(Name characterName) {
        requireNonNull(characterName);
        return new Character(characterName, getStats(), getLevel(), getXP(), getInventory(), getTags());
    }

    /**
     * Returns true if other template has the same name.
     */
    public boolean isSameTemplate(Template other) {
        return other == this
                || other.getName() == this.getName();
    }

}
