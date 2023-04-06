package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

/**
 * A prefix that marks the beginning of an argument in an arguments string.<p>
 * E.g. '/name' in 'add CS2103 /name Software Engineering'.
 */
public class Prefix {
    /**
     * A regex for checking if a prefix is valid.<p>
     *
     * A prefix is valid if it is empty (in the case of a preamble / unnamed argument) or it begins with a
     * slash followed by one or more alphabetical characters (a-Z, A-Z).
     */
    public static final String VALIDATION_REGEX = "(^$|/[A-Za-z]+)";

    private final String prefix;

    /**
     * Constructs a {@code Prefix}.
     *
     * @param prefix The string representing the prefix.
     */
    public Prefix(String prefix) {
        requireNonNull(prefix);
        assert isValidPrefix(prefix);
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    public String toString() {
        return getPrefix();
    }

    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Prefix)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Prefix otherPrefix = (Prefix) obj;
        return otherPrefix.getPrefix().equals(getPrefix());
    }

    /**
     * Returns true if {@code test} is a valid prefix.
     *
     * @param test The string to check if it is a valid prefix.
     * @return True if {@code test} is a valid prefix. Otherwise, false.
     */
    private static boolean isValidPrefix(String test) {
        requireNonNull(test);
        return test.matches(VALIDATION_REGEX);
    }
}
