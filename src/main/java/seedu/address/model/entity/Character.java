package seedu.address.model.entity;

import java.util.HashSet;
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
    private final Progression progression;

    /**
     * Create a new character using a builder pattern
     * @param builder Builder to use
     */
    public Character(CharacterBuilder builder) {
        super(builder.name, builder.tags);
        this.stats = builder.stats;
        this.inventory = builder.inventory;
        this.progression = builder.progression;
    }

    public int getLevel() {
        return this.progression.getLevel();
    }

    public int getXP() {
        return this.progression.getXP();
    }

    public Stats getStats() {
        return this.stats;
    }

    public Progression getProgression() {
        return this.progression;
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
                new Pair<>("Level", String.valueOf(progression.getLevel())),
                new Pair<>("XP", String.valueOf(progression.getXP())),
                new Pair<>("Tags", serializedTags.toString())
        );
    }

    @Override
    public boolean isSameEntity(Entity otherEntity) {
        return otherEntity == this
                || (otherEntity instanceof Character
                && otherEntity.getName().equals(getName()));
    }

    /**
     * Builder class for Character
     */
    public static class CharacterBuilder {
        private final Name name;
        private Stats stats = new Stats();
        private Inventory inventory = Inventory.emptyInventory();
        private Progression progression = new Progression();
        private Set<Tag> tags = new HashSet<>();

        public CharacterBuilder(Name name) {
            this.name = name;
        }

        /**
         * Set tags of the CharacterBuilder instance
         * @param tags given tags to set
         * @return this
         */
        public CharacterBuilder setTags(Set<Tag> tags) {
            this.tags = tags;
            return this;
        }

        /**
         * Set stats of the CharacterBuilder instance
         * @param stats given tags to set
         * @return this
         */
        public CharacterBuilder setStats(Stats stats) {
            this.stats = stats;
            return this;
        }

        /**
         * Set inventory of the CharacterBuilder instance
         * @param inventory given tags to set
         * @return this
         */
        public CharacterBuilder setInventory(Inventory inventory) {
            this.inventory = inventory;
            return this;
        }

        /**
         * Set progression of the CharacterBuilder instance
         * @param progression given tags to set
         * @return this
         */
        public CharacterBuilder setProgression(Progression progression) {
            this.progression = progression;
            return this;
        }

        /**
         * Create a copy of a given CharacterBuilder
         * @param toCopy given CharacterBuilder to copy
         */
        public void copy(CharacterBuilder toCopy) {
            this.stats = toCopy.stats;
            this.inventory = toCopy.inventory;
            this.progression = toCopy.progression;
        }

        public Character build() {
            return new Character(this);
        }
    }
}
