package seedu.address.model.entity;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Represents a mob, which is a child class of Entity
 */
public class Mob extends Entity {
    private final Stats stats;
    // A higher challenge rating (CR) represents an increased difficulty to defeat a mob
    private final int challengeRating;
    private final boolean isLegendary;

    /**
     * Every field should be present and non-null.
     * @param name name of the mob
     * @param stats stats of the mob (e.g. Strength, Dexterity)
     * @param challengeRating challenge rating of the mob
     * @param isLegendary legendary status of the mob
     * @param tags tags categorizing the mob
     */
    public Mob(Name name, Stats stats, int challengeRating, boolean isLegendary, Set<Tag> tags) {
        super(name, tags);
        this.stats = stats;
        this.challengeRating = challengeRating;
        this.isLegendary = isLegendary;
    }

    public int getChallengeRating() { return this.challengeRating; }

    public boolean getLegendaryStatus() { return this.isLegendary; }

    public Stats getStats() {
        return this.stats;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(
                getName(),
                stats.getStrength(),
                stats.getDexterity(),
                stats.getIntelligence(),
                getChallengeRating(),
                getLegendaryStatus(),
                getTags()
        );
    }

    @Override
    public String toString() {
        String characterDetails = String.format(
                "Name: %s | Str: %d | Dex: %d | Int: %d | CR: %d | Legendary: %b",
                getName(),
                stats.getStrength(),
                stats.getDexterity(),
                stats.getIntelligence(),
                getChallengeRating(),
                getLegendaryStatus()
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
