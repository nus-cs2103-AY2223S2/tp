package seedu.address.model.tag;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in
 */
public class ModuleTag extends Tag {
    public ModuleTag(String tagName) {
        super(tagName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof ModuleTag
            && tagName.equals(((ModuleTag) other).tagName));
    }

    @Override
    public String toString() {
        return  " [Module: " + tagName.split("XXXXX")[1] + "] ";
    }
}
