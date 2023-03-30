package seedu.address.model.person.fields;

import java.util.Set;

import seedu.address.model.person.fields.subfields.Tag;

/**
 * Represents a Person's tags in the address book.
 */
public class Tags extends SuperField<Tag> {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric and space";

    public Tags(Set<Tag> values) {
        super(values);
    }

    /**
     * Checks whether all the tags are valid Tag
     *
     * @param tags the set of Tag to be checked
     * @return false if there is at least one invalid tag
     */
    public static boolean isValidTags(Set<Tag> tags) {
        for (Tag tag : tags) {
            if (!Tag.isValidTagName(tag.value)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tags // instanceof handles nulls
                // Uses Java's definition of equality between Sets.
                && this.values.equals(((Tags) other).values)); // state check
    }
}
