package seedu.wife.testutil;

import static seedu.wife.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_NAME_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_QUANTITY_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_UNIT_MEIJI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.wife.model.food.Food;

/**
 * A utility class containing a list of {@code Food} objects to be used in tests.
 */
public class TypicalFood {
    public static final Food CHOCOLATE = new FoodBuilder()
            .withName("Breyls")
            .withUnit("Bar")
            .withQuantity("3")
            .withExpiryDate("13-11-2024")
            .build();

    public static final Food MEIJI = new FoodBuilder()
            .withName(VALID_NAME_MEIJI)
            .withUnit(VALID_UNIT_MEIJI)
            .withQuantity(VALID_QUANTITY_MEIJI)
            .withExpiryDate(VALID_EXPIRY_DATE_MEIJI)
            .build();

    public static final Food CHOCOLATE_WITHOUT_TAG = new FoodBuilder()
            .withName("Breyls")
            .withUnit("Bar")
            .withQuantity("3")
            .withExpiryDate("13-11-2024")
            .build();

    public static final Food MEIJI_WITHOUT_TAG = new FoodBuilder()
            .withName(VALID_NAME_MEIJI)
            .withUnit(VALID_UNIT_MEIJI)
            .withQuantity(VALID_QUANTITY_MEIJI)
            .withExpiryDate(VALID_EXPIRY_DATE_MEIJI)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meiji"; // A keyword that matches MEIER

    private TypicalFood() {} // prevents instantiation

    public static List<Food> getTypicalFood() {
        return new ArrayList<>(Arrays.asList(MEIJI, CHOCOLATE));
    }

    public static List<Food> getTypicalFoodWithoutTag() {
        return new ArrayList<>(Arrays.asList(MEIJI_WITHOUT_TAG, CHOCOLATE_WITHOUT_TAG));
    }

    public static List<Food> getTypicalFoodCombination() {
        return new ArrayList<>(Arrays.asList(MEIJI, CHOCOLATE_WITHOUT_TAG));
    }
}
