package seedu.address.model.util;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Utility class which contains functions for utility purposes, such as conversion of dates and prices
 * @author Nicholas Lee
 */
public class UserInterfaceUtil {

    /**
     * Returns a string representation of the given date object in the format "dd/MM/yy".
     * @param date the date object to be formatted
     * @return a formatted date string
     */
    public static String parseDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        return date.format(formatter);
    }

    /**
     * Returns a string representation of the given double as a price string in the format "$xx.xx".
     * @param amount double value representing a price
     * @return a formatted price string representation
     */
    public static String parsePrice(double amount) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String priceString = df.format(amount);
        return "$" + priceString;
    }

    /**
     * Returns the input string with the first letter capitalized and the rest of the letters in lower case.
     * @param input the string to be capitalized
     * @return a string with the first letter capitalized
     */
    public static String capitalizeFirstLetter(final String input) {
        return Character.toUpperCase(input.charAt(0)) + input.substring(1).toLowerCase();
    }
}


