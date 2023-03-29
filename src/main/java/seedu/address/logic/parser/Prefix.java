package seedu.address.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;
    private final String placeholderText;

    /**
     * Constructs a {@code Prefix} with given prefix and an no placeholder text.
     * @param prefix Prefix value.
     */
    public Prefix(String prefix) {
        this.prefix = prefix;
        this.placeholderText = "";
    }

    //@@author EvitanRelta-reused
    // Reused from https://github.com/AY2223S1-CS2103T-T12-2/tp
    // with minor modifications.
    /**
     * Constructs a {@code Prefix} with given prefix and placeholder text.
     * @param prefix Prefix value.
     * @param placeholderText Placeholder text describing the expected data for the prefix.
     */
    public Prefix(String prefix, String placeholderText) {
        this.prefix = prefix;
        this.placeholderText = placeholderText;
    }
    //@@author

    public String getPlaceholderText() {
        return placeholderText;
    }

    public String getPrefix() {
        return prefix;
    }

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
        return !getPrefix().isBlank()
                ? otherPrefix.getPrefix().equals(getPrefix())
                : otherPrefix.getPrefix().isBlank()
                        && otherPrefix.getPlaceholderText().equals(getPlaceholderText());
    }
}
