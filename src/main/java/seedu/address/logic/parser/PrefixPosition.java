package seedu.address.logic.parser;

/**
 * Represents a prefix's position in an arguments string.
 */
public class PrefixPosition implements Comparable<PrefixPosition> {
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

    @Override
    public String toString() {
        return String.format("%s(%d)", prefix.toString(), startPosition);
    }

    @Override
    public int compareTo(PrefixPosition otherPp) {
        return Integer.compare(startPosition, otherPp.startPosition);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrefixPosition // instanceof handles nulls
                && prefix.equals(((PrefixPosition) other).prefix)
                && startPosition == ((PrefixPosition) other).startPosition);
    }
}
