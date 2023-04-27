package seedu.wife.testutil;


import seedu.wife.model.Wife;
import seedu.wife.model.food.Food;

/**
 * A utility class to help with building Wife objects.
 * Example usage: <br>
 *     {@code Wife wife = new Wife().withFood("Meiji", "Chocolate").build();}
 */
public class WifeBuilder {

    private Wife wife;

    public WifeBuilder() {
        wife = new Wife();
    }

    public WifeBuilder(Wife wife) {
        this.wife = wife;
    }

    /**
     * Adds a new {@code FOod} to the {@code Wife} that we are building.
     */
    public WifeBuilder withFood(Food food) {
        wife.addFood(food);
        return this;
    }

    public Wife build() {
        return wife;
    }
}
