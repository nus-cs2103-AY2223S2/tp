package arb.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String mainPrefix;
    private final Optional<String> aliasPrefix;

    public Prefix(String mainPrefix, String aliasPrefix) {
        requireNonNull(mainPrefix);
        this.mainPrefix = mainPrefix;
        this.aliasPrefix = Optional.ofNullable(aliasPrefix);
    }

    public String getPrefix() {
        return this.mainPrefix;
    }

    public boolean isAliasPresent() {
        return this.aliasPrefix.isPresent();
    }

    public String getAlias() {
        return this.aliasPrefix.orElse(null);
    }

    public String toString() {
        return getPrefix();
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainPrefix, aliasPrefix);
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
        return otherPrefix.getPrefix().equals(getPrefix())
                && otherPrefix.aliasPrefix.equals(aliasPrefix);
    }
}
