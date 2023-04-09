package seedu.address.model.entity;

import java.util.HashSet;
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
    private final ChallengeRating challengeRating;
    private final Legend legend;
    private final Inventory inventory;

    /**
     * Create a mob using a builder pattern
     * @param builder given MobBuilder
     */
    public Mob(MobBuilder builder) {
        super(builder.name, builder.tags);
        this.stats = builder.stats;
        this.challengeRating = builder.challengeRating;
        this.legend = builder.legend;
        this.inventory = Inventory.emptyInventory();
    }

    public ChallengeRating getChallengeRating() {
        return this.challengeRating;
    }

    public Legend getLegend() {
        return this.legend;
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
                new Pair<>("Legendary", String.valueOf(getLegend())),
                new Pair<>("Tags", serializedTags.toString())
        );
    }

    @Override
    public boolean isSameEntity(Entity otherEntity) {
        return otherEntity == this
                || (otherEntity instanceof Mob
                && otherEntity.getName().equals(getName()));
    }

    /**
     * Builder class for Mob
     */
    public static class MobBuilder {
        private final Name name;
        private Stats stats = new Stats();
        private ChallengeRating challengeRating = new ChallengeRating();
        private Legend legend = new Legend();
        private Inventory inventory = Inventory.emptyInventory();
        private Set<Tag> tags = new HashSet<>();

        public MobBuilder(Name name) {
            this.name = name;
        }

        /**
         * Set tags of the MobBuilder instance
         * @param tags given tags to set
         * @return this
         */
        public MobBuilder setTags(Set<Tag> tags) {
            this.tags = tags;
            return this;
        }

        /**
         * Set stats of the MobBuilder instance
         * @param stats given stats to set
         * @return this
         */
        public MobBuilder setStats(Stats stats) {
            this.stats = stats;
            return this;
        }

        /**
         * Set inventory of the MobBuilder instance
         * @param inventory given inventory to set
         * @return this
         */
        public MobBuilder setInventory(Inventory inventory) {
            this.inventory = inventory;
            return this;
        }

        /**
         * Set challengeRating of the MobBuilder instance
         * @param challengeRating given tags to set
         * @return this
         */
        public MobBuilder setChallengeRating(ChallengeRating challengeRating) {
            this.challengeRating = challengeRating;
            return this;
        }

        /**
         * Set legend of the MobBuilder instance
         * @param legend given tags to set
         * @return this
         */
        public MobBuilder setLegend(Legend legend) {
            this.legend = legend;
            return this;
        }


        /**
         * Create a copy of a given MobBuilder
         * @param toCopy given MobBuilder to copy
         */
        public void copy(MobBuilder toCopy) {
            this.stats = toCopy.stats;
            this.inventory = toCopy.inventory;
            this.challengeRating = toCopy.challengeRating;
            this.legend = toCopy.legend;
        }

        public Mob build() {
            return new Mob(this);
        }
    }
}
