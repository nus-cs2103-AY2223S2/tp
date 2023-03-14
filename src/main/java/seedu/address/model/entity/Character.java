package seedu.address.model.entity;

import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

import java.util.Objects;
import java.util.Set;

public class Character extends Entity {
    private int strength;
    private int dexterity;
    private int intelligence;
    private int level;
    private int xp;

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
        String CharacterDetails = String.format(
                "Name: %s | Str: %d | Dex: %d | Int: %d | Level: %d | XP: %d",
                getName(),
                getStrength(),
                getDexterity(),
                getIntelligence(),
                getLevel(),
                getXP()
        );

        final StringBuilder builder = new StringBuilder(CharacterDetails);

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append(" | Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
