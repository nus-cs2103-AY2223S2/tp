package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Represents a ModuleTag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class ModuleTag extends Tag implements Comparable<ModuleTag> {

    public static final String MESSAGE_CONSTRAINTS =
            "NUS Modules should have 2 - 3 letter prefix, followed by 4 digits and optional 1 - 3 alphabets";
    public static final String VALIDATION_REGEX = "[A-Z]{2,4}[0-9]{4}[A-Z]{0,3}";

    private final String day;
    private final String startTime;
    private final String endTime;

    /**
     * Constructs a {@code ModuleTag}.
     *
     * @param tagName A valid tag name.
     */
    public ModuleTag(String tagName) {
        super(tagName);
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.day = null;
        this.startTime = null;
        this.endTime = null;
    }

    /**
     * Overloaded constructor for a {@code ModuleTag}
     *
     * @param tagNames ArrayList of valid Tag command arguments.
     */
    public ModuleTag(ArrayList<String> tagNames) {
        super(tagNames.get(0));
        requireNonNull(tagNames);
        if (tagNames.size() == 4) {
            this.day = tagNames.get(1);
            this.startTime = tagNames.get(2);
            this.endTime = tagNames.get(3);
            return;
        }
        this.day = null;
        this.startTime = null;
        this.endTime = null;
    }
    @Override
    boolean isValidTagName(String test, String regex) {
        return Pattern.matches(VALIDATION_REGEX, test);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleTag // instanceof handles nulls
                && tagName.equals(((ModuleTag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return tagName;
    }

    @Override
    public int compareTo(ModuleTag otherModuleTag) {
        return tagName.compareTo(otherModuleTag.tagName);
    }

    public String getDayAsStr() {
        return day;
    }

    public String getStartTimeAsStr() {
        return startTime;
    }

    public String getEndTimeAsStr() {
        return endTime;
    }

    /**
     * Flags if this moduleTag is a tag for the basic functionality, with no lesson parameters tied to it.
     * @return boolean if moduleTag is a generated from a simple input with no extra parameters.
     */
    public boolean isBasicTag() {
        return this.day == null && this.startTime == null && this.endTime == null;
    }
}
