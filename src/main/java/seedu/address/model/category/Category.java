package seedu.address.model.category;

/**
 * Category class to represent categories that expenses are grouped under.
 */
public abstract class Category {

    public static final String MESSAGE_CONSTRAINTS = "Category names should be alphanumeric";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    protected String categoryName;
    protected String description;
    /**
     * Constructor for Category class.
     * @param categoryName Name of the category
     * @param description Description of the category
     */
    public Category(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }
    public String getCategoryName() {
        return this.categoryName;
    };

    public String getDescription() {
        return this.description;
    };

    @Override
    public String toString() {
        return this.categoryName;
    }

    /**
     * Returns true if a given string is a valid category name.
     */
    public static boolean isValidCategoryName(String test) {
        return test.matches(VALIDATION_REGEX);
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
