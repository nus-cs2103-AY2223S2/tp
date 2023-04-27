package seedu.wife.model.food.foodvalidator;

import static seedu.wife.commons.util.AppUtil.checkArgument;
import static seedu.wife.model.food.ExpiryDate.VALID_DATE_FORMAT;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.ResolverStyle;

/**
 * Validator class to validate Expiry Date.
 */
public class ExpiryDateValidator implements FoodValidator {
    public static final String VALIDATION_REGEX = "[0-9][0-9]-[0-9][0-9]-[0-9][0-9][0-9][0-9]";
    public static final String MESSAGE_FORMAT_CONSTRAINTS = "Format of your date is incorrect. "
            + "Please insert using the format DD-MM-YYYY";
    public static final String MESSAGE_DATE_NOT_EXIST = "The date you entered does not exist.";
    public static final String MESSAGE_DATE_NOT_AFTER = "The date you entered should not be before today.";

    /**
     * Checks if the date format is valid.
     *
     * @param date Date entered by the user.
     * @return True if format is valid.
     */
    public static boolean isValidDateFormat(String date) {
        return date.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if the date is an actual calendar date.
     *
     * @param date Date entered by the user.
     * @return True if the date exists, else false.
     */
    public static boolean isDateExist(String date) {
        try {
            LocalDate.parse(date, VALID_DATE_FORMAT.withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeException de) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the date is on or after the date of insertion of the food item.
     *
     * @param date Date entered by the user.
     * @return True if the date is after the date of insertion, else false.
     */
    public static boolean isDateOnOrAfter(String date) {
        LocalDate expiryDate = LocalDate.parse(date, VALID_DATE_FORMAT);
        LocalDate dateNow = LocalDate.now();
        return !expiryDate.isBefore(dateNow);
    }

    /**
     * Validates the date before adding it into expiry date.
     *
     * @param date Date input by the user.
     */
    public static void validate(String date) {
        checkArgument(isValidDateFormat(date), MESSAGE_FORMAT_CONSTRAINTS);
        checkArgument(isDateExist(date), MESSAGE_DATE_NOT_EXIST);
        checkArgument(isDateOnOrAfter(date), MESSAGE_DATE_NOT_AFTER);
    }

    /**
     * Validates the expiry date before adding it into wife.
     *
     * @param date Expiry date obtained from storage.
     */
    public static void validateOnLoad(String date) {
        checkArgument(isValidDateFormat(date), MESSAGE_FORMAT_CONSTRAINTS);
        checkArgument(isDateExist(date), MESSAGE_DATE_NOT_EXIST);
    }
}
