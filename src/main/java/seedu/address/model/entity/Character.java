package seedu.address.model.entity;

import java.util.List;
import java.util.Set;

import javafx.util.Pair;
import seedu.address.model.tag.Tag;

/**
 * Represents a Character, which is a child class of Entity
 */
public class Character extends Entity {

    private final Stats stats;
    private final Inventory inventory;
    private final int level;
    // Represents the amount of experience points (xp) needed for the next level-up
    private final int xp;

    /**
     * Every field should be present and non-null.
     *
     * @param name  name of the character
     * @param stats stats of the character (e.g. Strength, Dexterity)
     * @param level level of the character
     * @param xp    experience points of the character
     * @param tags  tags categorizing the character
     */
    public Character(Name name, Stats stats, int level, int xp, Set<Tag> tags) {
        super(name, tags);
        this.stats = stats;
        this.level = level;
        this.xp = xp;
        this.inventory = Inventory.emptyInventory();
    }

    /**
     * Every field should be present and non-null. Dummy values used for character specific fields.
     *
     * @param name name of the character
     * @param tags tags categorizing the character
     */
    public Character(Name name, Set<Tag> tags) {
        super(name, tags);
        this.stats = new Stats(10, 20, 30);
        this.level = 12;
        this.xp = 1;
        this.inventory = Inventory.emptyInventory();
    }

    /**
     * Constructor for character with inventory
     */
    public Character(Name name, Stats stats, int level, int xp, Set<Tag> tags, Inventory inventory) {
        super(name, tags);
        this.stats = stats;
        this.level = level;
        this.xp = xp;
        this.inventory = inventory;
    }

    public int getLevel() {
        return this.level;
    }

    public int getXP() {
        return this.xp;
    }

    public Stats getStats() {
        return this.stats;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public List<Pair<String, String>> getFields() {
        Set<Tag> tags = getTags();
        final StringBuilder serializedTags = new StringBuilder();
        tags.forEach(serializedTags::append);
        return List.of(
                new Pair<>("Str", String.valueOf(stats.getStrength())),
                new Pair<>("Dex", String.valueOf(stats.getDexterity())),
                new Pair<>("Int", String.valueOf(stats.getIntelligence())),
                new Pair<>("Level", String.valueOf(getLevel())),
                new Pair<>("XP", String.valueOf(getXP())),
                new Pair<>("Tags", serializedTags.toString())
        );
    }

    @Override
    public boolean isSameEntity(Entity otherEntity) {
        return otherEntity == this
                || (otherEntity instanceof Character
                && otherEntity.getName().equals(getName()));
    }
}
