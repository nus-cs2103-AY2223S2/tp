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
}
