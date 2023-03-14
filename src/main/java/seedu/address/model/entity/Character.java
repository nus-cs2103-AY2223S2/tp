package seedu.address.model.entity;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Represents a Character, which is a child class of Entity
 */
public class Character extends Entity {
    private int strength;
    private int dexterity;
    private int intelligence;
    private int level;
    private int xp;

    /**
     * Every field should be present and non-null.
     * @param name name of the character
     * @param str strength of the character
     * @param dex dexterity of the character
     * @param intel intelligence of the character
     * @param level level of the character
     * @param xp experience points of the character
     * @param tags tags categorizing the character
     */
    public Character(Name name, int str, int dex, int intel, int level, int xp, Set<Tag> tags) {
        super(name, tags);
        this.strength = str;
        this.dexterity = dex;
        this.intelligence = intel;
        this.level = level;
        this.xp = xp;
    }

    public int getStrength() { return this.strength; }

    public int getDexterity() { return this.dexterity; }

    public int getIntelligence() { return this.intelligence; }

    public int getLevel() { return this.level; }

    public int getXP() { return this.xp; }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(
                getName(),
                getStrength(),
                getDexterity(),
                getIntelligence(),
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
                getStrength(),
                getDexterity(),
                getIntelligence(),
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
