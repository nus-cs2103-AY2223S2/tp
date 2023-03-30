package seedu.address.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;
    private final String placeholderText;
    private final boolean isOptionalPrefix;

    private Prefix(String prefix, String placeholderText, boolean isOptionalPrefix) {
        this.prefix = prefix;
        this.placeholderText = placeholderText;
        this.isOptionalPrefix = isOptionalPrefix;
    }

    /**
     * Constructs a {@code Prefix} with given prefix and an no placeholder text.
     * @param prefix Prefix value.
     */
    public Prefix(String prefix) {
        this(prefix, "", false);
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
        this(prefix, placeholderText, false);
    }
    //@@author

    /**
     * Returns true if this {@code Prefix} is just a placeholder for
     * index/keywords arguments.
     * @return Whether this is a placeholder.
     */
    public boolean isPlaceholder() {
        return getPrefix().isEmpty();
    }

    public String getPlaceholderText() {
        return placeholderText;
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    public String toString() {
        return getPrefix();
    }

    /**
     * Returns true if this prefix is optional in a command.
     */
    public boolean isOptional() {
        return isOptionalPrefix;
    }

    /**
     * Returns a copy of this {@code Prefix}, but as an optional prefix.
     */
    public Prefix asOptional() {
        return new Prefix(this.prefix, this.placeholderText, true);
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
