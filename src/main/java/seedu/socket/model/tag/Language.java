package seedu.socket.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.socket.commons.util.AppUtil.checkArgument;

/**
 * Represents a (Programming) Language in SOCket.
 * Guarantees: immutable; name is valid as declared in {@link #isValidLanguageName(String)}
 */
public class Language {
    public static final String MESSAGE_CONSTRAINTS = "Language names should be a minimum of 1 character, start with a "
            + "letter and consist of alphanumeric characters and/or -, +, #.";
    public static final String VALIDATION_REGEX = "^(?=[a-zA-Z])[-+#a-zA-Z0-9]+";

    public final String languageName;

    /**
     * Constructs a {@code Language}.
     *
     * @param languageName A valid language name.
     */
    public Language(String languageName) {
        requireNonNull(languageName);
        checkArgument(isValidLanguageName(languageName), MESSAGE_CONSTRAINTS);
        this.languageName = languageName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidLanguageName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Language // instanceof handles nulls
                && languageName.equals(((Language) other).languageName)); // state check
    }

    @Override
    public int hashCode() {
        return languageName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + languageName + ']';
    }

}
