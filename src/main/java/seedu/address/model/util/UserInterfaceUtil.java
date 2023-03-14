package seedu.address.model.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class which contains functions for utility purposes, such as conversion of dates and prices
 * @author Nicholas Lee
 */
public class UserInterfaceUtil {

    public static String parseDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        return formatter.format(date);
    }


    public static String parsePrice(double amount) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String priceString = df.format(amount);
        return "$" + priceString;
    }

    public static String capitalizeFirstLetter(final String input) {
        return Character.toUpperCase(input.charAt(0)) + input.substring(1).toLowerCase();
    }
}


