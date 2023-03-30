package seedu.address.model.entity;

import java.util.List;
import java.util.Set;

import javafx.util.Pair;
import seedu.address.model.tag.Tag;

/**
 * Represents a mob, which is a child class of Entity
 */
public class Mob extends Entity {

    private final Stats stats;
    // A higher challenge rating (CR) represents an increased difficulty to defeat a mob
    private final float challengeRating;
    private final boolean isLegendary;
    private final Inventory inventory;

    /**
     * Every field should be present and non-null.
     *
     * @param name            name of the mob
     * @param stats           stats of the mob (e.g. Strength, Dexterity)
     * @param challengeRating challenge rating of the mob
     * @param isLegendary     legendary status of the mob
     * @param tags            tags categorizing the mob
     */
    public Mob(Name name, Stats stats, float challengeRating, boolean isLegendary, Set<Tag> tags) {
        super(name, tags);
        this.stats = stats;
        this.challengeRating = challengeRating;
        this.isLegendary = isLegendary;
        this.inventory = Inventory.emptyInventory();
    }

    /**
     * Every field should be present and non-null. Dummy values used for mob-specific fields.
     *
     * @param name name of the mob
     */
    public Mob(Name name) {
        super(name);
        this.stats = new Stats(22, 11, 33);
        this.challengeRating = 2;
        this.isLegendary = false;
        this.inventory = Inventory.emptyInventory();
    }

    /**
     * Every field should be present and non-null. Dummy values used for mob-specific fields.
     *
     * @param name name of the mob
     */
    public Mob(Name name, Set<Tag> tags) {
        super(name, tags);
        this.stats = new Stats(22, 11, 33);
        this.challengeRating = 2;
        this.isLegendary = false;
        this.inventory = Inventory.emptyInventory();
    }

    /**
     * Constructor for Mobs with inventory
     */

    public Mob(Name name, Stats stats, float challengeRating, boolean isLegendary, Inventory inventory, Set<Tag> tags) {
        super(name, tags);
        this.stats = stats;
        this.challengeRating = challengeRating;
        this.isLegendary = isLegendary;
        this.inventory = inventory;
    }


    public float getChallengeRating() {
        return this.challengeRating;
    }

    public boolean getLegendaryStatus() {
        return this.isLegendary;
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
                new Pair<>("CR", String.valueOf(getChallengeRating())),
                new Pair<>("Legendary", String.valueOf(getLegendaryStatus())),
                new Pair<>("Tags", serializedTags.toString())
        );
    }

    @Override
    public boolean isSameEntity(Entity otherEntity) {
        return otherEntity == this
                || (otherEntity instanceof Mob
                && otherEntity.getName().equals(getName()));
    }
}
