package seedu.address.model.category;

/**
 * Default miscellaneous category.
 */
public class MiscellaneousCategory extends Category {
    private final String categoryName = "Miscellaneous";
    private final String summary = "Placeholder Description";

    public MiscellaneousCategory() {
        super("Miscellaneous", "Placeholder Description");
    }

    @Override
    public String toString() {
        return "Miscellaneous";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MiscellaneousCategory // instanceof handles nulls
                        && this.categoryName.equals(((MiscellaneousCategory) other).categoryName)); // state check
    }

    @Override
    public int hashCode() {
        return categoryName.hashCode();
    }
}
