package seedu.address.model.category;

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
