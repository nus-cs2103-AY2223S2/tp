package seedu.address.model.tutee.fields;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;


/**
 * Represents a Tutee's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSubject(String)}
 */
public class Subject {


    public static final String[] SUBJECTS_OFFERED = {"Math", "Physics", "English"};
    public static final String MESSAGE_CONSTRAINTS =
            "Subjects should only be: " + subjectsBuilder();
    public static final List<String> SUBJECTS_OFFERED_LIST = Arrays.asList(SUBJECTS_OFFERED);

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String subject;

    /**
     * Constructs a {@code Subject}.
     *
     * @param subject A valid subject.
     */
    public Subject(String subject) {
        requireNonNull(subject);
        checkArgument(isValidSubject(subject), MESSAGE_CONSTRAINTS);
        this.subject = subject;
    }

    /**
     * Helper method for creating the MESSAGE_CONSTRAINTS message
     */
    private static String subjectsBuilder() {
        final StringBuilder builder = new StringBuilder();
        for (String subject: SUBJECTS_OFFERED) {
            builder.append(subject).append(", ");
        }
        return builder.toString();
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidSubject(String test) {
        return test.matches(VALIDATION_REGEX)
                && SUBJECTS_OFFERED_LIST.contains(test);
    }


    @Override
    public String toString() {
        return subject;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Subject // instanceof handles nulls
                && subject.equals(((Subject) other).subject)); // state check
    }

    @Override
    public int hashCode() {
        return subject.hashCode();
    }

}
