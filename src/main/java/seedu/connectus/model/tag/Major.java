package seedu.connectus.model.tag;

import static seedu.connectus.logic.parser.CliSyntax.PREFIX_MAJOR;

/**
 * Represents a Major in the ConnectUS.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Major extends Tag {

    public static final int MAX_MAJOR_COUNT = 2;
    public static final String MESSAGE_CONSTRAINTS = "Major names should be alphanumeric and may contain spaces\n"
            + "Format: " + PREFIX_MAJOR + "MAJOR";
    public final String major;

    /**
     * Constructs a {@code Major}.
     *
     * @param major A valid Major.
     */
    public Major(String major) {
        super(major, MESSAGE_CONSTRAINTS);
        this.major = major;
    }

    /**
     * Returns true if a given string is a valid majorName.
     */
    public static boolean isValidMajorName(String test) {
        return isValidTagName(test);
    }
}
