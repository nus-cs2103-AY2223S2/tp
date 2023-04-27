package seedu.wife.model.food;

import static java.util.Objects.requireNonNull;
import static seedu.wife.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.wife.model.tag.Tag;

/**
 * Represents Food stored in Well Informed Fridge Environment.
 * Guarantees: details are present and not null, field values are validated; immutable.
 */
public class Food implements Comparable<Food> {
    private Name foodName;
    private Unit unit;
    private Quantity quantity;
    private ExpiryDate expiryDate;
    private Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a {@code Food}
     * All field must be not null.
     */
    public Food(Name foodName, Unit unit, Quantity quantity, ExpiryDate expiryDate, Set<Tag> tags) {
        requireAllNonNull(foodName, unit, quantity, expiryDate, tags);
        this.foodName = foodName;
        this.unit = unit;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return this.foodName;
    }

    public Unit getUnit() {
        return this.unit;
    }

    public Quantity getQuantity() {
        return this.quantity;
    }

    public ExpiryDate getExpiryDate() {
        return this.expiryDate;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * **may need to be mutable in future implementation
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(this.tags);
    }

    /**
     * Removes the specified element from tags if it is present (optional operation).
     */
    public boolean removeTag(Tag tag) {
        return this.tags.remove(tag);
    }

    /**
     * Returns a new tag set for edit, which throws {@code UnsupportedOperationException}
     */
    public Set<Tag> getCurrentTags() {
        return new HashSet<Tag>(this.tags);
    }

    /**
     * Creates a new food item with an updated tag list.
     */
    public Food createNewFoodWithNewTags(Food food, Set<Tag> tags) {
        requireNonNull(tags);
        return new Food(food.getName(), food.getUnit(), food.getQuantity(), food.getExpiryDate(), tags);
    }

    /**
     * Returns True if two food items are the same.
     * This uses a weaker equivalence relation as it checks only if two food items have the same name.
     */
    public boolean isSameFood(Food otherFood) {
        if (otherFood == this) {
            return true;
        }
        return otherFood != null
                && foodName.equals(otherFood.getName())
                && expiryDate.equals(otherFood.getExpiryDate());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Food)) {
            return false;
        }

        Food otherFood = (Food) other;

        boolean equalsName = otherFood.getName().equals(getName());
        boolean equalsUnit = otherFood.getUnit().equals((getUnit()));
        boolean equalsQuantity = otherFood.getQuantity().equals((getQuantity()));
        boolean equalsExpiryDate = otherFood.getExpiryDate().equals(getExpiryDate());
        boolean equalsTags = otherFood.getTags().equals(getTags());

        return equalsName && equalsUnit && equalsQuantity && equalsExpiryDate && equalsTags;
    }

    @Override
    public String toString() {
        return String.format("%s (expires on: %s)", foodName, expiryDate);
    }

    @Override
    public int compareTo(Food food) {
        return this.expiryDate.compareTo(food.expiryDate);
    }
}
