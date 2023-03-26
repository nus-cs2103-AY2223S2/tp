package seedu.address.model.tag;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in
 */
public class ModuleTag extends Tag {
    public ModuleTag(String tagName) {
        super(tagName);
    }

    /**
     * @return the corresponding color code for css
     */
    @Override
    public String tagColor() {
        return "#193E04";
    }

    @Override
    public String toString() {
        return " [Module: " + tagName.split("XXXXX")[1] + "] ";
    }
}
