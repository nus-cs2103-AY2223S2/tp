package seedu.address.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;

    public Prefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    /**
     * @param param Parameter name.
     * @param isOptional Whether the prefix is optional.
     * @param hasMany Whether the prefix can be used multiple times.
     */
    public String toString(String param, boolean isOptional, boolean hasMany) {
        return toString(param, isOptional) + (hasMany ? "..." : "");
    }

    /**
     * @param param Parameter name.
     * @param isOptional Whether the prefix is optional.
     */
    public String toString(String param, boolean isOptional) {
        return isOptional
                ? String.format("[%s]", toString(param))
                : toString(param);
    }

    public String toString(String param) {
        return prefix + param;
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
}
