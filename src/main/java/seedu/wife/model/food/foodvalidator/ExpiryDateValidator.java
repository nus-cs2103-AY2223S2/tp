package seedu.wife.model.food.foodvalidator;

public class ExpiryDateValidator implements FoodValidator {
    public static final String VALIDATION_REGEX = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";

    public static boolean isValidDateFormat(String date) {
        return date.matches(VALIDATION_REGEX);
    }
}
