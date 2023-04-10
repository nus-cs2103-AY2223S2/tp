package seedu.address.model.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.util.Pair;
import seedu.address.model.tag.Tag;

/**
 * Represents an item, which is a child class of Entity
 */
public class Item extends Entity {

    public static final String ITEM_COMMAND_TERM = "item";
    private final Cost cost;
    private final Weight weight;

    /**
     * Constructs an item using a given ItemBuilder
     * @param builder given ItemBuilder
     */
    public Item(ItemBuilder builder) {
        super(builder.name, builder.tags);
        this.cost = builder.cost;
        this.weight = builder.weight;
    }

    public Cost getCost() {
        return this.cost;
    }

    public Weight getWeight() {
        return this.weight;
    }

    @Override
    public List<Pair<String, String>> getFields() {
        Set<Tag> tags = getTags();
        final StringBuilder serializedTags = new StringBuilder();
        tags.forEach(serializedTags::append);
        return List.of(
                new Pair<>("Cost", getCost().toString()),
                new Pair<>("Weight", getWeight().toString()),
                new Pair<>("Tags", serializedTags.toString())
        );
    }

    @Override
    public boolean isSameEntity(Entity otherEntity) {
        return otherEntity == this
                || (otherEntity instanceof Item
                && otherEntity.getName().equals(getName()));
    }

    /**
     * Builder class for Item
     */
    public static class ItemBuilder {
        private final Name name;
        private Cost cost = new Cost();
        private Weight weight = new Weight();
        private Set<Tag> tags = new HashSet<>();

        public ItemBuilder(Name name) {
            this.name = name;
        }

        /**
         * Set tags of the ItemBuilder instance
         * @param tags given tags to set
         * @return this
         */
        public ItemBuilder setTags(Set<Tag> tags) {
            this.tags = tags;
            return this;
        }


        /**
         * Set cost of the ItemBuilder instance
         * @param cost given cost to set
         * @return this
         */
        public ItemBuilder setCost(Cost cost) {
            this.cost = cost;
            return this;
        }

        /**
         * Set weight of the ItemBuilder instance
         * @param weight given weight to set
         * @return this
         */
        public ItemBuilder setWeight(Weight weight) {
            this.weight = weight;
            return this;
        }

        /**
         * Create a copy of a given CharacterBuilder
         * @param toCopy given CharacterBuilder to copy
         */
        public void copy(ItemBuilder toCopy) {
            this.weight = toCopy.weight;
            this.cost = toCopy.cost;
            this.tags = toCopy.tags;
        }

        public Item build() {
            return new Item(this);
        }
    }
}
