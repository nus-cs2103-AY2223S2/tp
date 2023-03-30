package seedu.wife.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.wife.model.food.ExpiryDate;
import seedu.wife.model.food.Food;
import seedu.wife.model.food.Name;
import seedu.wife.model.food.Quantity;
import seedu.wife.model.food.Unit;
import seedu.wife.model.tag.Tag;
import seedu.wife.model.util.SampleDataUtil;

/**
 * A utility class to help with building Food objects.
 */
public class FoodBuilder {
    public static final String DEFAULT_NAME = "Meiji";
    public static final String DEFAULT_UNIT = "Carton";
    public static final String DEFAULT_QUANTITY = "2";
    public static final String DEFAULT_EXPIRY_DATE = "13-11-2026";

    private Name name;
    private Unit unit;
    private Quantity quantity;
    private ExpiryDate expiryDate;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public FoodBuilder() {
        name = new Name(DEFAULT_NAME);
        unit = new Unit(DEFAULT_UNIT);
        quantity = new Quantity(DEFAULT_QUANTITY);
        expiryDate = new ExpiryDate(DEFAULT_EXPIRY_DATE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the FoodBuilder with the data of {@code foodToCopy}.
     */
    public FoodBuilder(Food foodToCopy) {
        name = foodToCopy.getName();
        unit = foodToCopy.getUnit();
        quantity = foodToCopy.getQuantity();
        expiryDate = foodToCopy.getExpiryDate();
        tags = new HashSet<>(foodToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Food} that we are building.
     */
    public FoodBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Food} that we are building.
     */
    public FoodBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Unit} of the {@code Food} that we are building.
     */
    public FoodBuilder withUnit(String unit) {
        this.unit = new Unit(unit);
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Food} that we are building.
     */
    public FoodBuilder withQuantity(String quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    /**
     * Sets the {@code ExpiryDate} of the {@code Food} that we are building.
     */
    public FoodBuilder withExpiryDate(String expiryDate) {
        this.expiryDate = new ExpiryDate(expiryDate);
        return this;
    }

    public Food build() {
        return new Food(name, unit, quantity, expiryDate, tags);
    }

}
