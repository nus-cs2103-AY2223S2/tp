package fasttrack.model.category;

/**
* Category class to represent categories that expenses are not grouped into a
* specific category.
*/
public class MiscellaneousCategory extends Category {
    /**
     * Constructor for MiscellaneousCategory class.
     */
    public MiscellaneousCategory() {
        super("Misc", "Placeholder Description");
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
