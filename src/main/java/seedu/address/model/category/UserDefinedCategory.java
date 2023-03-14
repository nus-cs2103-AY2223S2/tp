package seedu.address.model.category;

/**
 * User-defined category class which allows users to customize their own categories to use.
 */
public class UserDefinedCategory extends Category {

    public UserDefinedCategory(String categoryName, String summary) {
        this.categoryName = categoryName;
        this.summary = summary;
    }

    public void setCategoryName(String newName) {
        this.categoryName = newName;
    }

    public void setDescription(String newSummary) {
        this.summary = newSummary;
    }
}
