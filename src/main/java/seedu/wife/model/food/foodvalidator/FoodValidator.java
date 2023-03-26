package seedu.wife.model.food.foodvalidator;

/**
 * Interface to validate string parsed to create food.
 */
public interface FoodValidator {
    /**
     * Validates the string input.
     * @param string String input by the user.
     */
    static void validate(String string) {};
}
