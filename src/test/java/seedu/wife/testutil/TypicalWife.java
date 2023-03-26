package seedu.wife.testutil;

import seedu.wife.model.Wife;
import seedu.wife.model.food.Food;
import seedu.wife.model.tag.Tag;

/**
 * A utility class containing a list of {@code Wife} objects to be used in tests.
 */
public class TypicalWife {
    /**
     * Returns an {@code Wife} with all the typical food (tagged).
     */
    public static Wife getTypicalWife() {
        Wife wife = new Wife();
        for (Food food : TypicalFood.getTypicalFood()) {
            wife.addFood(food);
        }

        for (Tag tag : TypicalTag.getTypicalTag()) {
            wife.createTag(tag);
        }

        return wife;
    }

    /**
     * Returns an {@code Wife} with all the typical food (untagged).
     */
    public static Wife getTypicalWifeWithoutFoodTag() {
        Wife wife = new Wife();
        for (Food food : TypicalFood.getTypicalFoodWithoutTag()) {
            wife.addFood(food);
        }

        return wife;
    }
}
