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

    public static final int DEFAULT_COST = 0;
    public static final int DEFAULT_WEIGHT = 0;
    private final int cost;
    private final float weight;

    /**
     * Every field should be present and non-null.
     *
     * @param name   name of the character
     * @param cost   price of the item
     * @param weight weight of the item
     * @param tags   tags categorizing the item
     */
    public Item(Name name, int cost, float weight, Set<Tag> tags) {
        super(name, tags);
        this.cost = cost;
        this.weight = weight;
    }

    /**
     * Every field should be present and non-null.
     *
     * @param name name of the character
     * @param tags tags categorizing the item
     */
    public Item(Name name, Set<Tag> tags) {
        super(name, tags);
        cost = DEFAULT_COST;
        weight = DEFAULT_WEIGHT;
    }

    /**
     * Every field should be present and non-null.
     *
     * @param name name of the character
     */
    public Item(Name name) {
        super(name, new HashSet<>());
        cost = DEFAULT_COST;
        weight = DEFAULT_WEIGHT;
    }

    public int getCost() {
        return this.cost;
    }

    public float getWeight() {
        return this.weight;
    }

    @Override
    public List<Pair<String, String>> getFields() {
        Set<Tag> tags = getTags();
        final StringBuilder serializedTags = new StringBuilder();
        tags.forEach(serializedTags::append);
        return List.of(
                new Pair<>("Cost", String.valueOf(getCost()) + "g"),
                new Pair<>("Weight", String.valueOf(getWeight())),
                new Pair<>("Tags", serializedTags.toString())
        );
    }

    @Override
    public boolean isSameEntity(Entity otherEntity) {
        return otherEntity == this
                || (otherEntity instanceof Item
                && otherEntity.getName().equals(getName()));
    }
}
