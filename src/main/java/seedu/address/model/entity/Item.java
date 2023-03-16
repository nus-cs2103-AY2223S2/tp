package seedu.address.model.entity;

import java.util.Objects;
import java.util.Set;

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
     * @param name name of the character
     * @param cost price of the item
     * @param weight weight of the item
     * @param tags tags categorizing the item
     */
    public Item(Name name, int cost, float weight, Set<Tag> tags) {
        super(name, tags);
        this.cost = cost;
        this.weight = weight;
    }
    /**
     * Every field should be present and non-null.
     * @param name name of the character
     * @param tags tags categorizing the item
     */
    public Item(Name name, Set<Tag> tags) {
        super(name, tags);
        cost = DEFAULT_COST;
        weight = DEFAULT_WEIGHT;
    }

    public int getCost() { return this.cost; }

    public float getWeight() { return this.weight; }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(
                getName(),
                getCost(),
                getWeight(),
                getTags()
        );
    }

    @Override
    public String toString() {
        String characterDetails = String.format(
                "Name: %s | Cost: %d | Weight: %d | Attribute: %s",
                getName(),
                getCost(),
                getWeight()
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
