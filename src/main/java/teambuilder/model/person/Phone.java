package teambuilder.model.person;

import static java.util.Objects.requireNonNull;
import static teambuilder.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {


    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}|^\\s*";
    private static final Phone NO_PHONE = new Phone();
    private final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    private Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    private Phone() {
        value = "";
    }

    public static Phone getEmptyPhone() {
        return NO_PHONE;
    }

    /**
     * Creates a phone instance if the phone number is not blank, otherwise return the unique "empty_phone" instance.
     * The method also ensures that the phone is of the correct formatting, i.e. if the phone is not blank it should
     * only contain numerical characters and have a length of at least 3.
     *
     * @param phone The phone number is given by the input string.
     * @return An instance of Phone, based on the input string.
     */
    public static Phone of(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        if (phone.length() == 0) {
            return getEmptyPhone();
        }
        return new Phone(phone);
    }

    public boolean isEmptyPhone() {
        return this == NO_PHONE;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && value.equals(((Phone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
