package seedu.address.model.entity;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Character, which is a child class of Entity
 */
public class Character extends Entity {
    private Stats stats;
    private int level;
    // Represents the amount of experience points (xp) needed for the next level-up
    private int xp;

    /**
     * Every field should be present and non-null.
     * @param name name of the character
     * @param stats stats of the character (e.g. Strength, Dexterity)
     * @param level level of the character
     * @param xp experience points of the character
     * @param tags tags categorizing the character
     */
    public Character(Name name, Stats stats, int level, int xp, Set<Tag> tags) {
        super(name, tags);
        this.stats = stats;
        this.level = level;
        this.xp = xp;
    }

    public int getLevel() {
        return this.level;
    }

    public int getXP() {
        return this.xp;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(
                getName(),
                stats.getStrength(),
                stats.getDexterity(),
                stats.getIntelligence(),
                getLevel(),
                getXP(),
                getTags()
        );
    }

    @Override
    public String toString() {
        String characterDetails = String.format(
                "Name: %s | Str: %d | Dex: %d | Int: %d | Level: %d | XP: %d",
                getName(),
                stats.getStrength(),
                stats.getDexterity(),
                stats.getIntelligence(),
                getLevel(),
                getXP()
        );

        final StringBuilder builder = new StringBuilder(characterDetails);

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append(" | Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
