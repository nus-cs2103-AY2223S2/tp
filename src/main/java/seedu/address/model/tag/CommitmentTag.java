package seedu.address.model.tag;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in
 */
public class CommitmentTag extends Tag {
    public CommitmentTag(String tagName) {
        super(tagName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof CommitmentTag
            && tagName.equals(((CommitmentTag) other).tagName));
    }

    @Override
    public String toString() {
        return " [Commitment: " + tagName.split("XXXXX")[1] + "] ";
    }
}
