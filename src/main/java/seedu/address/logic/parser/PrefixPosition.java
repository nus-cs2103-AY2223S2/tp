package seedu.address.logic.parser;

/**
 * Represents a prefix's position in an arguments string.
 */
public class PrefixPosition {
    private final int startPosition;
    private final Prefix prefix;

    PrefixPosition(Prefix prefix, int startPosition) {
        this.prefix = prefix;
        this.startPosition = startPosition;
    }

    int getStartPosition() {
        return startPosition;
    }

    Prefix getPrefix() {
        return prefix;
    }
}
