package seedu.address.model.category;

/**
 * Category class to represent categories that expenses are grouped under.
 */
public abstract class Category {

    protected String categoryName;
    protected String description;

    public String getCategoryName() {
        return this.categoryName;
    };

    public String getDescription() {
        return this.description;
    };

    public Category(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    @Override
    public String toString() {
        return this.categoryName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Category // instanceof handles nulls
                        && this.categoryName.equals(((Category) other).categoryName)); // state check
    }

    @Override
    public int hashCode() {
        return categoryName.hashCode();
    }
}
