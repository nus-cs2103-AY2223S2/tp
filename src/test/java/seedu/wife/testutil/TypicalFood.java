package seedu.wife.testutil;

import static seedu.wife.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_NAME_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_QUANTITY_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_DAIRY;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_UNIT_MEIJI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.wife.model.Wife;
import seedu.wife.model.food.Food;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalFood {
    public static final Food CHOCOLATE = new FoodBuilder().withName("Breyls")
            .withUnit("Bar").withQuantity("3")
            .withExpiryDate("13-11-2024")
            .withTags("CHOCOLATE").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Food MEIJI = new FoodBuilder().withName(VALID_NAME_MEIJI).withUnit(VALID_UNIT_MEIJI)
            .withQuantity(VALID_QUANTITY_MEIJI).withExpiryDate(VALID_EXPIRY_DATE_MEIJI)
            .withTags(VALID_TAG_DAIRY).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meiji"; // A keyword that matches MEIER

    private TypicalFood() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static Wife getTypicalWife() {
        Wife wife = new Wife();
        for (Food food : getTypicalFood()) {
            wife.addFood(food);
        }
        return wife;
    }

    public static List<Food> getTypicalFood() {
        return new ArrayList<>(Arrays.asList(MEIJI, CHOCOLATE));
    }
}
