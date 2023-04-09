package seedu.address.model.tag;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in
 */
public class CommitmentTag extends Tag {
    public CommitmentTag(String tagName) {
        super(tagName);
    }

    /**
     * @return the corresponding color code for css
     */
    @Override
    public String tagColor() {
        return "#f88379";
    }

    @Override
    public String toString() {
        return " [Commitment: " + tagName.split("XXXXX")[1] + "] ";
    }
}
