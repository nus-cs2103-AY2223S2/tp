package seedu.library.model.bookmark;

import static java.util.Objects.requireNonNull;
import static seedu.library.commons.util.AppUtil.checkArgument;

/**
 * Represents a Bookmark's url in the library.
 */
public class Url {

    public static final String MESSAGE_CONSTRAINTS =
            "UrlLink should be in valid format [Protocol][Domain name] for example: [http://]www.[example.com] ";
    public static final String VALIDATION_REGEX =
            "^$|^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public final String value;

    /**
     * Constructs a {@code Url}.
     * @param url
     */
    public Url(String url) {
        requireNonNull(url);
        checkArgument(isValidUrlLink(url), MESSAGE_CONSTRAINTS);
        value = url;
    }

    @Override
    public String toString() {
        return value;
    }
    /**
     * Returns true if a given string is a valid url.
     */
    public static boolean isValidUrlLink(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Url // instanceof handles nulls
                && value.equals(((Url) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
