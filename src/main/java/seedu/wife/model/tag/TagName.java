package seedu.wife.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.wife.commons.util.AppUtil.checkArgument;

import seedu.wife.commons.util.StringUtil;

/**
 * A class to store the name for every {@code Tag}
 */
public class TagName {
    public static final Integer TAG_NAME_MAX_LENGTH = 15;
    public static final String MESSAGE_CONSTRAINTS = "Your tag name violates the tag name convention. Please follow "
            + "the below formats: \n"
            + "1. Tag Name should not be empty\n"
            + "2. Tag name should only contain letters\n"
            + "3. Tag name should not be longer than 15 characters";
    public static final String NAME_VALIDATION_REGEX = "^[a-zA-Z\\s]*$";
    public static final String TAG_NAME_NOT_PRESENT = "Tag name should not be empty. "
            + "Please insert a name for your tag.";
    public static final String TAG_NAME_LENGTHY = "Tag name should have maximum 15 characters.";
    public static final String INVALID_TAG_NAME = "Tags names should contain only letters.";
    private final String tagName;

    /**
     * Constructor to create a {@code TagName} object with validation checks on the input string.
     * @param tagName
     */
    public TagName(String tagName) {
        requireNonNull(tagName);
        checkArgument(isNamePresent(tagName), TAG_NAME_NOT_PRESENT);
        checkArgument(isNameNotLengthy(tagName), TAG_NAME_LENGTHY);
        checkArgument(isValidString(tagName), INVALID_TAG_NAME);
        this.tagName = formatName(tagName);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidString(String test) {
        return test.matches(NAME_VALIDATION_REGEX);
    }

    /**
     * Returns true if the given name is not blank.
     */
    public static Boolean isNamePresent(String tagName) {
        return !tagName.isBlank();
    }

    /**
     * Returns true if the given name has length not more than 15.
     */
    public static Boolean isNameNotLengthy(String tagName) {
        return tagName.length() < TAG_NAME_MAX_LENGTH;
    }

    /**
     * Returns true is tag name satisfies the tag naming convention.
     */
    public static Boolean isValidTagName(String tagName) {
        return isNameNotLengthy(tagName) && isNamePresent(tagName) && isValidString(tagName);
    }

    /**
     * Returns formatted tag name with the first character of each word in tag name capitalized."
     */
    public static String formatName(String tagName) {
        return StringUtil.capitalizeString(tagName.toLowerCase());
    }

    @Override
    public String toString() {
        return this.tagName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TagName
                && tagName.equals(((TagName) other).toString()));
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }
}
