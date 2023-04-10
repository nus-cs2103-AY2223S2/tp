package teambuilder.model.person;

import static java.util.Objects.requireNonNull;
import static teambuilder.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's major study in the contact list.
 * Guarantees: immutable; is valid as declared in {@link #isValidMajor(String)}
 */
public class Major {

    public static final String MESSAGE_CONSTRAINTS =
            "Majors should only contain alphanumeric characters and spaces.";


    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*|^\\s*$";
    private static final Major EMPTY_MAJOR = new Major();
    private final String majorStudy;

    /**
     * Constructs a {@code Name}.
     *
     * @param major A valid major.
     */
    private Major(String major) {
        requireNonNull(major);
        checkArgument(isValidMajor(major), MESSAGE_CONSTRAINTS);
        majorStudy = major;
    }

    private Major() {
        majorStudy = "";
    }

    /**
     * Public static method to create major.
     * Returns default EMPTY_MAJOR if the input is blank or contains only whitespaces.
     *
     * @param major Major given by input.
     * @return      Respective major instance based on given input.
     */
    public static Major of(String major) {
        requireNonNull(major);
        checkArgument(isValidMajor(major), MESSAGE_CONSTRAINTS);
        if (major.length() == 0) {
            return getEmptyMajor();
        }
        return new Major(major);
    }

    /**
     * Returns the static final default EMPTY_MAJOR
     *
     * @return EMPTY_MAJOR.
     */
    public static Major getEmptyMajor() {
        return EMPTY_MAJOR;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidMajor(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if the major instance calling this method is the default EMPTY_MAJOR.
     *
     * @return  True if instance is the EMPTY_MAJOR, false otherwise.
     */
    public boolean isEmptyMajor() {
        return this == EMPTY_MAJOR;
    }


    @Override
    public String toString() {
        return majorStudy;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Major // instanceof handles nulls
                && majorStudy.equals(((Major) other).majorStudy)); // state check
    }

    @Override
    public int hashCode() {
        return majorStudy.hashCode();
    }

}
