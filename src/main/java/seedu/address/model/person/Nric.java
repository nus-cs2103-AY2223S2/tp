package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * To represents the patient's identity
 */
public class Nric {

    public static final String MESSAGE_CONSTRAINTS =
            "Person's Nric should start with 'S/T/G' and must have the length of 9 (E.g. T0000000A)";
    public final String number;

    /**
     * To create the Nric object
     * @param number nric number in string
     */
    public Nric(String number) {
        requireNonNull(number);
        checkArgument(isValidNumber(number), MESSAGE_CONSTRAINTS);
        this.number = number;
    }

    /**
     * @param number the NRIC number
     * @return boolean, true if is a valid NRIC number
     */
    public static boolean isValidNumber(String number) {
        char c = (char) number.indexOf(0);
        if (number.equals("")) {
            return true;
        } else if (number.charAt(0) == 'S' || number.charAt(0) == 'T' || number.charAt(0) == 'G') {
            if (number.length() == 9) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    public String getNumber() {
        return this.number;
    }
    @Override
    public String toString() {
        return "NRIC: " + number;
    }
}
